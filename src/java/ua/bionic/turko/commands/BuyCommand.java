
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.bionic.turko.dao.DAOFactory;
import ua.bionic.turko.dao.SubscriptionDAO;
import ua.bionic.turko.dao.UserDAO;
import static ua.bionic.turko.commands.LoginCommand.LOG;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class BuyCommand implements ICommand{

    private static final String PARAM_PUBLISH_ID = "publ_id";
    private String page;
    private HttpSession session = null;
    private String uId = null;
    private String uIdLogin = null;
    private UserDAO user = null;
    private SubscriptionDAO subs = null;
          
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          int balance = -1;
          int summ = 0;
          session = null;
          uId = null;
          uIdLogin = null;
          user = null;
          subs = null; 
                      
          
          user = DAOFactory.getUserDAO();
          
          session = request.getSession(true);          
                    
          if (session.getAttribute("login") != null) {
              uIdLogin = session.getAttribute("login").toString();
              LOG.info("Session user: " + uIdLogin);
              uId = user.getIdByLogin(uIdLogin);
              LOG.info("Session user id: " + uId);
          }            
          
         if (uId != null) {
          balance = Integer.parseInt(user.getBalance(uId));
//          System.out.println("balance: " + balance);
          
          subs = DAOFactory.getSubscriptionDAO();
          summ = subs.getBuySumm(uId);
//          System.out.println("summ: " + summ);
         }
         
          if (summ<=balance) {
                            
              user.withdraw(uId,(balance-summ));
              
              subs.setBuyFlag(uId);
              
                session = request.getSession(true);
                LOG.info("Get login from session");
                LOG.info("Session 1: " + session.getAttribute("login"));
                if (session.getAttribute("login") != null) {
                    
                    request.setAttribute("SUBSCRIBE_TO_ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SUBSCRIBE_TO_ORDERS_MESSAGE));
                    request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
                    request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
                    
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.BUY_PAGE_PATH);
                }else{
                    request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
                    request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
                }
                    
          }
          else {
              
                request.setAttribute("ERROR_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ERROR_MESSAGE));
                request.setAttribute("ERROR_SUBSCRIBEING_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ERROR_SUBSCRIBEING_MESSAGE));
                request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
            
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.BUY_ERROR_PAGE_PATH);
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

         if(!(obj instanceof BuyCommand)) return false;
         
          BuyCommand obj1 = (BuyCommand) obj;
         
          return PARAM_PUBLISH_ID == obj1.PARAM_PUBLISH_ID
                  && (page == obj1.page)
                  && (uIdLogin == obj1.uIdLogin)
                  && (uId == obj1.uId)
                  && (subs == obj1.subs)
                  && (session == obj1.session);
    
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }          
    
    
}
