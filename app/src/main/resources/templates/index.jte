@template.layout.page(
content = @`
    <h1>Анализатор страниц</h1>
    <form action="/urls" method="post">
        <input type='text' name='url' placeholder='Введите URL' required/>
        <button type='submit'>Отправить</button>
    </form>
`
)
