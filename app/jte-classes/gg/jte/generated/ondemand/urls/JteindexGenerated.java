package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.util.NamedRoutes;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,5,5,5,7,7,10,10,23,23,25,25,25,26,26,26,26,26,26,26,26,26,26,26,26,27,27,28,30,30,30,30,31,31,31,32,32,35,35,37,37,40,40,40,41,41,41,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <h1>Все сайты</h1>\r\n\r\n    <table class=\"table table-striped\">\r\n        <thead>\r\n        <tr>\r\n            <td>ID</td>\r\n            <td>Имя</td>\r\n            <td>Последняя проверка</td>\r\n            <td>Код ответа</td>\r\n        </tr>\r\n        </thead>\r\n        <tbody>\r\n        ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\r\n            <tr>\r\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</td>\r\n                <td><a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a></td>\r\n                ");
					if (page.getLastChecks().get(url.getId()) != null) {
						jteOutput.writeContent("\r\n                    <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(ZonedDateTime.of(page.getLastChecks().get(url.getId()).getCreatedAt()
                                .toLocalDateTime(), ZoneId.systemDefault())
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
						jteOutput.writeContent("</td>\r\n                    <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getLastChecks().get(url.getId()).getStatusCode());
						jteOutput.writeContent("</td>\r\n                ");
					} else {
						jteOutput.writeContent("\r\n                    <td></td>\r\n                    <td></td>\r\n                ");
					}
					jteOutput.writeContent("\r\n            </tr>\r\n        ");
				}
				jteOutput.writeContent("\r\n        </tbody>\r\n    </table>\r\n");
			}
		}, page, null);
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
