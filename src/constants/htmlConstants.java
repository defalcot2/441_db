package constants;

public class htmlConstants {
	public static String htmlHead(String title) {
		return "<!Doctype html>"
				+ "<html>"
				+ "<head>"
					+ "<meta charset=\"ISO-8859-1\">"
					+ "<title>" + title + "</title>"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/servlets.css\"/>"
				+ "</head>"
				+ "<body>";
	}
	public static String htmlClose = "</body></html>";
	public static String logout(String input) {
		return "<hr/>" + input + "<form action=\"LogOutControllerServlet\"><div class=\"input-right\">"
				+ "<input type=\"Submit\" value=\"Logout\" />"
				+ "</div></form>";
	}
}