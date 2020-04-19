package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SessionBean;

public class User_Collection_Info {
	public static void enterCardIntoUserCollection(String user_primary_key, String card_primary_key, String amount) {
		Connection con = Database_Connect.Connect2LocalDB();
		
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM card_collections where card_primary_key = ? and user_primary_key = ?");
            statement.setString(1, card_primary_key);
            statement.setString(2, user_primary_key);
            
            ResultSet rs = statement.executeQuery();
            if (!rs.next()){
            	statement = con.prepareStatement("INSERT INTO card_collections (user_primary_key, card_primary_key, amount_owned) VALUES (?,?,?)");
            	statement.setString(1, user_primary_key);
            	statement.setString(2, card_primary_key);
            	statement.setString(3, amount);
            
            	statement.executeUpdate();
            } else
            	updateCardInUserCollection(user_primary_key, card_primary_key, amount);
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   "; " + e.getMessage(), e);
        }
		
		try {
			con.close();
		} catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, e);
        }
	}
	public static void updateCardInUserCollection(String user_primary_key, String card_primary_key, String amount) {
		Connection con = Database_Connect.Connect2LocalDB();
		
		try {
            PreparedStatement statement = con.prepareStatement("UPDATE card_collections set amount_owned = ? where card_primary_key = ? and user_primary_key = ?");
            statement.setString(1, amount);
            statement.setString(2, card_primary_key);
            statement.setString(3, user_primary_key);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   "; " + e.getMessage(), e);
        }
		
		try {
			con.close();
		} catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, e);
        }
	}
	public static void getUserCardCollection(HttpServletRequest request, HttpServletResponse response) {
		Connection con = Database_Connect.Connect2LocalDB();
		
		try {
            HttpSession session = request.getSession(false);
    		boolean beanCheck = session.getAttribute("bean") != null;
    		
    		if (beanCheck == false){
    			utilities.Redirects.gotoLogout(response);
    			return;
    		} else {
    			SessionBean beanObject = (SessionBean) session.getAttribute("bean");
    			
    			PreparedStatement statement = con.prepareStatement("SELECT * FROM card_collections where user_primary_key = ?");
            	statement.setString(1, beanObject.getUserPrimaryKey());
            
	            ResultSet rs = statement.executeQuery();
	            while (rs.next()){
	            	beanObject.setCardCollection(rs.getString("primary_key"), rs.getString("card_primary_key"), rs.getString("amount_owned") );
	            }
    		}
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   "; " + e.getMessage(), e);
        }
		
		try {
			con.close();
		} catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, e);
        }
	}
}
