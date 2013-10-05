
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.bionic.turko.dao.DAOFactory;
import ua.bionic.turko.dao.UserDAO;
import ua.bionic.turko.dao.UserType;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class AccountCommand implements ICommand{

    private static final String PARAM_USER_ID = "user_id";
    private static final Logger LOG=Logger.getLogger(LoginCommand.class.getName());  
    private String page;
    private String userTypeString = null;
    private String pid = null;
    private String login = null;
    private HttpSession session = null;
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
          session = request.getSession(true);
          LOG.info("Get login from session: " + session.getAttribute("login"));
          if (session.getAttribute("login") != null) {
             login = session.getAttribute("login").toString();
          }
          
          UserDAO user = DAOFactory.getUserDAO(); //pid = request.getParameter(PARAM_USER_ID);
          pid = user.getIdByLogin(login);
          
          
          LOG.info("user_id: "+login);
          
          LOG.info("user: "+user);
          
          String userName = user.getName(pid);
          String userSurname  = user.getSurname(pid);
          String userLogin = user.getLogin(pid);
          String userLastLogin = user.getLastLogin(pid);
          String userBalance = user.getBalance(pid);
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
         request.setAttribute("userBalance", userBalance);
        
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
         request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
         request.setAttribute("HISTORY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HISTORY_MESSAGE));
         request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
         request.setAttribute("BALANCE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.BALANCE_MESSAGE));
         
         
         page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ACCOUNT_PAGE_PATH);
        
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

         if(!(obj instanceof AccountCommand)) return false;
         
          AccountCommand obj1 = (AccountCommand) obj;
         
          return PARAM_USER_ID == obj1.PARAM_USER_ID
                  && (page == obj1.page)
                  && (userTypeString == obj1.userTypeString)
                  && (pid == obj1.pid)
                  && (login == obj1.login)
                  && (session == obj1.session);
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }          
    
    
}
