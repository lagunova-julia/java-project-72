package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AppTest {

    private static Javalin app;
    public static MockWebServer mockServer;
    public static MockResponse mockResponse;
    private static Path getFixedPath(String fileName) {
        return Path.of(new File(fileName).getAbsolutePath());
    }

    public static String readFixedPath(String fileName) throws Exception {

        Path filePath = getFixedPath(fileName);
        if (!Files.exists(filePath)) {
            throw new Exception("File '" + filePath + "' does not exist");
        }
        return Files.readString(filePath);

    }

    @BeforeAll
    static void serverStart() throws Exception {
        mockServer = new MockWebServer();
        mockResponse = new MockResponse().setBody(readFixedPath("src/test/resources/test.html"));
        mockServer.enqueue(mockResponse);
        mockServer.start(8080);
    }

    @BeforeEach
    void appStart() throws SQLException, IOException {
        app = App.getApp();
    }

    @AfterEach
    void testDown() {
        app.stop();
    }

    @AfterAll
    static void serverDown() throws Exception {
        mockServer.shutdown();
    }


    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.rootPath());
            assertEquals(200, response.code());
            assertThat(response.body().string()).contains("Page analyzer");
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
            var requestBody = "url=https://ru.hexlet.io";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertEquals(200, response.code());
            assertThat(response.body().string()).contains("https://ru.hexlet.io");
        });
    }

    @Test
    public void testCheckUrl() {
        String url = mockServer.url("/").toString().replaceAll("/$", "");

        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=" + url;
            assertThat(client.post("/urls", requestBody).code()).isEqualTo(200);

            var actualUrl = UrlRepository.findByName(new Url(url)).orElse(null);

            assertThat(actualUrl).isNotNull();
            assertThat(actualUrl.getName()).isEqualTo(url);

            client.post("/urls/" + actualUrl.getId() + "/checks");

            var response = client.get("/urls/" + actualUrl.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains(url);

            var actualCheckUrl = UrlCheckRepository.findAllLastChecks().get(actualUrl.getId());

            assertThat(actualCheckUrl).isNotNull();
            assertThat(actualCheckUrl.getStatusCode()).isEqualTo(200);
            assertThat(actualCheckUrl.getTitle()).isEqualTo("Test page");
            assertThat(actualCheckUrl.getH1()).isEqualTo("Do not expect a miracle, miracles yourself!");
            assertThat(actualCheckUrl.getDescription()).contains("statements of great people");
        });
    }
}
