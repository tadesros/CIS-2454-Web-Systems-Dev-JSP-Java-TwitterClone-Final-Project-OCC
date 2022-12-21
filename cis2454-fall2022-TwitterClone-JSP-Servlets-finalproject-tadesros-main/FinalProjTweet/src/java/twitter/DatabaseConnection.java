
package twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
      //Force java to load the driver
        Class.forName("com.mysql.jdbc.Driver");
        
        //driver://url:port/ database
        String dbURL = "jdbc:mysql://localhost:3306/twitter-winter2022";
        
        //This is not best form = don't have a better way
        String username = "twitterUser";
        String password = "test";
        
        //Connection
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        
        return connection;
       
    }
       
}
