package beans;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class SessionBean {
	private String userName = "";
	private String message = "";
	private String userPrimaryKey = "";
	private String[] currCard = new String[7];
	private HashMap<String, String[]> cardCollection = new HashMap<String, String[]>();
	private HashMap<String, String[]> cardsForSale = new HashMap<String, String[]>();

	private String cardsForSaleHTML;
	private String cardCollectionHTML;
	
	public String getUserName()
    {
        return userName;
    }
    public String getMessage()
    {
        return message;
    }
    public String getUserPrimaryKey() {
    	return userPrimaryKey;
    }
    public String[] getCurrentCard() {
    	return currCard;
    }
    public Set<Entry<String, String[]>> getCardsForSale()
    {	
    	Set<Entry<String, String[]>> set = cardsForSale.entrySet();
    	return set;
    }
    public String[] getCard(String primaryKey)
    {	
    	String[] set = cardCollection.get(primaryKey);
    	return set;
    }
    public String[] getCardForSaleData(String primaryKey)
    {	
    	String[] set = cardsForSale.get(primaryKey);
    	return set;
    }
    public String getKeyByValue(String cardName) {
    	for (HashMap.Entry<String, String[]> entry : getCardsForSale()) {
            String cardsForSaleEntries[] = entry.getValue();
            if(cardName.equals(cardsForSaleEntries[0]))
            	return entry.getKey();
    	}
    	return "";
    }
    public String doesUserHaveCard(String cardPrimaryKey) {
    	for (HashMap.Entry<String, String[]> entry : getCardCollection()) {
            String cardCollectionEntries[] = entry.getValue();
            if(cardPrimaryKey.equals(cardCollectionEntries[0]))
            	return entry.getKey();
    	}
    	return "";
    }
    public Set<Entry<String, String[]>> getCardCollection()
    {	
    	Set<Entry<String, String[]>> set = cardCollection.entrySet();
    	return set;
    }
    public String getCardCollectionHTML() {
		return cardCollectionHTML;
	}
    public String getCardsForSaleHTML() {
		return cardsForSaleHTML;
	}


    public void setUserName(String value)
    {
        this.userName = value;
    }
    public void setCurrentCard(String[] card) {
    	this.currCard = card;
    }
    public void setMessage(String value)
    {
        this.message = value;
    }
    public void setUserPrimaryKey(String value) {
    	this.userPrimaryKey = value;
    }
    public void setCardCollection(String primaryKey, String cardPrimaryKey, String amount ) {
    	this.cardCollection.put(primaryKey, new String[] { cardPrimaryKey, amount });
    }
    public void setCardsForSale(String primaryKey, String Card, String CardNumber, String Price ) {
    	this.cardsForSale.put(primaryKey, new String[] { Card, CardNumber, Price });
    }
	public void setCardsForSaleHTML(String passedHTML) {
		this.cardsForSaleHTML = passedHTML;
	}
	public void setCardCollectionHTML(String passedHTML) {
		this.cardCollectionHTML = passedHTML;
	}
}
