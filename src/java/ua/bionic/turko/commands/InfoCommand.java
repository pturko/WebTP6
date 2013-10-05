
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.bionic.turko.dao.AuthorDAO;
import ua.bionic.turko.dao.DAOFactory;
import ua.bionic.turko.dao.GenreDAO;
import ua.bionic.turko.dao.PublishDAO;
import static ua.bionic.turko.commands.LoginCommand.LOG;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class InfoCommand implements ICommand{

    private static final String PARAM_PUBLISH_ID = "publ_id";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page;
          HttpSession session = null;
          
          String pid = request.getParameter(PARAM_PUBLISH_ID);
//          System.out.println("publ_id: "+pid);
          PublishDAO publ = DAOFactory.getPublishDAO();
//          System.out.println("publ: "+publ);
          
          String publName = publ.getName(pid);
          String publAuthorId  = publ.getAuthId(pid);
          String publGenre = publ.getGenreId(pid);
          String publPrice = publ.getPrice(pid);
          String publDescription = publ.getDescription(pid);
          String publImagePath = publ.getImagePath(pid);
          
          GenreDAO genre = DAOFactory.getGenreDAO();
          String genreID = genre.getGenre(publGenre);
                             
          AuthorDAO author = DAOFactory.getAuthorDAO();
          String authorID = author.getAuthorName(publAuthorId) + " " + author.getAuthorSurname(publAuthorId);
          
                    
        request.setAttribute("id", pid);
        request.setAttribute("name", publName);
        request.setAttribute("author", authorID);
        request.setAttribute("genre", genreID);
        request.setAttribute("price", publPrice);
        request.setAttribute("description", publDescription);
        request.setAttribute("image", publImagePath);
        
        session = request.getSession(true);
        LOG.info("Get login from session");
        LOG.info("Session 1: " + session.getAttribute("login"));
        if (session.getAttribute("login") != null) {
            
            request.setAttribute("DETAIL_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DETAIL_MESSAGE));
            request.setAttribute("AUTHOR_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.AUTHOR_MESSAGE));
            request.setAttribute("DIVISION_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DIVISION_MESSAGE));
            request.setAttribute("PRICE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PRICE_MESSAGE));
            request.setAttribute("ID_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ID_MESSAGE));
            request.setAttribute("BACK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.BACK_MESSAGE));
            request.setAttribute("SUBSCRIBE_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.SUBSCRIBE_MESSAGE));
            
            request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
            request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
            request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
            request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
            request.setAttribute("HISTORY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HISTORY_MESSAGE));
            request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
   
            
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INFO_PAGE_PATH);
        }else{
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }  
                     
          return page;
    }
    
}
