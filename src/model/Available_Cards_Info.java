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

public class Available_Cards_Info {
	public static void insertCardsIntoDB() {
		Connection con = Database_Connect.Connect2LocalDB();
		String[][] entries = {{ "1983 Topps Tony Gwynn RC", "482", "75.21" }, {"1984 Fleer Update Roger Clemens", "27", "120.00"}, {"1983 Topps Ryne Sandberg", "83", "20.00"}, {"1984 Donruss Don Mattingly", "248", "40"}, {"1984 Donruss Joe Carter RC", "41", "8"}, {"1984 Donruss Darryl Strawberry", "68", "12"}};
		
		try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM available_cards");
            ResultSet rs = statement.executeQuery();
            if( !rs.next() ) {
            	for(int i = 0; i < entries.length; i++ ) {
            		statement = con.prepareStatement("INSERT INTO available_cards (card_name,card_number,card_value) VALUES (?,?,?)");
            		for( int a = 0; a < entries[i].length; a++ )
            			statement.setString(a+1, entries[i][a]);
            		statement.executeUpdate();
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
	
	public static void cardsInDB(HttpServletRequest request, HttpServletResponse response) {
		Connection con = Database_Connect.Connect2LocalDB();
		
		try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM available_cards");
            HttpSession session = request.getSession(false);
    		boolean beanCheck = session.getAttribute("bean") != null;
    		
    		if (beanCheck == false){
    			utilities.Redirects.gotoLogout(response);
    			return;
    		} else {
    			SessionBean beanObject = (SessionBean) session.getAttribute("bean");
            	
	            ResultSet rs = statement.executeQuery();
	            while (rs.next()){
	            	beanObject.setCardsForSale(rs.getString("primary_key"), rs.getString("card_name"), rs.getString("card_number"), rs.getString("card_value") );
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
