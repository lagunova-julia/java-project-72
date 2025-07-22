@import hexlet.code.dto.BasePage
@param BasePage page = null

@template.layout.page(
page = page,
content = @`
    <h1 class="text-center mb-0 pb-1">Анализатор страниц</h1>
    <form class="text-center" action="/urls" method="post">
        <input
                type='text'
                class="custom-input"
                name='url'
                placeholder='Введите URL' required/>
        <button class="custom-button" type='submit'>Отправить</button>
    </form>
`
)
