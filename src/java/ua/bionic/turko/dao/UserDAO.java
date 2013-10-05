package ua.bionic.turko.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.bionic.turko.daointerfaces.IUserDAO;
import ua.bionic.turko.db.Query;

public class UserDAO implements IUserDAO {

    public static final Logger LOG=Logger.getLogger(UserDAO.class.getName());
    
    @Override
    public String getLogin(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("UserDAO getLogin from id");
            rSet = Query.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            return rSet.getString("login");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public String getName(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("UserDAO getName from id");
            rSet = Query.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            return rSet.getString("name");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public String getSurname(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("UserDAO getSurname from id");
            rSet = Query.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            return rSet.getString("Surname");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }


    @Override
    public String getLastLogin(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("UserDAO getLastLogin from id");
            rSet = Query.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            return rSet.getString("Last_Login");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public String getIdByLogin(String login) {
        ResultSet rSet = null;
        
        try {
            LOG.info("UserDAO getIdByLogin from login");
            rSet = Query.getResultSetFromQuery("select * from users where login='"+login+"'");
            return rSet.getString("user_id");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public UserType getUserType(String id) {
         ResultSet rSet = null;
        
        try {
            LOG.info("UserDAO getUserType from id");
            rSet = Query.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            String userType = rSet.getString("User_Type");
            
            if (userType.equals("0")) {
                return UserType.ADMIN;
            }
            else {
                return UserType.USER;
            }
            
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public String getBalance(String id) {
        ResultSet rSet = null;
        
        try {
            LOG.info("UserDAO getBalance from id");
            rSet = Query.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            return rSet.getString("balance");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public void withdraw(String id, int balance) {

            LOG.info("USERDAO withdraw from id and balance");
            Query.queryUpdate("update users set balance='"+balance+"' where user_id='"+id+"'");

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
         if(!(obj instanceof UserDAO)) return false;
         
          UserDAO obj1 = (UserDAO) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }
    
}