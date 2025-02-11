package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private static Javalin app;
    public static MockWebServer server;

    @BeforeEach
    void appStart() throws Exception {
        server = new MockWebServer();
        server.start(8080);
        app = App.getApp();
    }

    @AfterEach
    void testDown() throws IOException {
        server.shutdown();
        app.stop();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertEquals(200, response.code());
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    void testShow() throws SQLException {
        var url = new Url("http://test.io");
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var responce = client.get("/urls/" + url.getId());
            assertThat(responce.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertEquals(200, response.code());
        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("https://some-domain.org");
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath() + "/" + url.getId());
            assertEquals(200, response.code());
        });
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "name=https://some-domain.org:8080/example/path";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertEquals(200, response.code());
            assertThat(response.body().string()).contains("https://some-domain.org:8080");
        });
    }

}
