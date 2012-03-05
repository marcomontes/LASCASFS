import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;

/**
 *
 * @author Sadan
 */
@WebServlet(name="auth", urlPatterns={"/auth"})
public class auth extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String code=request.getParameter("code");

        //Instatiate GetWebRes Object
        GetWebRes webrs = new GetWebRes();

        //Get Session Variables
        HttpSession session = request.getSession(true);
        String client_key=(String)session.getAttribute("client_key");
        String client_secret=(String)session.getAttribute("client_secret");
        String redirect_uri=(String)session.getAttribute("redirect_uri");

        //If code is not null
        if (!code.equals("")){

            String url="https://foursquare.com/oauth2/access_token?client_id="+client_key+"&client_secret="+client_secret+"&grant_type=authorization_code&redirect_uri="+redirect_uri+"&code="+code;
            String content=webrs.getContent(url);

            //JSON Parsing using GSON Library
            OauthToken oauthtoken = new Gson().fromJson(content, OauthToken.class);

            //Store Oauth token in session
            session.setAttribute("access_token", oauthtoken.getAccess_Token());

            out.println("Authenticated Successfully!");

            //Utilizar posiblemente este codigo en la parte de getvenues, asi utilizamos esta clase Java para hacer todos los checkings.
            //recordar que el checkin solo se puede realizar utilizando metodos POST, de otra forma devuelve error al realizarlo.
            //Redirect to welcome page
            response.sendRedirect(request.getContextPath() + "/getvenues");

        }else{
            out.println("Error: Could not authenticate");
        }


        try {
           // out.println(myCode);
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

//OauthToken object for JSON Mapping
class OauthToken{
    private String access_token;
    public String getAccess_Token(){return access_token;}
    public void setAccess_Token(String accesstoken){
        this.access_token=accesstoken;
    }    
}
