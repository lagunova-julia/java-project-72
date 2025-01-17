@import hexlet.code.dto.urls.UrlPage
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
                <td>${page.getUrl().getCreatedAt().toString()}</td>
            </tr>
    </table>
`,
)
