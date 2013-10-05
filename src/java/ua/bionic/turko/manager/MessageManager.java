
package ua.bionic.turko.manager;

import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import ua.bionic.turko.commands.LoginCommand;
import static ua.bionic.turko.commands.LoginCommand.LOG;


public class MessageManager {
    private static MessageManager instanceEN, instanceRU;
    private ResourceBundle resourceBundle;
    
    private static final String BUNDLE_NAME_RU = "ua.bionic.turko.manager.messages_ru";
    private static final String BUNDLE_NAME_EN = "ua.bionic.turko.manager.messages_en";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String RETURN_LINK_MESSAGE = "RETURN_LINK_MESSAGE";
    public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
    public static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE";     
    public static final String LOG_ON_MESSAGE = "LOG_ON_MESSAGE"; 
    public static final String LOGIN_MESSAGE = "LOGIN_MESSAGE"; 
    public static final String PASSWORD_MESSAGE = "PASSWORD_MESSAGE"; 
    public static final String SUBMIT_MESSAGE = "SUBMIT_MESSAGE";     
    public static final String MAIN_MESSAGE = "MAIN_MESSAGE"; 
    public static final String HELLO_MESSAGE = "HELLO_MESSAGE"; 
    public static final String PUBLICATIONS_MESSAGE = "PUBLICATIONS_MESSAGE"; 
    public static final String ACCOUNT_MESSAGE = "ACCOUNT_MESSAGE"; 
    public static final String ORDERS_MESSAGE = "ORDERS_MESSAGE"; 
    public static final String HISTORY_MESSAGE = "HISTORY_MESSAGE"; 
    public static final String EXIT_MESSAGE = "EXIT_MESSAGE";     
    public static final String ID_MESSAGE = "ID_MESSAGE"; 
    public static final String NAME_MESSAGE = "NAME_MESSAGE"; 
    public static final String SURNAME_MESSAGE = "SURNAME_MESSAGE"; 
    public static final String USERTYPE_MESSAGE = "USERTYPE_MESSAGE"; 
    public static final String RETURN_MESSAGE = "RETURN_MESSAGE"; 
    public static final String VIEW_MESSAGE = "VIEW_MESSAGE"; 
    public static final String AUTHOR_MESSAGE = "AUTHOR_MESSAGE";     
    public static final String DIVISION_MESSAGE = "DIVISION_MESSAGE"; 
    public static final String PRICE_MESSAGE = "PRICE_MESSAGE"; 
    public static final String BACK_MESSAGE = "BACK_MESSAGE"; 
    public static final String SUBSCRIBE_MESSAGE = "SUBSCRIBE_MESSAGE"; 
    public static final String SUBSCRIBE_ANOTHER_MESSAGE = "SUBSCRIBE_ANOTHER_MESSAGE"; 
    public static final String SUBSCRIBE_TO_ORDERS_MESSAGE = "SUBSCRIBE_TO_ORDERS_MESSAGE"; 
    public static final String SUMM_MESSAGE = "SUMM_MESSAGE"; 
    public static final String BALANCE_MESSAGE = "BALANCE_MESSAGE"; 
    public static final String ERROR_MESSAGE = "ERROR_MESSAGE"; 
    public static final String ERROR_SUBSCRIBEING_MESSAGE = "ERROR_SUBSCRIBEING_MESSAGE"; 
    public static final String DETAIL_MESSAGE = "DETAIL_MESSAGE"; 
    public static final String OPTIONS_CONFIRM_MESSAGE = "OPTIONS_CONFIRM_MESSAGE";    
    public static final String ADMIN_PANEL_MESSAGE = "ADMIN_PANEL_MESSAGE"; 
    public static final String NEW_MESSAGE = "NEW_MESSAGE"; 
    public static final String PUBLICATIONS_EDITOR_MESSAGE = "PUBLICATIONS_EDITOR_MESSAGE"; 
    public static final String EDIT_MESSAGE = "EDIT_MESSAGE"; 
    public static final String DELETE_MESSAGE = "DELETE_MESSAGE"; 
    public static final String ADD_PASS_MESSAGE = "ADD_PASS_MESSAGE"; 
    public static final String ADD_PUBL_ADD_MESSAGE = "ADD_PUBL_ADD_MESSAGE"; 
    public static final String ADD_FAIL_MESSAGE = "ADD_FAIL_MESSAGE"; 
    public static final String MISTAKE_MESSAGE = "MISTAKE_MESSAGE"; 
    public static final String MISTAKE_TRY_MESSAGE = "MISTAKE_TRY_MESSAGE"; 
    public static final String MAIN_ADMIN_PANEL_MESSAGE = "MAIN_ADMIN_PANEL_MESSAGE"; 
    public static final String ADD_PUBLICATION_MESSAGE = "ADD_PUBLICATION_MESSAGE";
    public static final String COVER_MESSAGE = "COVER_MESSAGE";
    public static final String DESCRIPTION_MESSAGE = "DESCRIPTION_MESSAGE";
    public static final String TRY_AGAIN_MESSAGE = "TRY_AGAIN_MESSAGE";            
    public static final String DELETE_PUBLICATION_MESSAGE = "DELETE_PUBLICATION_MESSAGE";
    public static final String PUBLICATION_WITH_ID_MESSAGE = "PUBLICATION_WITH_ID_MESSAGE";
    public static final String WAS_DELETE_MESSAGE = "WAS_DELETE_MESSAGE";
    public static final String CANCEL_ALL_MESSAGE = "CANCEL_ALL_MESSAGE";
    public static final String ORDERS_CANCELED_MESSAGE = "ORDERS_CANCELED_MESSAGE";
    public static final String SUBSCRIBING_CANCELED_MESSAGE = "SUBSCRIBING_CANCELED_MESSAGE";
    public static final String SAVE_MESSAGE = "SAVE_MESSAGE";
    public static final String EDIT_PUBLICATION_MESSAGE = "EDIT_PUBLICATION_MESSAGE";
    public static final String EDIT_CONFIRM_PASS = "EDIT_CONFIRM_PASS";
    public static final String EDIT_CONFIRM_ERROR = "EDIT_CONFIRM_ERROR";

    
    private static HttpSession session = null;
    private static int localeId = 0;
    public static final Logger LOG=Logger.getLogger(MessageManager.class.getName());  
        

    public static MessageManager getInstance(){        
        if (instanceEN == null){
            instanceEN = new MessageManager();
            instanceEN.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME_EN);
        }
        
        if (instanceRU == null){
            instanceRU = new MessageManager();
            instanceRU.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME_RU);
        }
        
        if (localeId==0) {
            return instanceEN;
        }
        else {
            return instanceRU;
        }
    }
    
        
    public static void setLocale(int locale){
        localeId = locale;   
    }
         
    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}
