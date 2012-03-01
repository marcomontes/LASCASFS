/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javo
 */
@WebServlet(name = "Authentication", urlPatterns = {"/Authentication"})
public class Authentication extends HttpServlet {
    String ClientID = "5IRI1GZD25A051NKM4M5PLI4XCHQRP3Q3JTWO2R4XJ3ZWYTD";
    String ClientSecret = "QFIFMHRWSEANVMASMEABO3WHZS4S5MQUSVMOGNEZDKJZ2DJA";
    String CallbackURL = "https://github.com/javotrujillo";
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            FoursquareApi foursquareApi = new FoursquareApi("5IRI1GZD25A051NKM4M5PLI4XCHQRP3Q3JTWO2R4XJ3ZWYTD", "QFIFMHRWSEANVMASMEABO3WHZS4S5MQUSVMOGNEZDKJZ2DJA", "https://github.com/javotrujillo");
                try {
                    // First we need to redirect our user to authentication page. 
                    response.sendRedirect(foursquareApi.getAuthenticationUrl());
                } catch (IOException e) {
                    // TODO: Error handling
                }
            String code = request.getParameter("code");
                try {
                  // finally we need to authenticate that authorization code 
                  foursquareApi.authenticateCode(code);
                  // ... and voilà we have a authenticated Foursquare client
                } catch (FoursquareApiException e) {
                 // TODO: Error handling
                }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Authentication</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authentication at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
