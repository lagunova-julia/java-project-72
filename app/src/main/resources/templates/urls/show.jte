@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.util.NamedRoutes
@import java.time.ZoneId
@import java.time.ZonedDateTime
@import java.time.format.DateTimeFormatter
@param UrlPage page

@template.layout.page(
page = page,
content = @`
    <div class="mb-3">
        <h1>Сайт: ${page.getUrl().getName()}</h1>
    </div>

    <table class="table table-striped">
            <tr>
                <td>ID</td>
                <td>${page.getUrl().getId()}</td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>${page.getUrl().getName()}</td>
            </tr>
            <tr>
                <td>Дата создания</td>
                <td>${ZonedDateTime.of(page.getUrl().getCreatedAt().toLocalDateTime(), ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))}</td>
            </tr>
    </table>

    <h2 class="text-center">Проверки</h2>
    <form class="text-center" action="${NamedRoutes.urlPathCheck(page.getUrl().getId())}" method="post">
        <button class="custom-button" type='submit'>Запустить проверку</button>
    </form>

    <table class="table table-striped">
        <thead>
        <tr>
            <td>ID</td>
            <td>Код ответа</td>
            <td>Title</td>
            <td>H1</td>
            <td>Description</td>
            <td>Дата проверки</td>
        </tr>
        </thead>
        <tbody>
        @for(var urlCheck : page.getChecks())
            <tr>
                <td>${urlCheck.getId()}</td>
                <td>${urlCheck.getStatusCode()}</td>
                <td>${urlCheck.getTitle()}</td>
                <td>${urlCheck.getH1()}</td>
                <td>${urlCheck.getDescription()}</td>
                <td>${ZonedDateTime.of(urlCheck.getCreatedAt().toLocalDateTime(), ZoneId.systemDefault())
                                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))}</td>
            </tr>
        @endfor
        </tbody>
    </table>
`,
)
