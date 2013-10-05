
package ua.bionic.turko.commands;

import ua.bionic.turko.daointerfaces.ICommand;
import java.io.IOException;
import java.util.ArrayList;
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
import ua.bionic.turko.logic.LoginLogic;


public class ListPublCommand implements ICommand{

    private static final String PARAM_NAME_LOGIN = "login";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String page;
          Map<String, String> hMap = new HashMap<String, String>();
          HttpSession session = null;
          
          
          String pid = null; //request.getParameter(PARAM_PUBLISH_ID);
//          System.out.println("publ_id: "+pid);
          PublishDAO publ = DAOFactory.getPublishDAO();
//          System.out.println("publ: "+publ);
          
          hMap = publ.getSubscriptions();
   
        request.setAttribute("list", hMap);
        
        
        session = request.getSession(true);
        LOG.info("Get login from session");
        if (session.getAttribute("login") != null) {
            
            request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
            request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
            request.setAttribute("VIEW_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.VIEW_MESSAGE));
            
            request.setAttribute("MAIN_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.MAIN_MESSAGE));
            request.setAttribute("PUBLICATIONS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.PUBLICATIONS_MESSAGE));
            request.setAttribute("ACCOUNT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ACCOUNT_MESSAGE));
            request.setAttribute("ORDERS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.ORDERS_MESSAGE));
            request.setAttribute("HISTORY_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.HISTORY_MESSAGE));
            request.setAttribute("EXIT_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EXIT_MESSAGE));
                      
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LIST_PAGE_PATH);
            
        }else{
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            request.setAttribute("RETURN_LINK_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.RETURN_LINK_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }      
          
        return page;
    }
    
    
    
//public HashMap resultSetToHashMap(ResultSet rs,int h) throws SQLException{
//  ResultSetMetaData md = rs.getMetaData();
//  int columns = md.getColumnCount();
//  int k=0;
//  HashMap row = new HashMap();
//  while (rs.next()){
//     k++; 
//     //for(int i=1; i<=columns; i++){
//     //  row.put(md.getColumnName(i)+"_"+k,rs.getObject(i));
//     //  System.out.println(md.getColumnName(i)+"_"+k);
//     //}
//     row.put(md.getColumnName(2)+"_"+k,rs.getObject(2));
//     System.out.println(md.getColumnName(2)+"_"+k);
//  }
// return row;
//}
    
    
    
    
}
