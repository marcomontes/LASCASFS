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
        String client_id="5IRI1GZD25A051NKM4M5PLI4XCHQRP3Q3JTWO2R4XJ3ZWYTD";
        String client_secret="QFIFMHRWSEANVMASMEABO3WHZS4S5MQUSVMOGNEZDKJZ2DJA";
        String redirect_uri="http://localhost:8080/LASCASFS/auth";
        
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
