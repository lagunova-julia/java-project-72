package hexlet.code.model;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class Url {
    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public Url(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }
    public void setId(Long id) {
        this.id = id;
    }
}
