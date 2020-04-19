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
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		boolean beanCheck = session.getAttribute("bean") != null;
		
		if (beanCheck == false){
			utilities.Redirects.gotoLogin(response);
			return;
		} else {
			SessionBean beanObject = (SessionBean) session.getAttribute("bean");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (DataTools.NullOrEmpty(username) || DataTools.NullOrEmpty(password)) {
				beanObject.setMessage("*****   Do not leave fields empty   *****");
				utilities.Redirects.gotoLogin(response);
				return;
			} else if (!model.User_Login_Register_Info.isUserNamePasswordCorrect(username, password)){
				beanObject.setMessage("*****   Invalid Login   *****");
				utilities.Redirects.gotoLogin(response);
				return;
			} else {
				beanObject.setUserPrimaryKey(model.User_Login_Register_Info.getUserPrimaryKey(username).toString());
				beanObject.setUserName(username);
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
