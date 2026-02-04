import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><style>");
        out.println("body { font-family: Arial; background-color: #eef2f3; text-align: center; padding-top: 100px; }");
        out.println(".message-container { background: white; display: inline-block; padding: 40px; border-radius: 15px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); }");
        out.println(".btn { text-decoration: none; color: white; background: #2ecc71; padding: 10px 20px; border-radius: 5px; display: inline-block; margin-top: 20px; }");
        out.println("</style></head><body>");

        out.println("<div class='message-container'>");
        if (pass != null && pass.length() < 8) {
            out.println("<h2 style='color: #e67e22;'>Oops!</h2>");
            out.println("<p>Hello <b>" + user + "</b>, your password is weak. Try a strong one.</p>");
        } else {
            out.println("<h2 style='color: #2ecc71;'>Success!</h2>");
            out.println("<p>Welcome <b>" + user + "</b></p>");
        }
        out.println("<br><a href='search.html' class='btn'>Continue to Search</a>");
        out.println("</div></body></html>");
    }
}