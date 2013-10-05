
package ua.bionic.turko.db;

import java.sql.Connection;
import java.sql.SQLException;

import ua.bionic.turko.driver.Driver;


public class Database {

    private String url;
    private String login;
    private String password;
    private String className;
    private Connection conn;
    private final Driver driver;

    
    public Database(Driver driver) throws SQLException {
      
      this.driver = driver;
      this.className = DBResources.getDriverClassName();
      this.url = DBResources.getConnectionUrl();
      this.login = DBResources.getLogin();
      this.password = DBResources.getPassword();
    }
    	

    public void connect() throws SQLException {
    	this.conn = getDriver().getConnection(url, login, password);
    }

    public void disconnect() throws SQLException {
//    	if (pstmt != null)
//    		pstmt.close();                
    	if (conn != null)
    		conn.close();   
    }
    	
    public boolean validate() {
        try {
            conn.getAutoCommit();
        } catch (SQLException e) {
//            logger.warn(e, e);
            return false;
        }
        return true;
    }

    
    public Connection getConnection() {
        return conn;
    }
    
    
    public Driver getDriver() {
        return driver;
    }
}
