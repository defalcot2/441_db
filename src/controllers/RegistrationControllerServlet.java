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
 * Servlet implementation class RegistrationControllerServlet
 */
@WebServlet("/RegistrationControllerServlet")
public class RegistrationControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!utilities.DataTools.IfReferredBy(request, "registration.jsp")){
			utilities.Redirects.gotoRegistration(response);
			return;
		} 
		
		HttpSession session = request.getSession(false);
		boolean beanCheck = session.getAttribute("bean") != null;
		
		if (beanCheck == false){
			utilities.Redirects.gotoRegistration(response);
			return;
		} else {
			SessionBean beanObject = (SessionBean) session.getAttribute("bean");
			
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String confirm = request.getParameter("confirm");
			if (DataTools.NullOrEmpty(userName) || DataTools.NullOrEmpty(password) || DataTools.NullOrEmpty(confirm)) {
				beanObject.setMessage("*****   Do not leave fields empty   *****");
				utilities.Redirects.gotoRegistration(response);
				return;
			} else if (!password.equalsIgnoreCase(confirm)){
				beanObject.setMessage("*****   Passwords dont match   *****");
				utilities.Redirects.gotoRegistration(response);
				return;
			} else if (model.User_Login_Register_Info.isUserInDB(userName)){
				beanObject.setMessage("*****   " + userName + " username taken   *****");
				utilities.Redirects.gotoRegistration(response);
				return;
			} else {
				model.User_Login_Register_Info.addUserToDB(userName, password);
				model.Available_Cards_Info.insertCardsIntoDB();
				beanObject.setUserName(userName);
				beanObject.setUserPrimaryKey(model.User_Login_Register_Info.getUserPrimaryKey(userName).toString());
				utilities.Redirects.gotoCardTradingServlet(response);	
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
