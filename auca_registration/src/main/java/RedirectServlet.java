import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String searchTerm = request.getParameter("query");
        
      
        if (searchTerm != null && !searchTerm.isEmpty()) {
            
            response.sendRedirect("https://www.google.com/search?q=" + searchTerm);
        } else {
            response.sendRedirect("https://www.google.com");
        }
    }
}