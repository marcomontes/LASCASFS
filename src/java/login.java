import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="login", urlPatterns={"/login"})
public class login extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //API Credentials
        String client_id="HTX3FYAVTJOV2NR3NOS5RGOMBRNYIVJD55T2BIZ4DDXXXXXX";
        String client_secret="5W33FKDZ11UY3V23OBAM0AODFXYLVMTUORAAWYCSUHXXXXXX";
        String redirect_uri="http://localhost:8080/foursquare/auth";
        
        //Set Session Variables
        HttpSession session = request.getSession(true);
        session.setAttribute("client_key", client_id);
        session.setAttribute("client_secret", client_secret);
        session.setAttribute("redirect_uri", redirect_uri);

        try {
            //Redirect User to foursquare login page
            String url="https://foursquare.com/oauth2/authenticate?client_id="+client_id+"&response_type=code&redirect_uri="+redirect_uri;
            response.sendRedirect(url);

        } finally { 
            out.close();
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }


}
