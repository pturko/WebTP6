
package ua.bionic.turko.commandsadmin;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.bionic.turko.dao.DAOFactory;
import ua.bionic.turko.dao.UserDAO;
import ua.bionic.turko.dao.UserType;
import static ua.bionic.turko.commands.LoginCommand.LOG;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class AccountAdminCommand implements ICommand{

    private static final String PARAM_USER_ID = "user_id";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page;
          String userTypeString = null;
          HttpSession session = null;
          String pid = null;
          String login = null;
                
          session = request.getSession(true);
          LOG.info("Get login from session");
          LOG.info("Session 1: " + session.getAttribute("login"));
          if (session.getAttribute("login") != null) {
             login = session.getAttribute("login").toString();
          }
          
          
          UserDAO user = DAOFactory.getUserDAO();
          pid = user.getIdByLogin(login);
                    
//          System.out.println("user: "+user);
          
          String userName = user.getName(pid);
          String userSurname  = user.getSurname(pid);
          String userLogin = user.getLogin(pid);
          String userLastLogin = user.getLastLogin(pid);
          UserType userType = user.getUserType(pid);
          
          if (userType==UserType.ADMIN) {
                 userTypeString = "Admin";
              }
              else {
                  userTypeString = "User";
              }
          

          request.setAttribute("id", pid);  
          request.setAttribute("userName", userName);
          request.setAttribute("userSurname", userSurname);
          request.setAttribute("userLogin", userLogin);
          request.setAttribute("userLastLogin", userLastLogin);
          request.setAttribute("userType", userTypeString);
        
         request.setAttribute("ID_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ID_MESSAGE));
         request.setAttribute("NAME_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NAME_MESSAGE));
         request.setAttribute("SURNAME_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SURNAME_MESSAGE));
         request.setAttribute("USERTYPE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.USERTYPE_MESSAGE));
         request.setAttribute("RETURN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_MESSAGE));
         request.setAttribute("LOGIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.LOGIN_MESSAGE));
         request.setAttribute("BACK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.BACK_MESSAGE));
         request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
          
         request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
         request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
         request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
         request.setAttribute("NEW_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NEW_MESSAGE));
         request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
           
         page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_ADMIN_PAGE_PATH);
              

         return page;
    }
    
}
