package hexlet.code;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.repository.BaseRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import lombok.extern.slf4j.Slf4j;

import hexlet.code.controller.RootController;
import hexlet.code.controller.Controller;

@Slf4j
public final class App {

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }

    private static String getDatabaseUrl() {
        return System.getenv().getOrDefault("JDBC_DATABASE_URL",
                "jdbc:h2:mem:project;LOCK_TIMEOUT=10000;LOCK_MODE=0;DB_CLOSE_DELAY=-1;");
    }

    public static boolean isProduction() {
        return System.getenv().getOrDefault("APP_ENV", "development").equals("production");
    }

    public static Javalin getApp() throws IOException, SQLException {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDatabaseUrl());

        if (isProduction()) {
            var username = System.getenv("JDBC_DATABASE_USERNAME");
            var password = System.getenv("JDBC_DATABASE_PASSWORD");
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);
        }

        var dataSource = new HikariDataSource(hikariConfig);
        String sql = readResourceFile("schema.sql");

        log.info(sql);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.before(ctx -> {
            ctx.contentType("text/html; charset=utf-8");
        });



        app.get("/", RootController::index);
        app.get(NamedRoutes.urlsPath(), Controller::index);
        app.post(NamedRoutes.urlsPath(), Controller::create);
        app.get(NamedRoutes.urlPath("{id}"), Controller::show);
        app.post(NamedRoutes.urlPathCheck("{id}"), Controller::checkAndSave);

        return app;
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    private static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }

    }

    public static void main(String[] args) throws SQLException, IOException {
        Javalin app = getApp();
        app.start(getPort());
    }
}
