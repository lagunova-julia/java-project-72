package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


public class BuildUrlPage extends BasePage {
    private String url;
    private Map<String, List<ValidationError<Object>>> errors;

    public BuildUrlPage(String url, Map<String, List<ValidationError<Object>>> errors) {
        this.url = url;
        this.errors = errors;
    }


    public String getUrl() {
        return url;
    }

    public Map<String, List<ValidationError<Object>>> getErrors() {
        return errors;
    }
}
