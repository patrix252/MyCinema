<%-- 
    Document   : incassiclientitop
    Created on : Jan 11, 2016, 7:28:09 PM
    Author     : Patrix
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
        <c:forEach items="${sessionScope.utentiTop}" var = "ut">
            <c:out value="${ut.utente.nome} - ${ut.spesa}€"/><br>
        </c:forEach>
    </body>
</html>
