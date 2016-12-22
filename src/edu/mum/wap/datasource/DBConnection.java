package edu.mum.wap.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DBConnection {

	
	public Connection conn;
    public static DBConnection db;
    private DBConnection() throws SQLException {
    	System.out.println("Called from localhost");
        String url= "jdbc:mysql://localhost/";
        String dbName = "carpooling";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try {
            Class.forName(driver).newInstance();
           
            
        }
        catch (Exception sqle) {
        	System.out.println("No driver found");
            sqle.printStackTrace();
        }
        this.conn = DriverManager.getConnection(url+dbName,userName,password);

    }
    
    
    public static synchronized DBConnection getConnection() throws SQLException {
        if ( db == null ) {
            db = new DBConnection();
        }
        return db;
 
    }
}
