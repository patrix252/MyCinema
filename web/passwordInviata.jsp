<%-- 
    Document   : passwordInviata
    Created on : Feb 7, 2016, 3:23:25 PM
    Author     : Paolo
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <title>Passowrd inviata</title>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" type="css/my.css" href="./lib/css/mycss.css">
    </head>
    
    
    
    <body>
        <c:if test="${sessionScope.errorePassword==true}">
            <div class="container">
            <h1>C'è stato un errore nell'invio della password, la preghiamo di riprovare inserendo nuovamente la sua email!</h1>
            </div>>
            <script>
            setTimeout(function(){ window.location.replace("passwordDimenticata.jsp"); }, 3000);
            </script>
        </c:if>
        <c:if test="${sessionScope.errorePassword==false}">
            <div class="container">
            <h1>La sua password è stata inviata per email, la preghiamo di controllare la sua casella postale!</h1>
            </div>
            <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 3000);
            </script>
        </c:if>
    </body>
</html>
