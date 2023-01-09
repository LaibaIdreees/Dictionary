package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
private static DbConnection instance;
private Connection con;
private  String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
private String url = "jdbc:mysql://localhost:3306/";
private String db = "dictionary";

private DbConnection() {
	 try { 
		 try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		  con = DriverManager.getConnection(url+db+unicode,"root", "");
	 }
		catch (SQLException ex) {
	        System.out.println(ex.getMessage());
		}
}



public  Connection getConnection() {
	return con;
}

public static DbConnection getInstance() {
    if (instance == null) {
        instance = new DbConnection();
    } else
		try {
			if (instance.getConnection().isClosed()) {
			    instance = new DbConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

    return instance;
}
}


