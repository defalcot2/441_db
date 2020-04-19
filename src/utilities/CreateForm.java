package utilities;

import beans.SessionBean;
import java.util.HashMap;

public class CreateForm {
	public static void BuildForms(SessionBean beanObject) {
		buildCardsForSale(beanObject);
		buildCardCollection(beanObject);
	}
	public static void buildCardsForSale(SessionBean beanObject) {
		
		String buildHTML = "";
		for (HashMap.Entry<String, String[]> entry : beanObject.getCardsForSale()) {
			String cardPrimaryKey = entry.getKey();
            String cardsForSaleEntries[] = entry.getValue();
            if(utilities.DataTools.NullOrEmpty(beanObject.doesUserHaveCard(cardPrimaryKey)) || Integer.parseInt(beanObject.getCard(beanObject.doesUserHaveCard(cardPrimaryKey))[1]) <= 0)
				buildHTML += "<form action=\"TransactionControllerServlet\"><tr>"
					+ "<td>" + cardsForSaleEntries[0] + "</td>"
					+ "<input type=\"hidden\" name=\"card\" value=\"" + cardsForSaleEntries[0] + "\">"
					+ "<td>" + cardsForSaleEntries[1] + "</td>"
					+ "<input type=\"hidden\" name=\"cardNumber\" value=\"" + cardsForSaleEntries[1] + "\">"
					+ "<td>$" + String.format("%.2f", Double.parseDouble( cardsForSaleEntries[2] ) ) + "</td>"
					+ "<input type=\"hidden\" name=\"cost\" value=\"" + cardsForSaleEntries[2] + "\">"
					+ "<input type=\"hidden\" name=\"amountOwned\" value=\"0\">"
					+ "<input type=\"hidden\" name=\"action\" value=\"Buy\">"
					+ "<td><input type=\"text\" name=\"quantity\" /></td>"
					+ "<td><input type=\"Submit\" value=\"Purchase\" name=\"submit\" onclick=\"this.disabled=true\"/></td></tr></form>";

		}
		beanObject.setCardsForSaleHTML(buildHTML);
	}
	public static void buildCardCollection(SessionBean beanObject) {
		String buildHTML = "";
		for (HashMap.Entry<String, String[]> entry : beanObject.getCardCollection()) {
            String cardCollectionEntries[] = entry.getValue();
            String cardData[] = beanObject.getCardForSaleData(cardCollectionEntries[0]);
            if(Integer.parseInt(cardCollectionEntries[1]) > 0)
				buildHTML += "<form action=\"TransactionControllerServlet\"><tr>"
					+ "<td>" + cardData[0] + "</td>"
					+ "<input type=\"hidden\" name=\"card\" value=\"" + cardData[0] + "\">"
					+ "<td>" + cardData[1] + "</td>"
					+ "<input type=\"hidden\" name=\"cardNumber\" value=\"" + cardData[1] + "\">"
					+ "<td>$" + String.format("%.2f", Double.parseDouble( cardData[2] ) ) + "</td>"
					+ "<input type=\"hidden\" name=\"cost\" value=\"" + cardData[2] + "\">"
					+ "<td>" + cardCollectionEntries[1] + "</td>"
					+ "<input type=\"hidden\" name=\"amountOwned\" value=\"" + cardCollectionEntries[1] + "\">"
					+ "<td>$" + String.format("%.2f", ( Integer.parseInt(cardCollectionEntries[1]) * Double.parseDouble(cardData[2])) ) + "</td>"
					+ "<input type=\"hidden\" name=\"total\" value=\"" + ( Integer.parseInt(cardCollectionEntries[1]) * Double.parseDouble(cardData[2])) + "\">"
					+ "<td><select name=\"action\"><option value=\"Buy\">Buy</option><option value=\"Sell\">Sell</option></select></td>"
					+ "<td><input type=\"text\" name=\"quantity\" /></td>"
					+ "<td><input type=\"Submit\" value=\"Make Transaction\" name=\"submit\" onclick=\"this.disabled=true\"/></td></tr></form>";
		}
		beanObject.setCardCollectionHTML(buildHTML);
	}
}
