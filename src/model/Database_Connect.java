package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database_Connect {
	public static String user = "defalcot2", pass = "Flashban9";
	
	public static Connection Connect2LocalDB() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/csci_441_cards", Database_Connect.user, Database_Connect.pass);

            return con;
		} catch(ClassNotFoundException e) {
			Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2LocalDB" + " ClassNotFoundException", e);
		} catch( SQLException e ) {
			Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2LocalDB" + " SQLException", e);
		}
		if( con == null )
			con = Connect2RemoteDB();
		return con;
	}
	public static Connection Connect2RemoteDB() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://35.194.81.140:3306/csci_441_cards", Database_Connect.user, Database_Connect.pass);

            return con;
		} catch(ClassNotFoundException e) {
			Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2RemoteDB" + " ClassNotFoundException", e);
		} catch( SQLException e ) {
			Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2RemoteDB" + " SQLException", e);
		}
		return con;
	}
}
