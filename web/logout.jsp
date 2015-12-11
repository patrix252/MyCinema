<%-- 
    Document   : logout
    Created on : 8-ott-2015, 13.39.55
    Author     : Paolo
--%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log out</title>
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" type="css/my.css" href="./lib/css/mycss.css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="navbar.jsp" />
            
            <p class="bg-success"><h1>Sei stato disconnesso con successo!</h1></p>
        </div>
        
        <%session.invalidate();%>
        <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 1500);
        </script>
    </body>
</html>
