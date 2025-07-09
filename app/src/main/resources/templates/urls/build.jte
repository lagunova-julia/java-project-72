@import hexlet.code.dto.urls.BuildUrlPage
@param BuildUrlPage page

@template.layout.page(
page = page,
content = @`
    <div class="mx-auto p-4 py-md-5">
        <form action="/" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Url
                    <input type="text" class="form-control" name="url" value="${page.getUrl()}" />
                </label>
            </div>
            <input type="submit" class="btn btn-primary" value="Submit" />
        </form>
    </div>
`,
)
