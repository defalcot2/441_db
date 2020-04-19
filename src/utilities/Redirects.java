package utilities;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirects {
	public static void gotoLogin(HttpServletResponse response) {
        try {
            response.sendRedirect("login.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for login.jsp did not work", ex);
        }
    }
	public static void gotoCardTradingServlet(HttpServletResponse response) {
        try {
            response.sendRedirect("CardTradingControllerServlet");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for CardTradingControllerServlet did not work", ex);
        }
    }
	public static void gotoCardTrader(HttpServletResponse response) {
        try {
            response.sendRedirect("cardTrader.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for cardTrader.jsp did not work", ex);
        }
    }
	public static void gotoRegistration(HttpServletResponse response) {
        try {
            response.sendRedirect("registration.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for registration.jsp did not work", ex);
        }
    }
	public static void gotoTransactionDetails(HttpServletResponse response) {
        try {
            response.sendRedirect("transactionDetails.jsp");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for transactionDetails.jsp did not work", ex);
        }
    }
	public static void gotoLogout(HttpServletResponse response) {
        try {
            response.sendRedirect("LogOutControllerServlet");
        	return;
        } catch (IOException ex) {
            Logger.getLogger(Redirects.class.getName()).log(Level.SEVERE, " ug, the Redirect for LogOutControllerServlet did not work", ex);
        }
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
