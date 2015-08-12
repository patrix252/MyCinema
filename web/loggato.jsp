<%-- 
    Document   : loggato
    Created on : 17-lug-2015, 15.16.14
    Author     : Paolo
--%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Benvenuto <c:out value="${sessionScope.utente.getNome()}"/>!</h1>
        <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 1500);
        </script>
    </body>
</html>
