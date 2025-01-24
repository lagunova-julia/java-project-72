package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import hexlet.code.dto.BasePage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,18,18,19,20,21,22,23,24,27,28,29,30,31,32,35,36,39,40,41,42,45,46,47,50,68,68,69,69,69,69,70,70,70,72,72,76,76,76,79,79,82,82,82,85,85,88,88,88,2,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, BasePage page, Content footer) {
		jteOutput.writeContent("\r\n<!doctype html>\r\n<html lang=\"en\">\r\n    <head>\r\n        <meta charset=\"utf-8\" />\r\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n        <title >Анализатор страниц</title>\r\n        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"\r\n              rel=\"stylesheet\"\r\n              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"\r\n              crossorigin=\"anonymous\">\r\n        <style>\r\n            body {\r\n                background-color: #ffcccb; ");
		jteOutput.writeContent("\r\n");
		jteOutput.writeContent("\r\n");
		jteOutput.writeContent("\r\n");
		jteOutput.writeContent("\r\n");
		jteOutput.writeContent("\r\n");
		jteOutput.writeContent("\r\n");
		jteOutput.writeContent("\r\n            }\r\n            .custom-button {\r\n                background-color: #e599b2; ");
		jteOutput.writeContent("\r\n                color: white; ");
		jteOutput.writeContent("\r\n                border: none; ");
		jteOutput.writeContent("\r\n                padding: 10px 20px; ");
		jteOutput.writeContent("\r\n                border-radius: 5px; ");
		jteOutput.writeContent("\r\n                font-size: 16px; ");
		jteOutput.writeContent("\r\n            }\r\n            .custom-button:hover {\r\n                background-color: #db7093; ");
		jteOutput.writeContent("\r\n                box-shadow: 0 0 5px #e599b2; ");
		jteOutput.writeContent("\r\n            }\r\n            .custom-input {\r\n                border: 2px solid #e599b2; ");
		jteOutput.writeContent("\r\n                border-radius: 5px; ");
		jteOutput.writeContent("\r\n                padding: 10px; ");
		jteOutput.writeContent("\r\n                transition: border-color 0.3s; ");
		jteOutput.writeContent("\r\n            }\r\n            .custom-input:focus {\r\n                border-color: #007BFF; ");
		jteOutput.writeContent("\r\n                box-shadow: 0 0 5px #e599b2; ");
		jteOutput.writeContent("\r\n                outline: none; ");
		jteOutput.writeContent("\r\n            }\r\n            .custom-input:hover {\r\n                border-color: #ff69b4; ");
		jteOutput.writeContent("\r\n            }\r\n        </style>\r\n    </head>\r\n    <body>\r\n        <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n            <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">\r\n                <ul class=\"navbar-nav mx-auto\">\r\n                    <li class=\"nav-item active\">\r\n                        <a class=\"nav-link\" href=\"/\">Главная</a>\r\n                    </li>\r\n                    <li class=\"nav-item\">\r\n                        <a class=\"nav-link\" href=\"/urls\">Сайты</a>\r\n                    </li>\r\n                </ul>\r\n            </div>\r\n        </nav>\r\n\r\n        ");
		if (page != null && page.getFlash() != null) {
			jteOutput.writeContent("\r\n            <div class=\"alert alert-");
			jteOutput.setContext("div", "class");
			jteOutput.writeUserContent(page.getFlashType());
			jteOutput.setContext("div", null);
			jteOutput.writeContent("\" role=\"alert\">\r\n                ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(page.getFlash());
			jteOutput.writeContent("\r\n            </div>\r\n        ");
		}
		jteOutput.writeContent("\r\n\r\n\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\r\n        </div>\r\n\r\n        ");
		if (footer != null) {
			jteOutput.writeContent("\r\n            <footer class=\"text-center text-lg-start bg-light text-muted\">\r\n                <div class=\"text-center p-4\" style=\"background-color: rgba(0, 0, 0, 0.05);\">\r\n                    ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(footer);
			jteOutput.writeContent("\r\n                </div>\r\n            </footer>\r\n        ");
		}
		jteOutput.writeContent("\r\n\r\n    </body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		BasePage page = (BasePage)params.getOrDefault("page", null);
		Content footer = (Content)params.getOrDefault("footer", null);
		render(jteOutput, jteHtmlInterceptor, content, page, footer);
	}
}
