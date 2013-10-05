
package ua.bionic.turko.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import static ua.bionic.turko.dao.AuthorDAO.LOG;
import ua.bionic.turko.db.DBResources;
import ua.bionic.turko.db.DBTypes;
import ua.bionic.turko.db.Database;
import ua.bionic.turko.db.Query;
import ua.bionic.turko.driver.Driver;
import ua.bionic.turko.driver.DriverFactory;


public class InfoLogic {
     public static final Logger LOG=Logger.getLogger(InfoLogic.class.getName());    
    
    public static boolean getPublicationName(String id) throws ClassNotFoundException, SQLException {
        
      ResultSet rs = null;
        
       try { 
        String queryUsers = "select * from publication where subs_id='"+id+"'";
        
        DBResources.getInstance();
    	
    	Driver dbDriver = DriverFactory.createDriver(DBTypes.MYSQL);
    	    	
    	Database database = new Database(dbDriver); //Connection
    	database.connect(); //conn = getConnection(url, login, password)
    	
    	Query query = new Query(database);
    	query.createStatement(); //createStatement()
    	
    	rs = query.executeQuery(queryUsers);
    	
    	//Statement st = database.getConnection().createStatement();		
		//ResultSet rs = st.executeQuery(query);

        System.out.println(">>>>> DB >>>>>");
        
		String NAME;		
		while (rs.next()) {
			NAME  = rs.getString("NAME ");
//                        System.out.println("******************************");
//			System.out.println("NAME : " + NAME );
//			System.out.println("******************************");
		}
         return rs.next();       
            } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.warn(ex.toString());
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
         if(!(obj instanceof InfoLogic)) return false;
         
          InfoLogic obj1 = (InfoLogic) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }
    
}
