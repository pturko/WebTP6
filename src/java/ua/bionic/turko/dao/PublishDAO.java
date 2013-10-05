package ua.bionic.turko.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import ua.bionic.turko.daointerfaces.IPublishDAO;
import ua.bionic.turko.db.Query;
   

public class PublishDAO implements IPublishDAO {

    public static final Logger LOG=Logger.getLogger(PublishDAO.class.getName());
    
    @Override
    public String getName(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("PublishDAO getName from id");
            rSet = Query.getResultSetFromQuery("select * from publication where publ_id='"+id+"'");
            return rSet.getString("name");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());            
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
        }
    }

    @Override
    public String getAuthId(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("PublishDAO getAuthId from id");
            rSet = Query.getResultSetFromQuery("select * from publication where publ_id='"+id+"'");
            //rSet = getResultSetFromQuery("select * from users where login='root'");
//            System.out.println("rs: "+rSet);
//            System.out.println(rSet.getString("auth_id"));
            return rSet.getString("auth_id");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());   
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
        }
    }
    
    
    @Override
    public String getGenreId(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("PublishDAO getGenreId from id");
            rSet = Query.getResultSetFromQuery("select * from publication where publ_id='"+id+"'");
            //rSet = getResultSetFromQuery("select * from users where login='root'");
//            System.out.println("rs: "+rSet);
//            System.out.println(rSet.getString("GENRE_ID"));
            return rSet.getString("GENRE_ID");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());   
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
        }
    }
     

    @Override
    public String getPrice(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("PublishDAO getPrice from id");
            rSet = Query.getResultSetFromQuery("select * from publication where publ_id='"+id+"'");
            //rSet = getResultSetFromQuery("select * from users where login='root'");
//            System.out.println("rs: "+rSet);
//            System.out.println(rSet.getString("price"));
            return rSet.getString("price");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());   
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
        }
    }

    @Override
    public String getDescription(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("PublishDAO getDescription from id");
            rSet = Query.getResultSetFromQuery("select * from publication where publ_id='"+id+"'");
            //rSet = getResultSetFromQuery("select * from users where login='root'");
//            System.out.println("rs: "+rSet);
//            System.out.println(rSet.getString("DESCRIPTION"));
            return rSet.getString("DESCRIPTION");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());   
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
        }
        
    }

    

    @Override
    public Map<String, String> getSubscriptions() {
        ResultSet rSet = null;
        Map<String, String> list = new HashMap<String, String>();
        
        try {
            LOG.info("PublishDAO getSubscriptions from id");
            rSet = Query.getResult("select * from publication");
           
            String PUBL_ID;
            String  NAME;                        
            while (rSet.next()) {
			PUBL_ID   = rSet.getString("PUBL_ID");		
			NAME      = rSet.getString("NAME");
//                        System.out.println("******************************");
//			System.out.println("PUBL_ID: " + PUBL_ID);
//			System.out.println("NAME : " + NAME  );
//			System.out.println("******************************");
                        list.put(PUBL_ID, NAME);
		}

            return list;
        } catch (SQLException ex) {
            LOG.warn(ex.toString());   
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
        }
    }

    @Override
    public String getImagePath(String id) {
         ResultSet rSet = null;
        
        try {
            LOG.info("PublishDAO getImagePath from id");
            rSet = Query.getResultSetFromQuery("select * from publication where publ_id='"+id+"'");
            //rSet = getResultSetFromQuery("select * from users where login='root'");

            return rSet.getString("image_path");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());   
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
        }        
    }   

    @Override
    public int getPublicationCount() {
         ResultSet rSet = null;
        
        try {            
            LOG.info("PublishDAO getPublicationCount");
            rSet = Query.getResultSetFromQuery("select count(*) as ccount from publication");

            return rSet.getInt("ccount");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());   
            return 0;
        } finally {
            if (rSet != null)
                rSet = null;
        }
    }

    @Override
    public void addPublication(String name, String price, String description, String image) {
        ResultSet rSet = null;
        LOG.info("PublishDAO addPublication");
        int count = getPublicationCount()+1;
        LOG.info("Publication count: " + count);
        
        Query.queryUpdate("insert into publication values('"+count+"','"+name+"','1','1','"+price+"','"+description+"','"+image+"')");
    }   
    
    
    
    @Override
    public void updatePublication(String id, String name, String price, String description, String image) {
        ResultSet rSet = null;
        LOG.info("PublishDAO updatePublication for id=" + id);
     
        Query.queryUpdate("update publication set name='"+name+"', price='"+price+"', description='"+description+"', image_path='"+image+"' where publ_id='"+id+"'");
    }
    
    
    @Override
    public void deletePublication(String id) {
        ResultSet rSet = null;
        LOG.info("PublishDAO deletePublication");        
        
        Query.queryUpdate("delete from publication where publ_id='"+id+"'");
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
         if(!(obj instanceof PublishDAO)) return false;
         
          PublishDAO obj1 = (PublishDAO) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }
    
}
