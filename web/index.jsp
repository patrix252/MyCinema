<%-- 
    Document   : index
    Created on : 12-ago-2015, 18.36.35
    Author     : Paolo
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Index Page</h1><br>
        <c:if test="${sessionScope.utente.getEmail()!=null}">
            Benvenuto <c:out value="${sessionScope.utente.getNome()}"/><br>
        </c:if>
        <a href="oggialcinema.jsp"> - Oggi al cinema</a><br>
        <a href="filminprogramma.jsp"> - Tutti i film in programma</a><br>
        <c:if test="${sessionScope.utente.getEmail()!=null}">
            <a href="logout.jsp"> - Log Out</a><br>
        </c:if>
        <c:if test="${sessionScope.utente.getEmail()==null}">
            <a href="registrazione.jsp"> - Registrazione Test</a><br>
            <a href="LoginServlet"> - Login Test</a>
        </c:if>
    </body>
</html>
