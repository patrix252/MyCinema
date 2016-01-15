<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="./lib/css/mycss.css">
    </head>
    <body>
        <form action="LoginServlet" method="POST">
            <label style="display: block;">Mail:</label><input id="mail" name="Mail" size="10"/><br>
            <label style="display: block;">Password:</label><input type="password" id="password" name="Password" size="10"/><br>
            <input type="submit" value="Submit"/>
        </form>
        <c:if test="${sessionScope.loginError}">
            <p style="color: red;">Email o password non valide, reinserisci i dati!</p>
            <c:set var="loginError" value="false" scope="session"/>
        </c:if><br>
        <c:if test="${sessionScope.problemaConnessione}">
            <p style="color: red;">Problema a contattare il server, riprova tra qualche minuto!</p>
            <c:set var="problemaConnessione" value="false" scope="session"/>
        </c:if><br>
        <div>Nuovo utente? Registrati <a href="registrazione.jsp">qui</a></div>
    </body>
</html>
