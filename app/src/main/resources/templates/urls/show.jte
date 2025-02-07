@import hexlet.code.dto.urls.UrlPage
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
    <form class="text-center" action="/urls/{id}/checks" method="post">
        <button class="custom-button" type='submit'>Запустить проверку</button>
    </form>

    <table class="table table-striped">
        @for(var urlCheck : page.getChecks())
            <tr>
                <tr>
                    <td>ID</td>
                    <td>${urlCheck.getId()}</td>
                </tr>
                <tr>
                    <td>Код ответа</td>
                    <td>${urlCheck.getStatusCode()}</td>
                </tr>
                <tr>
                    <td>Title</td>
                    <td>${urlCheck.getTitle()}</td>
                </tr>
                <tr>
                    <td>H1</td>
                    <td>${urlCheck.getH1()}</td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td>${urlCheck.getDescription()}</td>
                </tr>
                <tr>
                    <td>Дата проверки</td>
                    <td>${ZonedDateTime.of(urlCheck.getCreatedAt().toLocalDateTime(), ZoneId.systemDefault())
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))}</td>
                </tr>
            </tr>
        @endfor
    </table>
`,
)
