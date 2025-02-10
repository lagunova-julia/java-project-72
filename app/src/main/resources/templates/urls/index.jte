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
        <thead>
        <tr>
            <td>ID</td>
            <td>Имя</td>
            <td>Последняя проверка</td>
            <td>Код ответа</td>
        </tr>
        </thead>
        <tbody>
        @for(var url : page.getUrls())
            <tr>
                <td>${url.getId()}</td>
                <td><a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a></td>
                @if (page.getLastChecks().get(url.getId()) != null)
                    <td>${ZonedDateTime.of(page.getLastChecks().get(url.getId()).getCreatedAt()
                                .toLocalDateTime(), ZoneId.systemDefault())
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))}</td>
                    <td>${page.getLastChecks().get(url.getId()).getStatusCode()}</td>
                @else
                    <td></td>
                    <td></td>
                @endif
            </tr>
        @endfor
        </tbody>
    </table>
`,
)
