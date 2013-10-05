
package ua.bionic.turko.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Driver {

    private final int fetchSize;

    protected Driver() throws ClassNotFoundException {
        this.fetchSize = 20;
    }

    protected abstract String getDriverClass();
    protected abstract boolean registerDriver();

    
    public void loadDriverClass(final String driverClassName) throws ClassNotFoundException{
        Class.forName(driverClassName);
    }

    public Connection getConnection(final String url, final String login, final String password) throws SQLException{
        return DriverManager.getConnection(url, login, password);
    }

//    public Connection restoreConnection(Database conn) throws SQLException{
//            if (conn.validate()) {
//                return conn.getConnection();
//            }
//            return getConnection(conn.getUrl(), conn.getLogin(), conn.getPassword());
//    }

    public int getFetchSize(){
        return fetchSize;
    }
}
