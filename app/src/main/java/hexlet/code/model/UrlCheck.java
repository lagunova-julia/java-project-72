package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
public class UrlCheck {
    private Long id; //должно генерироваться автоматически базой данных
    private int statusCode;
    private String title;
    private String h1;
    private String description; //должно уметь хранить большие объёмы текста
    private Long urlId; //идентификатор сущности Url, проверка для которого была выполнена
    //Сущность Url связана с текущей сущностью UrlCheck связью один-ко-многим
    private Timestamp createdAt;

    public UrlCheck(int statusCode, String title, String h1, String description) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
    }

    public UrlCheck(Long urlId, int statusCode, String h1, String title, String description) {
        this.urlId = urlId;
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
    }
}
