package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.util.NamedRoutes;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,5,5,5,7,7,10,10,12,12,12,18,18,18,22,22,22,26,27,27,27,32,32,32,32,32,32,32,32,32,48,48,50,50,50,51,51,51,52,52,52,53,53,53,54,54,54,55,56,56,56,58,58,61,61,61,62,62,62,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"mb-3\">\r\n        <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\r\n    </div>\r\n\r\n    <table class=\"table table-striped\">\r\n            <tr>\r\n                <td>ID</td>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\r\n            </tr>\r\n            <tr>\r\n                <td>Имя</td>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\r\n            </tr>\r\n            <tr>\r\n                <td>Дата создания</td>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(ZonedDateTime.of(page.getUrl().getCreatedAt().toLocalDateTime(), ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
				jteOutput.writeContent("</td>\r\n            </tr>\r\n    </table>\r\n\r\n    <h2 class=\"text-center\">Проверки</h2>\r\n    <form class=\"text-center\"");
				var __jte_html_attribute_0 = NamedRoutes.urlPathCheck(page.getUrl().getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\r\n        <button class=\"custom-button\" type='submit'>Запустить проверку</button>\r\n    </form>\r\n\r\n    <table class=\"table table-striped\">\r\n        <thead>\r\n        <tr>\r\n            <td>ID</td>\r\n            <td>Код ответа</td>\r\n            <td>Title</td>\r\n            <td>H1</td>\r\n            <td>Description</td>\r\n            <td>Дата проверки</td>\r\n        </tr>\r\n        </thead>\r\n        <tbody>\r\n        ");
				for (var urlCheck : page.getChecks()) {
					jteOutput.writeContent("\r\n            <tr>\r\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getId());
					jteOutput.writeContent("</td>\r\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getStatusCode());
					jteOutput.writeContent("</td>\r\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getTitle());
					jteOutput.writeContent("</td>\r\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getH1());
					jteOutput.writeContent("</td>\r\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getDescription());
					jteOutput.writeContent("</td>\r\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(ZonedDateTime.of(urlCheck.getCreatedAt().toLocalDateTime(), ZoneId.systemDefault())
                                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
					jteOutput.writeContent("</td>\r\n            </tr>\r\n        ");
				}
				jteOutput.writeContent("\r\n        </tbody>\r\n    </table>\r\n");
			}
		}, page, null);
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
