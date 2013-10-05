
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class NoCommand implements ICommand{
    public static final Logger LOG=Logger.getLogger(NoCommand.class.getName()); 
    
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
       request.setAttribute("LOG_ON_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.LOG_ON_MESSAGE));
       request.setAttribute("LOGIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.LOGIN_MESSAGE));
       request.setAttribute("PASSWORD_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PASSWORD_MESSAGE));
       request.setAttribute("SUBMIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SUBMIT_MESSAGE));
       
       String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
       return page;
    }
    
}
