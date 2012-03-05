import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

@WebServlet(name="checkin", urlPatterns={"/checkin"})
public class checkin extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //instantiate GetWebResource object
        GetWebRes webrs = new GetWebRes();

        //Get Session Variables
        HttpSession session = request.getSession(true);
        String access_token=(String)session.getAttribute("access_token");
        //If AccessToken is not created
        if (access_token == null){
            //Redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
        }

        //Get venueid and broadcast parameters from request object
        String venueid = request.getParameter("venueid");
        String broadcast = request.getParameter("broadcast");
        if ((venueid != null) && (broadcast != null)){
            String url="https://api.foursquare.com/v2/checkins/add";
            String inputdata = "venueId="+venueid+"&broadcast="+broadcast+"&oauth_token="+access_token;
            String content=webrs.setContent(url,inputdata);
            //out.println(content);

            //Extract message from JSON response usin JSON-Simple lib
            Object obj=JSONValue.parse(content);
            JSONObject main_obj=(JSONObject)obj;
            JSONArray notifications_arr = (JSONArray)main_obj.get("notifications");
            JSONObject notification_obj = (JSONObject)notifications_arr.get(0);
            JSONObject item_obj = (JSONObject)notification_obj.get("item");
            if (item_obj.get("message")!=null){
                out.println(item_obj.get("message"));
            }else{
                out.print("Checkin was successful");
                response.sendRedirect(request.getContextPath() + "/getvenues");
            }
            
        }else{
            out.println ("Error: broadcast and venueid parameters missing");
        }

        out.close();


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
