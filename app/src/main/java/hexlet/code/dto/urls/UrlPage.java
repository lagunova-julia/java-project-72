package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public final class UrlPage extends BasePage {
    private Url url;
    private List<UrlCheck> checks;

    public UrlPage(Url url) {
        this.url = url;
    }
}
