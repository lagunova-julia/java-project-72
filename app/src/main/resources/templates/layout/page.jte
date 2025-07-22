@import gg.jte.Content
@import hexlet.code.dto.BasePage
@param Content content
@param BasePage page = null
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Page analyzer</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
              crossorigin="anonymous">
        <style>
            body {
                background-color: #f1eae1;
            }
            .custom-button {
                background-color: #a8b252; /* Розовый цвет */
                color: white; /* Цвет текста */
                border: none; /* Убираем границу */
                padding: 10px 20px; /* Добавляем отступы */
                border-radius: 5px; /* Закругляем углы */
                font-size: 16px; /* Изменяем размер шрифта */
            }
            .custom-button:hover {
                background-color: #648b64; /* Цвет при наведении */
                box-shadow: 0 0 5px #717d1f; /* Тень при фокусировке */
            }
            .custom-input {
                border: 2px solid #a8b252; /* Цвет рамки */
                border-radius: 5px; /* Закругление углов */
                padding: 10px; /* Внутренний отступ */
                transition: border-color 0.3s; /* Плавный переход */
            }
            .custom-input:focus {
                border-color: #648b64; /* Цвет рамки при фокусировке */
                box-shadow: 0 0 5px #e599b2; /* Тень при фокусировке */
                outline: none; /* Убирает стандартное выделение */
            }
            .custom-input:hover {
                border-color: #648b64; /* Цвет рамки при наведении */
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Главная</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/urls">Сайты</a>
                    </li>
                </ul>
            </div>
        </nav>

        @if(page != null && page.getFlash() != null)
            <div class="alert alert-${page.getFlashType()}" role="alert">
                ${page.getFlash()}
            </div>
        @endif


        <div class="mx-auto p-4 py-md-5">
            ${content}
        </div>

        @if (footer != null)
            <footer class="text-center text-lg-start bg-light text-muted">
                <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
                    ${footer}
                </div>
            </footer>
        @endif

    </body>
</html>
