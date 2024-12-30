package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.controller.Controller;
import hexlet.code.controller.RootController;
import hexlet.code.repository.BaseRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class App {
    public static Javalin getApp() throws Exception {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:hexlet_project;DB_CLOSE_DELAY=-1;");

        var dataSource = new HikariDataSource(hikariConfig);

        // Получаем путь до файла в src/main/resources
        var url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));

        // Получаем соединение, создаем стейтмент и выполняем запрос
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }

        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            //config.fileRenderer(new JavalinJte());
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

//        app.get("/", ctx -> {
//            ctx.render("index.jte");
//        });
        return app;
    }

    public static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

//    public static void createDatabase(Javalin app) {
//        // Конфигурация базы данных
//        DatabaseConfig dbConfig = new DatabaseConfig();
//        dbConfig.initializeDatabase();
//
//        // Пример использования DataSource и проверка подключения БД h2
////        app.get("/db", ctx -> {
////            try (var conn = dbConfig.getDataSource().getConnection()) {
////                ctx.result("Database connection is successful!");
////            } catch (Exception e) {
////                ctx.result("Database connection failed: " + e.getMessage());
////            }
////        });
//    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    public static void main(String[] args) throws Exception {
        Javalin app = getApp();
        app.start(getPort());

//        createDatabase(app);

        app.get(NamedRoutes.rootPath(), RootController::index);
//        app.get(NamedRoutes.rootPath(), Controller::build);
        app.post(NamedRoutes.urlsPath(), Controller::create);
        app.get(NamedRoutes.urlsPath(), Controller::index);
        app.get(NamedRoutes.urlPath("{id}"), Controller::show);
    }
}
