
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
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


public class SubscribeCommand implements ICommand{

    private static final String PARAM_NAME_ID = "id";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page;
          String uId = null;
          String uIdLogin = null;
          UserDAO user = null;
          HttpSession session = null;
          
          user = DAOFactory.getUserDAO();
          
          session = request.getSession(true);
          if (session.getAttribute("login") != null) {
              uIdLogin = session.getAttribute("login").toString();
              LOG.info("Session user: " + uIdLogin);
              uId = user.getIdByLogin(uIdLogin);
              LOG.info("Session user id: " + uId);
          }
          
          if (uId != null) {        
            String publ_id = request.getParameter(PARAM_NAME_ID);
 //           System.out.println("publ_id: " + publ_id);
          
          
            PublishDAO publ = DAOFactory.getPublishDAO();
            String publPrice = publ.getPrice(publ_id);
 //           System.out.println("publPrice: " + publPrice);
          
          
            SubscriptionDAO subs = DAOFactory.getSubscriptionDAO();
            
            
            if (subs.findSomeSubscriptionforUser(uId, publ_id)==false) {             
                subs.addSubscription(publ_id,uId,publPrice);
            }
          
          }
          
          
        LOG.info("Session 1: " + session.getAttribute("login"));
        if (session.getAttribute("login") != null) {
                     
            request.setAttribute("SUBSCRIBE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SUBSCRIBE_MESSAGE));
            request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
            request.setAttribute("SUBSCRIBE_ANOTHER_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SUBSCRIBE_ANOTHER_MESSAGE));
            request.setAttribute("SUBSCRIBE_TO_ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SUBSCRIBE_TO_ORDERS_MESSAGE));
            
            request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
            request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
            request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
            request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
            request.setAttribute("HISTORY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HISTORY_MESSAGE));
            request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
   
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.SUBSCRIBE_PAGE_PATH);
        }else{
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }       
         
        return page;
    }
    
}
