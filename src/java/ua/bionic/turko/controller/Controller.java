
package ua.bionic.turko.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.bionic.turko.daointerfaces.ICommand;
import ua.bionic.turko.manager.ConfigurationManager;
import ua.bionic.turko.manager.MessageManager;


public class Controller extends HttpServlet {
    
    public static final Logger LOG=Logger.getLogger(Controller.class.getName());    
    RequestHelper requestHelper = RequestHelper.getInstance();

             
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        
        LOG.info("processRequest page");
        
        //System.out.println("login: " + request.getParameter("login"));
        try {
            ICommand command = requestHelper.getCommand(request);    
            //System.out.println("c: " + command.toString());
            page = command.execute(request, response);
            //System.out.println(request.getQueryString());
        } catch (ServletException e) {
            e.printStackTrace();
            LOG.warn(e.toString());
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e){
            e.printStackTrace();
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);

        }
                
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    
//    protected ResultSet SQL() throws Exception {
//        
//        String queryUsers = "select * from users";
//        
//        getConnectResources();
//    	
//    	Driver dbDriver = DriverFactory.createDriver(DBTypes.MYSQL);
//    	    	
//    	Database database = new Database(dbDriver); //Connection
//    	database.connect(); //conn = getConnection(url, login, password)
//    	
//    	Query query = new Query(database);
//    	query.createStatement(); //createStatement()
//    	
//    	ResultSet rs = query.executeQuery(queryUsers);
//    	
//    	//Statement st = database.getConnection().createStatement();		
//		//ResultSet rs = st.executeQuery(query);
//
//        System.out.println(">>>>> DB >>>>>");
//        
//		String LOGIN;
//		int USER_ID  ;
//                String  PASSWORD;
//		while (rs.next()) {
//			LOGIN  = rs.getString("LOGIN");
//			USER_ID   = rs.getInt("USER_ID ");			
//			PASSWORD  = rs.getString("PASSWORD ");
//                        System.out.println("******************************");
//			System.out.println("LOGIN: " + LOGIN);
//			System.out.println("PUBL_ID : " + USER_ID  );
//                      System.out.println("PASSWORD  : " + PASSWORD  );
//			System.out.println("******************************");
//		}
//         return rs;       
//    }
    
              
                
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
