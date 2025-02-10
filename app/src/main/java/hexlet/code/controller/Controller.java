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
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
                ctx.sessionAttribute("flash", "Страница уже существует");
                ctx.sessionAttribute("flash-type", "error");
                ctx.redirect(NamedRoutes.urlsPath());
                return;
            }

            UrlRepository.save(preparedUrl);
            ctx.sessionAttribute("flash", "Страница успешно добавлена!");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.urlsPath());
        } catch (ValidationException e) {
            var inputUrl = ctx.formParam("url");
            ctx.sessionAttribute("flash", "Некорректный URL");
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
        var id = ctx.pathParamAsClass("id", Long.class).get();
        String url = UrlRepository.find(id).toString();

        HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();
        int statusCode = jsonResponse.getStatus();

        HttpResponse<String> response = Unirest.get(url).asString();
        String body = response.getBody();
        Document doc = Jsoup.parse(body);
        String title = doc.title();
        String h1 = doc.select("h1").text();
        String description = doc.select("meta[name=description]").attr("content");

        var urlCheck = new UrlCheck(id, statusCode, title, h1, description);
        UrlCheckRepository.save(urlCheck);
        ctx.sessionAttribute("flash", "Страница успешно проверена!");
        ctx.sessionAttribute("flash-type", "success");
        ctx.redirect(NamedRoutes.urlPath(id));
    }
}
