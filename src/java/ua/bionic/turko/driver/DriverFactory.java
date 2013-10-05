
package ua.bionic.turko.driver;

import ua.bionic.turko.db.DBTypes;


public final class DriverFactory {

    private static Driver driver = null;

    
    public static Driver createDriver(final DBTypes dbType) throws ClassNotFoundException {
        switch (dbType) {
            case MYSQL:
                driver = new DriverMysql();
                break;
            case ORACLE:
                throw new ClassNotFoundException("Unknown DB type: " + dbType);
            default:
                driver = new DriverMysql();
        }
        
        loadDriver(driver);

        return driver;
    }
    
    public static void loadDriver(Driver driver) {
    	try {
			driver.loadDriverClass(driver.getDriverClass());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    

}
