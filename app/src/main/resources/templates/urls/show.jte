@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

@template.layout.page(

content = @`
    <div class="mb-3">
        <h1>${page.getUrl().getName()}</h1>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Последняя проверка</th>
        </tr>
        </thead>
        <tbody>
            @for(var url : page.getUrl())
                <tr>
                    <td>
                        ${page.getId()}
                    </td>
                    <td>
                        <a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                    </td>
                </tr>
            @endfor
        </tbody>
    </table>
`,
)
