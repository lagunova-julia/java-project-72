package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    private static Javalin app;

    @BeforeEach
    public final void setUp() throws Exception {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
            assertEquals(200, response.code());
            assertThat(response.body().string()).contains("Анализатор страниц");
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
