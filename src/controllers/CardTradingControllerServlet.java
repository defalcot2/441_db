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
 * Servlet implementation class CardTradingControllerServlet
 */
@WebServlet("/CardTradingControllerServlet")
public class CardTradingControllerServlet extends HttpServlet {
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
			} else {
				model.Available_Cards_Info.cardsInDB(request,response);
				model.User_Collection_Info.getUserCardCollection(request,response);
				utilities.CreateForm.BuildForms(beanObject);
				
				utilities.Redirects.gotoCardTrader(response);	
			}
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
