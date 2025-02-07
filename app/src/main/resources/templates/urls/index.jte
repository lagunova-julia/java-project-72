@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.NamedRoutes
@import java.time.ZoneId
@import java.time.ZonedDateTime
@import java.time.format.DateTimeFormatter
@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
    <h1>Все сайты</h1>

    <table class="table table-striped">
        @for(var url : page.getUrls())
            <tr>
                <tr>
                    <td>ID</td>
                    <td>${url.getId()}</td>
                </tr>
                <tr>
                    <td>Имя</td>
                    <td><a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a></td>
                </tr>
                <tr>
                    <td>Последняя проверка</td>
                    <td>${ZonedDateTime.of(page.getLastChecks().get(url.getId()).getCreatedAt()
                                .toLocalDateTime(), ZoneId.systemDefault())
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))}</td>
                </tr>
                <tr>
                    <td>Код ответа</td>
                    <td>${page.getLastChecks().get(url.getId()).getStatusCode()}</td>
                </tr>
            </tr>
        @endfor
    </table>
`,
)
