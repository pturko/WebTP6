
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.bionic.turko.dao.UserDAO;
import static ua.bionic.turko.commands.LoginCommand.LOG;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class BuyErrorCommand implements ICommand{

    private static final String PARAM_PUBLISH_ID = "publ_id";
    private String page;
    private UserDAO user = null;
    private HttpSession session = null;

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user = null;
        session = null;
        
        session = request.getSession(true);
        LOG.info("Get login from session");
        LOG.info("Session 1: " + session.getAttribute("login"));
        if (session.getAttribute("login") != null) {
            
            request.setAttribute("ERROR_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ERROR_MESSAGE));
            request.setAttribute("ERROR_SUBSCRIBEING_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ERROR_SUBSCRIBEING_MESSAGE));
            request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
                  
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.BUY_ERROR_PAGE_PATH);
        }else{
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }           
          
        return page;
    }
    
    
    public int hashCode() {
        long ht = this.getTime();
        return (int) ht ^ (int) (ht >> 31);
    }
	
	
    public String toString() {
	return getClass().getName();
    }
	

    public boolean equals(Object obj) {
         if (this == obj) return true;
		            
         if(obj == null) return false;

         if(!(obj instanceof BuyErrorCommand)) return false;
         
          BuyErrorCommand obj1 = (BuyErrorCommand) obj;
         
          return PARAM_PUBLISH_ID == obj1.PARAM_PUBLISH_ID
                && (page == obj1.page)
                && (user == obj1.user)
                && (session == obj1.session);
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }          
    
    
}
