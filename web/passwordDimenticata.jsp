<%-- 
    Document   : passwordDimenticata
    Created on : Feb 6, 2016, 7:07:53 PM
    Author     : Paolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Password dimenticata</title>
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
        <div class="container">
            <jsp:include page="navbar.jsp" />
        <h1>Inserisca la sua mail qua sotto, le sarà rimandata la password all'indirizzo indicato!</h1>
        
        <form class="form-inline" action="PasswordReminder">
            <input id="Mail" name="Mail" type="text"> <br>
            <input type="submit">
        </form>
        <jsp:include page="footer.jsp" />
        </div>
    </body>
</html>
