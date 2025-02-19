package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public final class BuildUrlPage extends BasePage {
    private String url;
    private Map<String, List<ValidationError<Object>>> errors;
}
