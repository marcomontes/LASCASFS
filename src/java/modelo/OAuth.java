/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javo
 */
public class OAuth {
    public void authenticationRequest(HttpServletRequest request, HttpServletResponse response) {
        FoursquareApi foursquareApi = new FoursquareApi("5IRI1GZD25A051NKM4M5PLI4XCHQRP3Q3JTWO2R4XJ3ZWYTD", "QFIFMHRWSEANVMASMEABO3WHZS4S5MQUSVMOGNEZDKJZ2DJA", "https://github.com/javotrujillo");
        try {
            // First we need to redirect our user to authentication page.
            response.sendRedirect(foursquareApi.getAuthenticationUrl());
        } catch (IOException e) {
            // TODO: Error handling
        }
    }
    
    public void handleCallback(HttpServletRequest request, HttpServletResponse response) {
        // After user has logged in and confirmed that our program may access user's Foursquare account
        // Foursquare redirects user back to callback url. 
        FoursquareApi foursquareApi = new FoursquareApi("5IRI1GZD25A051NKM4M5PLI4XCHQRP3Q3JTWO2R4XJ3ZWYTD", "QFIFMHRWSEANVMASMEABO3WHZS4S5MQUSVMOGNEZDKJZ2DJA", "https://github.com/javotrujillo");
        // Callback url contains authorization code 
        String code = request.getParameter("code");
        try {
          // finally we need to authenticate that authorization code 
          foursquareApi.authenticateCode(code);
          // ... and voilà we have a authenticated Foursquare client
        } catch (FoursquareApiException e) {
         // TODO: Error handling
        }
    }
  
}