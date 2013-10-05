
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

public class OrdersCommand implements ICommand{

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page;
          HttpSession session = null;
          Map<String, String> hMap = new HashMap<String, String>();
          Map<String, String> list = new HashMap<String, String>();
          String uId = null;
          String uIdLogin = null;
          UserDAO user = null;
                    
          SubscriptionDAO subs = DAOFactory.getSubscriptionDAO();
          user = DAOFactory.getUserDAO();
          
          session = request.getSession(true);
          if (session.getAttribute("login") != null) {
              uIdLogin = session.getAttribute("login").toString();
              LOG.info("Session user: " + uIdLogin);
              uId = user.getIdByLogin(uIdLogin);
              LOG.info("Session user id: " + uId);
          }
          
          if (uId != null) {  
            hMap = subs.getSubscriptions(uId);
            int summ = subs.getBuySumm(uId);
//            System.out.println("summ: " + summ);          
          
            PublishDAO publ = DAOFactory.getPublishDAO();
                    
            int balance = Integer.parseInt(user.getBalance(uId));
//            System.out.println("balance: " + balance);
                            
          
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
          request.setAttribute("balance", balance);
          request.setAttribute("summ", summ);
        }
          

          
        LOG.info("Session login: " + session.getAttribute("login"));
        if (session.getAttribute("login") != null) {
            
            request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
            request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
            request.setAttribute("SUBSCRIBE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SUBSCRIBE_MESSAGE));
            request.setAttribute("SUMM_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SUMM_MESSAGE));
            request.setAttribute("BALANCE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.BALANCE_MESSAGE));
            
            request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
            request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
            request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
            request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
            request.setAttribute("HISTORY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HISTORY_MESSAGE));
            request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
            request.setAttribute("CANCEL_ALL_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.CANCEL_ALL_MESSAGE));
               
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ORDERS_PAGE_PATH);
        }else{
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }   
                  
        return page;
    }
            
}
