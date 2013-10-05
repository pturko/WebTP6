/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.turko.manager;

import java.util.ResourceBundle;

/**
 *
 * @author Maksym
 */
public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    
    private static final String BUNDLE_NAME = "ua.bionic.turko.manager.config";
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";    
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String LIST_PAGE_PATH = "LIST_PAGE_PATH";
    public static final String INFO_PAGE_PATH = "INFO_PAGE_PATH";
    public static final String INFO_ADMIN_PAGE_PATH = "INFO_ADMIN_PAGE_PATH";
    public static final String SUBSCRIBE_PAGE_PATH = "SUBSCRIBE_PAGE_PATH";
    public static final String ACCOUNT_PAGE_PATH = "ACCOUNT_PAGE_PATH";
    public static final String ACCOUNT_ADMIN_PAGE_PATH = "ACCOUNT_ADMIN_PAGE_PATH";
    public static final String ORDERS_PAGE_PATH = "ORDERS_PAGE_PATH";
    public static final String HISTORY_PAGE_PATH = "HISTORY_PAGE_PATH";
    public static final String MAIN_PAGE_ADMIN_PATH = "MAIN_PAGE_ADMIN_PATH";
    public static final String LIST_PAGE_ADMIN_PATH = "LIST_PAGE_ADMIN_PATH";    
    public static final String BUY_PAGE_PATH = "BUY_PAGE_PATH";
    public static final String BUY_ERROR_PAGE_PATH = "BUY_ERROR_PAGE_PATH";    
    public static final String ADD_PUBLICATION_PAGE_PATH = "ADD_PUBLICATION_PAGE_PATH";
    public static final String ADD_PUBLICATION_CONFIRM_PAGE_PATH = "ADD_PUBLICATION_CONFIRM_PAGE_PATH";
    public static final String DELETE_PUBLICATION_PAGE_PATH = "DELETE_PUBLICATION_PAGE_PATH";
    public static final String DELETE_PUBLICATION_CONFIRM_PAGE_PATH = "DELETE_PUBLICATION_CONFIRM_PAGE_PATH";
    public static final String EDIT_PUBLICATION_PAGE_PATH = "EDIT_PUBLICATION_PAGE_PATH";
    public static final String EDIT_PUBLICATION_CONFIRM_PAGE_PATH = "EDIT_PUBLICATION_CONFIRM_PAGE_PATH";
    public static final String EDIT_PUBLICATION_ERROR_PATH = "EDIT_PUBLICATION_ERROR_PATH";
    public static final String LANGUAGE_PAGE_PATH = "LANGUAGE_PAGE_PATH";
    public static final String ADD_PUBLICATION_ERROR_PATH = "ADD_PUBLICATION_ERROR_PATH";
    public static final String LANGUAGE_ADMIN_PAGE_PATH = "LANGUAGE_ADMIN_PAGE_PATH";
    public static final String ORDERS_CANCEL_PAGE_PATH = "ORDERS_CANCEL_PAGE_PATH";
        
    
    public static ConfigurationManager getInstance(){
        if (instance == null){
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
    
    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}
