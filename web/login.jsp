<%-- 
    Document   : login
    Created on : 12-ago-2015, 21.41.56
    Author     : Paolo
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="LoginServlet" method="POST">
            <label style="display: block;">Mail:</label><input id="mail" name="Mail" size="10"/><br>
            <label style="display: block;">Password:</label><input type="password" id="password" name="Password" size="10"/><br>
            <input type="submit" value="Submit"/>
        </form>
        <c:if test="${sessionScope.loginError}">
            <FONT COLOR="#FF0000">Email o password non valide, reinserisci i dati!</FONT>
            <c:set var="loginError" value="false" scope="session"/>
        </c:if><br>
        <div>Nuovo utente? Registrati <a href="registrazione.jsp">qui</a></div>
    </body>
</html>
