
package ua.bionic.turko.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import ua.bionic.turko.daointerfaces.ISubscriptionDAO;
import ua.bionic.turko.db.Query;


public class SubscriptionDAO implements ISubscriptionDAO {
    
    public static final Logger LOG=Logger.getLogger(SubscriptionDAO.class.getName());


    @Override
    public void addSubscription(String publId, String userId, String cost) {
        ResultSet rSet = null;
        
        LOG.info("SubscriptionDAO addSubscription from id");
                
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        
        LOG.info("get current date"); //get current date
        String dateStr = dateFormat.format(cal.getTime());
        
        int count = getSubscriptionCount()+1;
        LOG.info("Subscription count: " + count);

        Query.queryUpdate("insert into subscription values('"+count+"','"+publId+"','"+userId+"','0','"+cost+"','"+dateStr+"')");

    }
    
    @Override
    public void cancelAllSubscription(String userId) {
        ResultSet rSet = null;
        
        LOG.info("SubscriptionDAO cancelAllSubscription from userId");
          
        Query.queryUpdate("update subscription set pay='2' where pay='0' and user_id='"+userId+"'");

    }
     
     
    @Override
    public int getSubscriptionCount() {
        ResultSet rSet = null;
        
        try {
            LOG.info("SubscriptionDAO getSubscriptionCount from id");
            rSet = Query.getResultSetFromQuery("select count(*) as ccount from subscription");

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
    public Map<String, String> getHistory(String userId) {
        ResultSet rSet = null;
        Map<String, String> list = new HashMap<String, String>();
        
        try {
            LOG.info("SubscriptionDAO getHistory from id");
            rSet = Query.getResult("select * from subscription where user_id='2' and pay='1'");
           
            String PUBL_ID;
            String DATE;                        
            while (rSet.next()) {
			PUBL_ID   = rSet.getString("PUBL_ID");		
			DATE      = rSet.getString("DATE");
//                        System.out.println("******************************");
//			System.out.println("PUBL_ID: " + PUBL_ID);
//			System.out.println("DATE : " + DATE);
//			System.out.println("******************************");
                        list.put(PUBL_ID, DATE);
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
    public Map<String, String> getSubscriptions(String userId) {
        ResultSet rSet = null;
        Map<String, String> list = new HashMap<String, String>();
        
        LOG.info("SubscriptionDAO getSubscriptions from id");
        
        try {
            rSet = Query.getResult("select * from subscription where user_id='2' and pay='0'");
           
            String PUBL_ID;
            String PRICE;                        
            while (rSet.next()) {
			PUBL_ID   = rSet.getString("PUBL_ID");		
			PRICE      = rSet.getString("PRICE");
//                        System.out.println("******************************");
//			System.out.println("PUBL_ID: " + PUBL_ID);
//			System.out.println("PRICE : " + PRICE);
//			System.out.println("******************************");
                        list.put(PUBL_ID, PRICE);
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
    public int getBuySumm(String userId) {
        ResultSet rSet = null;
        Map<String, String> list = new HashMap<String, String>();
        
        LOG.info("SubscriptionDAO getBuySumm from id");
        
        try {
            rSet = Query.getResult("select * from subscription where user_id='"+userId+"' and pay='0'");
           
            String PRICE;
            int summ = 0;
            while (rSet.next()) {			
			PRICE      = rSet.getString("PRICE");
//                        System.out.println("******************************");
//			System.out.println("PRICE : " + PRICE);
//			System.out.println("******************************");
                        summ = summ + Integer.parseInt(PRICE);
		}

            return summ;
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return 0;
        } finally {
            if (rSet != null)
                rSet = null;
        }
    }

    @Override
    public void setBuyFlag(String userId) {
         Query.queryUpdate("update subscription set pay='1' where pay='0' and user_id='"+userId+"'");
    }

    @Override
    public boolean findSomeSubscriptionforUser(String userId, String publId) {
        ResultSet rSet = null;
        Boolean bool = false;
        
        LOG.info("SubscriptionDAO findSomeSubscriptionforUser from id");
        
        try {
            rSet = Query.getResult("select * from subscription where pay='0' and publ_id='"+publId+"' and user_id='"+userId+"'");
           
            while (rSet.next()) {                
                bool = true;
		}            
            
            if (bool==true) {
                LOG.info("This subscription is exists: publ="+publId+" user="+userId);
            }
            else {
                LOG.info("This subscription is NOT exists: publ="+publId+" user="+userId);
            }

            return bool;    
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return true;
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
         if(!(obj instanceof SubscriptionDAO)) return false;
         
          SubscriptionDAO obj1 = (SubscriptionDAO) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }
    
}
