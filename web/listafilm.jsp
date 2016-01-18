<%-- 
    Document   : listafilm
    Created on : Jan 11, 2016, 7:27:26 PM
    Author     : Patrix
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="./lib/js/gestioneFilmAdmin.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <select id="films" onchange="showSpettacoli(this.value)">
            <option>-- Seleziona un film --</option>
            <c:forEach items="${sessionScope.filmsAll}" var = "fa">
                <option value="${fa.id_film}"><c:out value="${fa.titolo}"/></option>
            </c:forEach>
        </select>
        <select id="spettacoli" hidden onchange="getIncasso(this.value)">
        </select>
        <p id="incasso"></p>
    </body>
</html>
