
package ua.bionic.turko.commandsadmin;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.bionic.turko.dao.DAOFactory;
import ua.bionic.turko.dao.PublishDAO;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class DeletePublicationConfirmCommand implements ICommand{

    private static final String PARAM_PUBLISH_ID = "publ_id";
    public static final Logger LOG=Logger.getLogger(DeletePublicationConfirmCommand.class.getName());  
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        HttpSession session = null;
                    
        String publId = request.getParameter(PARAM_PUBLISH_ID);
        
        PublishDAO publ = DAOFactory.getPublishDAO();
        publ.deletePublication(publId);
        
        session = request.getSession(true);
        LOG.info("Get login from session");
        if (session.getAttribute("login") != null) {
             request.setAttribute("PUBL_ID", publId);
             
             request.setAttribute("DELETE_PUBLICATION_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DELETE_PUBLICATION_MESSAGE));
             request.setAttribute("PUBLICATION_WITH_ID_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATION_WITH_ID_MESSAGE));
             request.setAttribute("WAS_DELETE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.WAS_DELETE_MESSAGE));
             request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
        
             page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DELETE_PUBLICATION_CONFIRM_PAGE_PATH);
        }else{
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
                    
          return page;
    }
    
}
