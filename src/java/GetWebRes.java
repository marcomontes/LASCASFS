import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//Get Web Resource - Class
public class GetWebRes {

    //Return response after GET Request
    public String getContent(String httpurl){

        try {
            String sURL = httpurl;
            URL url = new URL(sURL);
            URLConnection httpc = url.openConnection();
            httpc.setDoInput(true);
            httpc.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpc.getInputStream()));
            String strLine = "";
            String content="";
            while ((strLine = in.readLine()) != null){
                 content=content+strLine;
            }
            in.close();
           
            return content;
            

        } catch (Exception e) {
            return ("Exception: " + e.getMessage());
        }
        
    }

    //Return response after POST Request
    public String setContent(String httpurl, String inputdata){

        try {
            String sURL = httpurl;
            URL url = new URL(sURL);
            HttpURLConnection httpc = (HttpURLConnection)url.openConnection();
            httpc.setDoOutput(true);
            httpc.setRequestMethod("POST");
            httpc.connect();

            //Write POST request data
            OutputStreamWriter out = new OutputStreamWriter(httpc.getOutputStream());
            out.write(inputdata);
            out.flush();            
            
            //Get Response
            BufferedReader in = new BufferedReader(new InputStreamReader(httpc.getInputStream()));
            String strLine="";
            String content="";
            while ((strLine = in.readLine()) != null){
                 content=content+strLine;
            }

            in.close();
            out.close();
            return content;

        } catch (Exception e) {
            return ("Exception: " + e.getMessage());
        }

    }

}
