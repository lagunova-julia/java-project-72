package hexlet.code.controller;

import hexlet.code.dto.BasePage;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    public static void index(Context ctx) {
        var page = new BasePage(); // Ваш DTO-класс
        page.setFlash(ctx.sessionAttribute("flash"));
        page.setFlashType(ctx.sessionAttribute("flash-type"));

        ctx.render("index.jte", model("page", page));
        ctx.sessionAttribute("flash", null);
    }
}
