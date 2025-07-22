package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс BasePage представляет базовую страницу с поддержкой флэш-сообщений.
 *
 * <p>Флэш-сообщения могут использоваться для предоставления временной информации пользователю,
 * например, уведомлений о результатах выполнения операций.</p>
 */
@Getter
@Setter
public class BasePage {
    /**
     * Флэш-сообщение, которое будет отображено на странице.
     */
    private String flash;
    /**
     * Тип флэш-сообщения (например, success, error).
     */
    private String flashType;
}
