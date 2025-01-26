@import hexlet.code.dto.urls.UrlPage
@import java.time.ZoneId
@import java.time.ZonedDateTime
@import java.time.format.DateTimeFormatter
@param UrlPage page

@template.layout.page(

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
`,
)
