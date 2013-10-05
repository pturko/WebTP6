
package ua.bionic.turko.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import ua.bionic.turko.driver.DriverFactory;
import ua.bionic.turko.logic.LoginLogic;

public final class Query {
    
    private Database database;
    private Statement statement;
    private String lastError;
    public static final Logger LOG=Logger.getLogger(Query.class.getName());    
    
    public static ResultSet getResultSetFromQuery(String queryString) {
             
       ResultSet rs = null; 
        
       try { 
           
        LOG.info("Execute query: "+queryString);
           
//        String queryUsers = "select * from users where login='"+login+"' and password='"+password+"'";
        
        DBResources.getInstance(); //getInstance()getConnectResources();
    	
    	ua.bionic.turko.driver.Driver dbDriver = DriverFactory.createDriver(DBTypes.MYSQL);
    	    	
    	Database database = new Database(dbDriver); //Connection
    	database.connect(); //conn = getConnection(url, login, password)
    	
    	Query query = new Query(database);
    	query.createStatement(); //createStatement()
    	
    	rs = query.executeQuery(queryString);
    	rs.next();

//        System.out.println(">>>>> DB >>>>>");
        
         return rs;       
            } catch (SQLException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
           LOG.info("query finally block");
        }   
            
    } 
            
    
     public static ResultSet getResult(String queryString) {
             
       ResultSet rs = null; 
        
       try { 
           
        LOG.info("Execute query: "+queryString);
        
        DBResources.getInstance(); //getInstance()getConnectResources();
    	
    	ua.bionic.turko.driver.Driver dbDriver = DriverFactory.createDriver(DBTypes.MYSQL);
    	    	
    	Database database = new Database(dbDriver); //Connection
    	database.connect(); //conn = getConnection(url, login, password)
    	
    	Query query = new Query(database);
    	query.createStatement(); //createStatement()
    	
    	rs = query.executeQuery(queryString);

//        System.out.println(">>>>> DB >>>>>");

         return rs;       
            } catch (SQLException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
           LOG.info("query finally block");
        }
   
            
    } 
    
    
     public static void queryUpdate(String queryString) {
             
       //ResultSet rs = null; 
        
       try { 
           
        LOG.info("Insert query: "+queryString);
        
        DBResources.getInstance(); //getInstance()getConnectResources();
    	
    	ua.bionic.turko.driver.Driver dbDriver = DriverFactory.createDriver(DBTypes.MYSQL);
    	    	
    	Database database = new Database(dbDriver); //Connection
    	database.connect(); //conn = getConnection(url, login, password)
    	
    	Query query = new Query(database);
    	query.createStatement(); //createStatement()

    	query.executeUpdate(queryString);

//        System.out.println(">>>>> DB INSERT >>>>>");

      
            } catch (SQLException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(LoginLogic.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
           LOG.info("query finally block");
        }
               
    }     
     
        
    public Query(Database database) throws SQLException {
        this.database = database;
//        this.stm = this.createStatemet(database.getConnection());
    }

    public Statement createStatement() throws SQLException {
    	statement = database.getConnection().createStatement(); // Singleton (!)
    	return statement;
    }

    public String getLastError() {
        return this.lastError;
    }

    public ResultSet executeQuery(String sql) throws SQLException {

        // If connection was broken and we can not restore it we will fail.
     //   checkConnectionState();
        ResultSet rs = this.statement.executeQuery(sql);
        rs.setFetchSize(database.getDriver().getFetchSize());
        return rs;

    }

    public int executeUpdate(String sql) throws SQLException {

        // If connection was broken and we can not restore it we will fail.
      //  checkConnectionState();
        return this.statement.executeUpdate(sql);

    }

    
    public void disconnect() throws SQLException {
        if (database!=null) {
            database.disconnect();
        }    
        
        if (statement!=null) {
            statement.close();
        }
    }    
    
    public boolean close() {
        try {
            this.statement.close();
        } catch (Exception e) {
//            logger.error(e, e);
            return false;
        }
        return true;
    }

    public boolean isClosed() throws Exception {
        return this.statement.isClosed();
    }

    public String processStringField(String fieldValue) {
        String thePattern = "\\\\";
        Pattern pattern = Pattern.compile(thePattern);
        Matcher matcher = pattern.matcher(fieldValue);
        fieldValue = matcher.replaceAll("\\\\\\\\");

        thePattern = "'";
        pattern = Pattern.compile(thePattern);
        matcher = pattern.matcher(fieldValue);
        fieldValue = matcher.replaceAll("\\\\'");
        return fieldValue;
    }

}
