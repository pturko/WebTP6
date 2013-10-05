
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


public class EditPublicationConfirmCommand implements ICommand{

    private static final String PARAM_PUBLISH_ID = "publ_id";
    private static final String PARAM_PUBLISH_NAME = "name";
    private static final String PARAM_PUBLISH_PRICE = "price";
    private static final String PARAM_PUBLISH_DESCRIPTION = "description";
    private static final String PARAM_PUBLISH_IMAGE = "image";
    public static final Logger LOG=Logger.getLogger(EditPublicationConfirmCommand.class.getName());  
    private boolean verify;
         
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page;
          HttpSession session = null;
          verify = true;
          PublishDAO publ = null;
          
          String pid = request.getParameter(PARAM_PUBLISH_ID);          
          String name = request.getParameter(PARAM_PUBLISH_NAME);
          String price = request.getParameter(PARAM_PUBLISH_PRICE);
          String description = request.getParameter(PARAM_PUBLISH_DESCRIPTION);
          String image = request.getParameter(PARAM_PUBLISH_IMAGE);
          
//          LOG.info(name);
//          LOG.info(price);
//          LOG.info(description);
//          LOG.info(image);
          LOG.info("PARAM_PUBLISH_ID="+pid);
          
          
          if (name==null || name.equals("")) {
              LOG.warn("Error value in field 'name'");
              verify=false;
          }
          if (price==null || price.equals("")) {
              LOG.warn("Error value in field 'price'");
              verify=false;
          }
          if (description==null || description.equals("")) {
              LOG.warn("Error value in field 'description'");
              verify=false;
          }
          if (image==null || image.equals("")) {
              LOG.warn("Error value in field 'image'");
              verify=false;
          }
        
          if (checkString(price)==false) {
              LOG.warn("Error value in field 'price'. Not number value");
              verify=false;
          }
          
          
          publ = DAOFactory.getPublishDAO();
          if (verify==true) {
                LOG.info("Add publication");
                publ.updatePublication(pid, name, price, description, image);
          }
               
          
        session = request.getSession(true);
        LOG.info("Get login from session");
        LOG.info("Session 1: " + session.getAttribute("login"));
        if (session.getAttribute("login") != null) {
            
            if (verify==true) {
                
                request.setAttribute("ADD_PUBLICATION_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ADD_PUBLICATION_MESSAGE));
                request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
                request.setAttribute("ADD_PASS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ADD_PASS_MESSAGE));
                request.setAttribute("ADD_PUBL_ADD_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ADD_PUBL_ADD_MESSAGE));
                request.setAttribute("EDIT_CONFIRM_PASS", MessageManager.getInstance().getProperty(MessageManager.EDIT_CONFIRM_PASS));
                  
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EDIT_PUBLICATION_CONFIRM_PAGE_PATH);
            } 
            else {

                request.setAttribute("ADD_PUBLICATION_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ADD_PUBLICATION_MESSAGE));
                request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
                request.setAttribute("NEW_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NEW_MESSAGE));
                request.setAttribute("ADD_FAIL_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ADD_FAIL_MESSAGE));
                request.setAttribute("MISTAKE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MISTAKE_MESSAGE));
                request.setAttribute("MISTAKE_TRY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MISTAKE_TRY_MESSAGE));
                request.setAttribute("TRY_AGAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.TRY_AGAIN_MESSAGE));
                request.setAttribute("EDIT_CONFIRM_ERROR", MessageManager.getInstance().getProperty(MessageManager.EDIT_CONFIRM_ERROR));
                
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EDIT_PUBLICATION_ERROR_PATH);
            }
        
        }else{
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
                    
        return page;
    }
    
    
    public boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
