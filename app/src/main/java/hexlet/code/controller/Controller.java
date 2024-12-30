package hexlet.code.controller;

import hexlet.code.dto.urls.BuildUrlPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.net.URL;
import java.sql.SQLException;
import static io.javalin.rendering.template.TemplateUtil.model;
import java.net.URI;
import java.util.Optional;

public class Controller {
//    public static void build(Context ctx) {
//        var page = new BuildUrlPage();
//        ctx.render("urls/build.jte", model("page", page));
//    }

    // BEGIN
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
        var page = new UrlsPage(urls);
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url with id = " + id + " not found"));
        var page = new UrlPage(url);
        ctx.render("urls/show.jte", model("page", page));
    }
}
