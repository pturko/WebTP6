
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.bionic.turko.dao.DAOFactory;
import ua.bionic.turko.dao.PublishDAO;
import ua.bionic.turko.dao.SubscriptionDAO;
import ua.bionic.turko.dao.UserDAO;
import static ua.bionic.turko.commands.LoginCommand.LOG;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class HistoryCommand implements ICommand{

    private static final String PARAM_PUBLISH_ID = "publ_id";
    private HttpSession session = null;
    private SubscriptionDAO subs = null;    
    private String page;
    private String uId = null;
    private String uIdLogin = null;
    private UserDAO user = null;        
    PublishDAO publ = null;              
    
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          Map<String, String> hMap = new HashMap<String, String>();
          Map<String, String> list = new HashMap<String, String>();  
          uId = null;
          uIdLogin = null;
          user = null;
          session = null;
          subs = DAOFactory.getSubscriptionDAO();
          
          user = DAOFactory.getUserDAO();
          
          session = request.getSession(true);
          if (session.getAttribute("login") != null) {
              uIdLogin = session.getAttribute("login").toString();
              LOG.info("Session user: " + uIdLogin);
              uId = user.getIdByLogin(uIdLogin);
              LOG.info("Session user id: " + uId);
          }
          
          if (uId != null) {          
             hMap = subs.getHistory("2");
          }
                   
          publ = DAOFactory.getPublishDAO();  
          publ = DAOFactory.getPublishDAO();
          
          Iterator it = hMap.entrySet().iterator();
          while (it.hasNext()) {
                 Map.Entry pairs = (Map.Entry)it.next();
                 list.put(publ.getName(pairs.getKey().toString()), pairs.getValue().toString());
//                 System.out.println(">>>>>");
//                 System.out.println(">>"+publ.getName(pairs.getKey().toString()));
//                 System.out.println(">>"+pairs.getValue().toString());
//                 System.out.println(">>>>>");
          }

        
        request.setAttribute("list", list);
        
        session = request.getSession(true);
        LOG.info("Get login from session");
        LOG.info("Session 1: " + session.getAttribute("login"));
        if (session.getAttribute("login") != null) {
            
         request.setAttribute("HISTORY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HISTORY_MESSAGE));
         request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
          
         request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
         request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
         request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
         request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
         request.setAttribute("HISTORY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HISTORY_MESSAGE));
         request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
               
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.HISTORY_PAGE_PATH);
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

         if(!(obj instanceof HistoryCommand)) return false;
         
          HistoryCommand obj1 = (HistoryCommand) obj;
         
          return subs == obj1.subs
                && (session == obj1.session)
                && (page == obj1.page)
                && (uId == obj1.uId)
                && (uIdLogin == obj1.uIdLogin)
                && (publ == obj1.publ)
                && (user == obj1.user);
          
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }          
    
    
}
