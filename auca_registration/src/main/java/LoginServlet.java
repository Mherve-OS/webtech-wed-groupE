import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Retrieve the parameters from the login.html form
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        
        // 2. Set content type to HTML and initialize the writer
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 3. Generate a styled HTML response
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<title>Login Result</title>");
        out.println("<style>");
        out.println("  body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f6; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println("  .container { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); text-align: center; border-top: 6px solid #1877f2; }");
        out.println("  h2 { margin-top: 0; }");
        out.println("  .btn { display: inline-block; margin-top: 20px; padding: 12px 24px; background-color: #1877f2; color: white; text-decoration: none; border-radius: 6px; font-weight: bold; }");
        out.println("  .btn:hover { background-color: #145dbf; }");
        out.println("</style></head><body>");

        out.println("<div class='container'>");
        
        // 4. Assignment Logic: Check password length
        if (pass != null && pass.length() < 8) {
            // Trend: Using high-contrast orange for warnings improves accessibility
            out.println("<h2 style='color: #e67e22;'>Password Warning</h2>");
            out.println("<p>Hello <strong>" + user + "</strong>, your password is weak. Try a strong one.</p>");
        } else {
            // Trend: "Success Green" provides immediate positive feedback
            out.println("<h2 style='color: #27ae60;'>Welcome!</h2>");
            out.println("<p>Welcome <strong>" + user + "</strong></p>");
        }

        // 5. Navigation to Assignment 2
        out.println("<a href='search.html' class='btn'>Go to (Search)</a>");
        
        out.println("</div></body></html>");
    }
}