
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


public class EditPublicationCommand implements ICommand{

    private static final String PARAM_PUBLISH_ID = "publ_id";
    public static final Logger LOG=Logger.getLogger(EditPublicationCommand.class.getName()); 
    
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        HttpSession session = null;
        PublishDAO publ = null;
        
        String pid = request.getParameter(PARAM_PUBLISH_ID);
                            
        LOG.info("Get PUBLISH_ID");
        publ = DAOFactory.getPublishDAO();
        String name = publ.getName(pid);
        String description = publ.getDescription(pid);
        String price = publ.getPrice(pid);
        String imagePath = publ.getImagePath(pid);
           
        session = request.getSession(true);
        LOG.info("Get login from session");
        if (session.getAttribute("login") != null) {
                                    
             request.setAttribute("PUBL_ID", pid);
             request.setAttribute("EDITOR_NAME", name);
             request.setAttribute("EDITOR_DESCRIPTION", description);
             request.setAttribute("EDITOR_PRICE", price);
             request.setAttribute("EDITOR_IMAGEPATH", imagePath);
                    
             request.setAttribute("EDIT_PUBLICATION_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EDIT_PUBLICATION_MESSAGE));
             request.setAttribute("NAME_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NAME_MESSAGE));
             request.setAttribute("AUTHOR_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.AUTHOR_MESSAGE));
             request.setAttribute("DIVISION_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DIVISION_MESSAGE));
             request.setAttribute("PRICE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PRICE_MESSAGE));
             request.setAttribute("COVER_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.COVER_MESSAGE));
             request.setAttribute("DESCRIPTION_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DESCRIPTION_MESSAGE));
             request.setAttribute("SAVE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SAVE_MESSAGE));
                          
             request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
             request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
             request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
             request.setAttribute("NEW_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NEW_MESSAGE));
             request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
            
             page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EDIT_PUBLICATION_PAGE_PATH);
        }else{
             
             request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
             request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
             
             page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
                    
          return page;
    }
    
}
