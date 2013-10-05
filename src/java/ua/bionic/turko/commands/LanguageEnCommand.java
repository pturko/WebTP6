
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class LanguageEnCommand implements ICommand{

    public static final Logger LOG=Logger.getLogger(LanguageEnCommand.class.getName());  
    private static final String PARAM_NAME_LANG = "lang_en";

    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page = null;
          String lang = request.getParameter(PARAM_NAME_LANG);
          HttpSession session = null;
                    
          MessageManager.setLocale(0);
          
          session = request.getSession(true);
          LOG.info("Get login from session");
          if (session.getAttribute("login") != null) {
              
              request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
              request.setAttribute("OPTIONS_CONFIRM_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.OPTIONS_CONFIRM_MESSAGE));
            
              page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LANGUAGE_PAGE_PATH);
          }else{
              request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
              request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
              page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
          }
          
          return page;
    }
    
}
