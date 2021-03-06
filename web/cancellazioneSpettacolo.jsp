<%-- 
    Document   : cancellazioneSpettacolo
    Created on : Feb 6, 2016, 3:40:09 PM
    Author     : Paolo
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" type="css/my.css" href="./lib/css/mycss.css">
    </head>
    
    
    
    <body>
        <c:if test="${sessionScope.problemaCancellazione==false}">
            <div class="container">
            <h1>Cancellazione avvenuta con successo!</h1>
            </div>
        </c:if>
        <c:if test="${sessionScope.problemaCancellazione==true}">
            <div class="container">
            <h1>Prenotazione già cominciata, impossibile cancellarla!</h1>
           </div>
        </c:if>
        <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 3000);
        </script>
    </body>
</html>
