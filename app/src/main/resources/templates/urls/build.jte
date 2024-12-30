@import hexlet.code.dto.urls.BuildUrlPage
@param BuildUrlPage page

@template.layout.page(

    content = @`
        @if(page.getErrors() != null)
            <div class="mb-3">
                <ul>
                    @for(var validator : page.getErrors().values())
                        @for(var error : validator)
                            <li>${error.getMessage()}</li>
                        @endfor
                    @endfor
                </ul>
            </div>
        @endif

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
