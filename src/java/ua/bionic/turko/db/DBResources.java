package ua.bionic.turko.db;

import java.util.Enumeration;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class DBResources {

	private static ResourceBundle rb = null;	
        public static final Logger LOG=Logger.getLogger(ResourceBundle.class.getName());
      
	public static void getInstance() { // Singleton
            LOG.info("load ResourceBundle"); //create ResourceBundle if not exists
            if (rb == null){
                rb = ResourceBundle.getBundle("MyProp");
            }
        }	
	
	public static void viewProrerties() {
		Enumeration <String> keys = rb.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = rb.getString(key);
//			System.out.println(key + ": " + value);
		}
	}
	
	
	public static String getLogin() {
		return rb.getString("login");
	}
	
	public static String getPassword() {
		return rb.getString("password");
	}	
	
	public static String getDriverClassName() {
		return rb.getString("driverClassName");
	}
	
	public static String getConnectionUrl() {
		return rb.getString("connectionUrl");
	}
	

}
