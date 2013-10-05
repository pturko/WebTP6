
package ua.bionic.turko.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.bionic.turko.db.DBResources;
import ua.bionic.turko.db.DBTypes;
import ua.bionic.turko.db.Database;
import ua.bionic.turko.db.Query;
import ua.bionic.turko.driver.Driver;
import ua.bionic.turko.driver.DriverFactory;


public class LoginLogic {

    public static final Logger LOG=Logger.getLogger(LoginLogic.class.getName());
    
    public static boolean checkLogin(String login, String password) throws ClassNotFoundException, SQLException {
        
        LOG.info("checkLogin");
       
        ResultSet rs = null;
        
       try {            
           
        String queryUsers = "select * from users where login='"+login+"' and password='"+password+"'";
        
        DBResources.getInstance(); //getConnectResources();
    	
    	Driver dbDriver = DriverFactory.createDriver(DBTypes.MYSQL);
    	    	
    	Database database = new Database(dbDriver); //Connection
    	database.connect(); //conn = getConnection(url, login, password)
    	
    	Query query = new Query(database);
    	query.createStatement(); //createStatement()
    	
    	rs = query.executeQuery(queryUsers);
    	
         return rs.next();       
            } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return false;
        } catch (ClassNotFoundException ex) {
            LOG.warn(ex.toString());
            return false;
        } finally {
            if (rs != null) {
                   rs.close();
                        }
        }
    }
    
    public int hashCode() {
        long ht = this.getTime();
        return (int) ht ^ (int) (ht >> 32);
    }
	
	
    public String toString() {
	return getClass().getName();
    }
	

    public boolean equals(Object obj) {
         if (this == obj) return true;
		            
         if(obj == null) return false;

         //проверяет является ли obj объектом App
         if(!(obj instanceof LoginLogic)) return false;
         
          LoginLogic obj1 = (LoginLogic) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }
    
}
