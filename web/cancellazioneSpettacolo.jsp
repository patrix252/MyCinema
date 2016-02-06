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
    </head>
    <body>
        <c:if test="${sessionScope.problemaCancellazione==false}">
            <h1>Cancellazione avvenuta con successo!</h1>
        </c:if>
        <c:if test="${sessionScope.problemaCancellazione==true}">
            <h1>Prenotazione già cominciata, impossibile cancellarla!</h1>
        </c:if>
        <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 3000);
        </script>
    </body>
</html>
