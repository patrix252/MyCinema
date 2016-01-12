<%-- 
    Document   : loggato
    Created on : 17-lug-2015, 15.16.14
    Author     : Paolo
--%>

<%@page import="beans.Utente"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        
        <link rel="stylesheet" type="css/my.css" href="./lib/css/mycss.css">
        
    </head>
    <body>
        <%
            String userName = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("idUtente"))
                        userName = cookie.getValue();
                }
            }
            if(session.getAttribute("utente")!=null)
                userName = ((Utente)session.getAttribute("utente")).getEmail();
            if (userName == null)
                response.sendRedirect("login.jsp");
        %>
        <div class="container">
            <jsp:include page="navbar.jsp" />
            
            <p class="bg-success"><h1>Benvenuto <%=userName%></h1></p>
        
        </div>
            
            <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 1500);
        </script>
    </body>
</html>
