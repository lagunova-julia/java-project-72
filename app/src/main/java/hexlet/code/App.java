package hexlet.code;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

public class App {
    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            //config.fileRenderer(new JavalinJte());
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });
        return app;
    }

    public static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    public static void createDatabase(Javalin app) {
        // Конфигурация базы данных
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.initializeDatabase();

        // Пример использования DataSource и проверка подключения БД h2
//        app.get("/db", ctx -> {
//            try (var conn = dbConfig.getDataSource().getConnection()) {
//                ctx.result("Database connection is successful!");
//            } catch (Exception e) {
//                ctx.result("Database connection failed: " + e.getMessage());
//            }
//        });
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());

        createDatabase(app);
    }
}
