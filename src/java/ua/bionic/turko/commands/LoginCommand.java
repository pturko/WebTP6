
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import static ua.bionic.turko.dao.AuthorDAO.LOG;
import ua.bionic.turko.dao.DAOFactory;
import ua.bionic.turko.dao.UserDAO;
import ua.bionic.turko.dao.UserType;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;
import ua.bionic.turko.logic.LoginLogic;

public class LoginCommand implements ICommand{

    public static final Logger LOG=Logger.getLogger(LoginCommand.class.getName());  
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_ = "password";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page = null;
          String login = request.getParameter(PARAM_NAME_LOGIN);
          String password = request.getParameter(PARAM_NAME_PASSWORD);
          String userId = null;
          UserDAO user = null;
          UserType userType = null;
          HttpSession session = null;
          
          
          if (login != null) {
              session = request.getSession(true);
              session.setAttribute("login", login);
              session.setAttribute("password", password);
              session.setMaxInactiveInterval(10*60); //set session time
              LOG.info("Session set: " + session.getAttribute("login"));
          }          
          else {              
              session = request.getSession(true);
              LOG.info("Get login from session");
              if (session.getAttribute("login") != null) {
                login = session.getAttribute("login").toString();
                password = session.getAttribute("password").toString();
              }
          }
   
          
            Boolean checkLogin = null;
            try {
             checkLogin = LoginLogic.checkLogin(login,password);
            } catch (ClassNotFoundException ex) {
                LOG.warn(ex.toString());
            } catch (SQLException ex) {
                LOG.warn(ex.toString());
            }
              
            
            if (login!=null) {
                LOG.info("getting userId & userType");
                user = DAOFactory.getUserDAO();
                userId = user.getIdByLogin(login);
                userType = user.getUserType(userId);
            }
            
          
            
          if(checkLogin){
              request.setAttribute("user", login);
              request.setAttribute("id", user.getIdByLogin(login));
              if (userType==UserType.ADMIN) {
                                    
                  request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
                  request.setAttribute("ADMIN_PANEL_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ADMIN_PANEL_MESSAGE));
                  request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
                  request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
                  request.setAttribute("NEW_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NEW_MESSAGE));                  
                  request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
                  request.setAttribute("MAIN_ADMIN_PANEL_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_ADMIN_PANEL_MESSAGE));
                  page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_ADMIN_PATH);
              }
              else {     
                  
                   request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
                   request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
                   request.setAttribute("HELLO_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HELLO_MESSAGE));
                   request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
                   request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
                   request.setAttribute("HISTORY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HISTORY_MESSAGE));
                   request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
                   
                   page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
              }
          }else{
              request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
              request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
              page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
          }
          
          return page;
    }
    
}
