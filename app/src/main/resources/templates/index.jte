@template.layout.page(
content = @`
    <h1 class="text-center">Анализатор страниц</h1>
    <form class="text-center" action="/urls" method="post">
        <input type='text' class="custom-input" name='url' placeholder='Введите URL' required/>
        <button class="custom-button" type='submit'>Отправить</button>
    </form>
`
)
