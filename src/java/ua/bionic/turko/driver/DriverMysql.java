
package ua.bionic.turko.driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.bionic.turko.dao.AuthorDAO;
import ua.bionic.turko.db.DBResources;


public class DriverMysql extends Driver{
	
    private static final String DRIVER_CLASS = DBResources.getDriverClassName(); //"org.gjt.mm.mysql.Driver";
    public static final Logger LOG=Logger.getLogger(AuthorDAO.class.getName());

    public DriverMysql() throws ClassNotFoundException {
        super();    	
    }


    @Override
    protected boolean registerDriver() {
        try{
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver()); //loading driver mySQL
        } catch (SQLException e) {
            LOG.warn("error in registerDriver");
            return false;
        }
        return true;
    }

    
    @Override
    protected String getDriverClass() {
        return DRIVER_CLASS;
    }
    
}
