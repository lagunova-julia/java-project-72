package hexlet.code.controller;

import hexlet.code.dto.urls.BuildUrlPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URL;
import java.sql.SQLException;
import static io.javalin.rendering.template.TemplateUtil.model;
import java.net.URI;
import java.util.Optional;

public class Controller {
    public static void create(Context ctx) throws Exception {
        try {
            String inputUrl = ctx.formParamAsClass("url", String.class)
                    .check(value -> !value.isEmpty(), "URL is empty")
                    .get();

            URI uri = new URI(inputUrl);
            URL url = uri.toURL();

            String baseUrl = String.format("%s://%s%s",
                    url.getProtocol(),
                    url.getHost(),
                    (url.getPort() > 0 ? ":" + url.getPort() : ""));
            Url preparedUrl = new Url(baseUrl);

            Optional<Url> existingUrl = UrlRepository.findByName(preparedUrl);
            if (existingUrl.isPresent()) {
                ctx.sessionAttribute("flash", "Page is already exist");
                ctx.sessionAttribute("flash-type", "error");
                ctx.redirect(NamedRoutes.urlsPath());
                return;
            }

            UrlRepository.save(preparedUrl);
            ctx.sessionAttribute("flash", "Page is added successfully!");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.urlsPath());
        } catch (ValidationException e) {
            var inputUrl = ctx.formParam("url");
            ctx.sessionAttribute("flash", "Incorrect URL");
            ctx.sessionAttribute("flash-type", "error");
            var page = new BuildUrlPage(inputUrl, e.getErrors());
            ctx.render("urls/build.jte", model("page", page)).status(422);
        }
    }

    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var lastChecks = UrlCheckRepository.findAllLastChecks();
        var page = (lastChecks == null) ? new UrlsPage(urls) : new UrlsPage(urls, lastChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url with id = " + id + " not found"));
        var urlChecks = UrlCheckRepository.findAllChecks(id);
        var page = new UrlPage(url, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void checkAndSave(Context ctx) throws SQLException {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        Url url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url with id = " + id + " not found"));

        try {
            HttpResponse<String> response = Unirest.get(url.getName()).asString();
            Document doc = Jsoup.parse(response.getBody());

            int statusCode = response.getStatus();
            String title = doc.title();
            Element h1Element = doc.selectFirst("h1");
            String h1 = h1Element == null ? "" : h1Element.text();
            Element descriptionElement = doc.selectFirst("meta[name=description]");
            String description = descriptionElement == null ? "" : descriptionElement.attr("content");

            UrlCheck newUrlCheck = new UrlCheck(statusCode, title, h1, description);
            newUrlCheck.setUrlId(id);
            UrlCheckRepository.save(newUrlCheck);

            ctx.sessionAttribute("flash", "Page checked successfully!");
            ctx.sessionAttribute("flash-type", "success");

        } catch (UnirestException e) {
            ctx.sessionAttribute("flash", "Incorrect address");
            ctx.sessionAttribute("flash-type", "danger");
        } catch (Exception e) {
            ctx.sessionAttribute("flash", e.getMessage());
            ctx.sessionAttribute("flash-type", "danger");
        }

        ctx.redirect("/urls/" + url.getId());
    };

}
