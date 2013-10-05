
package ua.bionic.turko.logic;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class FilterEncoding implements Filter {
        private String encoding;
        public static final Logger LOG=Logger.getLogger(InfoLogic.class.getName());
        FilterConfig fConfig = null; 
                
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException	{
		 HttpSession hsSession = ((HttpServletRequest) request).getSession();
                     
                 try {
                    LOG.info("check encoding");
                    String enc = request.getCharacterEncoding();
                    if (!enc.equalsIgnoreCase("UTF-8")) {
                        LOG.info("encoding to UTF");
                        response.setCharacterEncoding("UTF-8");
                    }
                 } catch(UnsupportedOperationException ex) {
                     LOG.warn(ex.toString());  
                 }		  
	}
	
        public void init(FilterConfig fConfig) throws ServletException {
            this.fConfig = fConfig;
	}

        public void destroy() {

	}
}