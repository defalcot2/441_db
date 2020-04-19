package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SessionBean;
import utilities.DataTools;

/**
 * Servlet implementation class TransactionControllerServlet
 */
@WebServlet("/TransactionControllerServlet")
public class TransactionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		boolean beanCheck = session.getAttribute("bean") != null;
		
		if (beanCheck == false){
			utilities.Redirects.gotoLogout(response);
			return;
		} else {
			SessionBean beanObject = (SessionBean) session.getAttribute("bean");
			String userName = beanObject.getUserName();
			
			if (DataTools.NullOrEmpty(userName)) {
				beanObject.setMessage("*****   You have been logged out   *****");
				utilities.Redirects.gotoLogout(response);
				return;
			}
			
			String card = "", cardNumber = "", cost = "", quantity = "", amountOwned = "", action = "", total = "";
			if(utilities.DataTools.IfReferredBy(request, "cardTrader.jsp")) {
				card = request.getParameter("card");
				cardNumber = request.getParameter("cardNumber");
				cost = request.getParameter("cost");
				quantity = request.getParameter("quantity");
				amountOwned = request.getParameter("amountOwned");
				action = request.getParameter("action");
				
				if (DataTools.NullOrEmpty(card) || DataTools.NullOrEmpty(action) || DataTools.NullOrEmpty(cardNumber) || DataTools.NullOrEmpty(cost) || DataTools.NullOrEmpty(quantity) || DataTools.NullOrEmpty(amountOwned)) {
					beanObject.setMessage("*****   Please make sure all fields are filled   *****");
					utilities.Redirects.gotoCardTrader(response);
					return;
				} else if (!DataTools.isInteger(quantity) || Integer.parseInt(quantity) < 0) {
					beanObject.setMessage("*****   Please enter a positive and/or valid quantity   *****");
					utilities.Redirects.gotoCardTrader(response);
					return;
				} else if (!DataTools.isInteger(amountOwned) || Integer.parseInt(amountOwned) < Integer.parseInt(quantity) && action.equalsIgnoreCase("Sell")) {
					beanObject.setMessage("*****   You can't sell more than you own   *****");
					utilities.Redirects.gotoCardTrader(response);
					return;
				}
				
				total = String.format("%.02f", Integer.parseInt(quantity) * Float.parseFloat(cost));
				beanObject.setCurrentCard(new String[]{card, cardNumber, quantity, amountOwned, cost, action, total});
			} else if(utilities.DataTools.IfReferredBy(request, "transactionDetails.jsp")) {
				card = beanObject.getCurrentCard()[0];
				cardNumber = beanObject.getCurrentCard()[1];
				cost = beanObject.getCurrentCard()[4];
				quantity = beanObject.getCurrentCard()[2];
				amountOwned = beanObject.getCurrentCard()[3];
				action = beanObject.getCurrentCard()[5];
				total = beanObject.getCurrentCard()[6];
			}
			
			if( utilities.DataTools.IfReferredBy(request, "transactionDetails.jsp") ) {
				// Register more than once if clicked fast
				String cardPrimaryKey = beanObject.getKeyByValue(card);
				if(Integer.parseInt(amountOwned) == 0 && !DataTools.NullOrEmpty(beanObject.doesUserHaveCard(cardPrimaryKey)))
					amountOwned = beanObject.getCard(beanObject.doesUserHaveCard(cardPrimaryKey))[1];
				String newAmountOwned = Integer.toString(action.equals("Buy") ? ( Integer.parseInt(amountOwned) + Integer.parseInt(quantity) ) : ( Integer.parseInt(amountOwned) - Integer.parseInt(quantity) ) );
				if(!DataTools.NullOrEmpty(cardPrimaryKey)) {
					if(!DataTools.NullOrEmpty(beanObject.doesUserHaveCard(cardPrimaryKey)))
						model.User_Collection_Info.updateCardInUserCollection(beanObject.getUserPrimaryKey(), cardPrimaryKey, newAmountOwned);
					else
						model.User_Collection_Info.enterCardIntoUserCollection(beanObject.getUserPrimaryKey(), cardPrimaryKey, newAmountOwned);
				}
				model.User_Collection_Info.getUserCardCollection(request,response);
				utilities.CreateForm.BuildForms(beanObject);
				utilities.Redirects.gotoCardTrader(response);
			}
			else if( utilities.DataTools.IfReferredBy(request, "cardTrader.jsp") ) utilities.Redirects.gotoTransactionDetails(response);
			else utilities.Redirects.gotoLogout(response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
