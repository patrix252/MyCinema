<%-- 
    Document   : passwordDimenticata
    Created on : Feb 6, 2016, 7:07:53 PM
    Author     : Paolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inserisca la sua mail qua sotto, le sarà rimandata la password all'indirizzo indicato!</h1>
        <form action="PasswordReminder">
            <input id="Mail" name="Mail" type="text"> <br>
            <input type="submit">
        </form>
    </body>
</html>
