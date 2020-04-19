package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User_Login_Register_Info {
	public static boolean isUserInDB( String userName ) {
		Connection con = Database_Connect.Connect2LocalDB();
		String userFromDB = "";
		int userCount = 0;
		
		try {
            PreparedStatement count = con.prepareStatement("SELECT user_name FROM usernamespasswords");
            ResultSet rs = count.executeQuery();
            while (rs.next()){
            	userFromDB = rs.getString("user_name");
					if (userFromDB.equalsIgnoreCase(userName))
						userCount++;
            }
 
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   "; " + e.getMessage(), e);
        }
		
		try {
			con.close();
		} catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, e);
        }
		if( userCount == 0 )
			return false;
		return true;
	}
	public static boolean isUserNamePasswordCorrect( String userName, String password ) {
		Connection con = Database_Connect.Connect2LocalDB();
		int userCount = 0;
		
		try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM usernamespasswords where user_name = ? and user_password COLLATE latin1_general_cs = ?");
            statement.setString(1, userName);
            statement.setString(2, password);
            
            ResultSet rs = statement.executeQuery();
            while( rs.next() )
            	userCount++;
 
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   "; " + e.getMessage(), e);
        }
		
		try {
			con.close();
		} catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, e);
        }
		if( userCount == 0 )
			return false;
		return true;
	}
	public static Integer getUserPrimaryKey(String userName) {
        Connection con = Database_Connect.Connect2LocalDB();
        int userKey = 0;

        try {
            PreparedStatement key = con.prepareStatement("SELECT user_primary_key FROM usernamespasswords WHERE user_name = ?");
            key.setString(1, userName);
            
            ResultSet resultUserKey = key.executeQuery();
            resultUserKey.next();
            userKey = resultUserKey.getInt(1);
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName(), e);
        }
        
        try {
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, e);
        }
        return userKey;
    }
	public static void addUserToDB(String userName, String password) {
    	Connection con = Database_Connect.Connect2LocalDB();
        
        try {
            PreparedStatement prep = con.prepareStatement("INSERT INTO usernamespasswords (user_name,user_password) VALUES (?,?)");
            prep.setString(1, userName);
            prep.setString(2, password);
            
            prep.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() + ".addUserCar ", e);
        }
        
        try {
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
