
package ua.bionic.turko.commandsadmin;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.bionic.turko.dao.DAOFactory;
import ua.bionic.turko.dao.PublishDAO;
import static ua.bionic.turko.commands.LoginCommand.LOG;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;



public class ListPublAdminCommand implements ICommand{
    
    private static final String PARAM_NAME_LOGIN = "login";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page;
          HttpSession session = null;
          Map<String, String> hMap = new HashMap<String, String>();

          
          String pid = null; //request.getParameter(PARAM_PUBLISH_ID);
//          System.out.println("publ_id: "+pid);
          PublishDAO publ = DAOFactory.getPublishDAO();
//          System.out.println("publ: "+publ);
          
          hMap = publ.getSubscriptions();
          
          request.setAttribute("list", hMap);

          session = request.getSession(true);
          LOG.info("Get login from session");
          LOG.info("Session 1: " + session.getAttribute("login"));
          if (session.getAttribute("login") != null) {
              
              request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
              request.setAttribute("PUBLICATIONS_EDITOR_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_EDITOR_MESSAGE));
              request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
              request.setAttribute("EDIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EDIT_MESSAGE));
              request.setAttribute("DELETE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DELETE_MESSAGE));
              request.setAttribute("ADMIN_PANEL_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ADMIN_PANEL_MESSAGE));
              request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
              request.setAttribute("NEW_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NEW_MESSAGE));                  
              request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
              
              page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LIST_PAGE_ADMIN_PATH);
          }else{
              request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
              request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
              page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
          }      
          
          return page;
    }
    
}
