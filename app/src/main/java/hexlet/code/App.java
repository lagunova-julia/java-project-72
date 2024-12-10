package hexlet.code;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

public class App {
    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            //config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.result("Hello World"));

        return app;
    }

    public static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
}
