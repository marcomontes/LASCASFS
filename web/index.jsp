<%-- 
    Document   : index
    Created on : 29/02/2012, 07:28:30 PM
    Author     : javo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foursquare API LASCAS</title>
    </head>
    <body>
    <center>
        <form action="login" method="POST">
            <img border="0" src="<%=request.getContextPath()%>/imagenes/foursquare-logo.png" alt="Foursquare" width="270" height="73" />
            <p>
            <input type="image" src="<%=request.getContextPath()%>/imagenes/connect-blue.png" alt="Adelante !" />
            <%-- <input type="submit" value="Adelante !" /> --%>
        </form>
    </center>
    </body>
</html>