package ua.bionic.turko.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.bionic.turko.daointerfaces.IGenreDAO;
import ua.bionic.turko.db.Query;


public class GenreDAO implements IGenreDAO {

    public static final Logger LOG=Logger.getLogger(GenreDAO.class.getName());

    @Override
    public String getGenre(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("GenreDAO getGenre from id");
            rSet = Query.getResultSetFromQuery("select * from genre where genre_id='"+id+"'");
            return rSet.getString("title");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
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
         if(!(obj instanceof GenreDAO)) return false;
         
          GenreDAO obj1 = (GenreDAO) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }          

}