<%-- 
    Document   : descrizionefilm
    Created on : 14-ago-2015, 10.42.03
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
        <c:set var="film" value="${sessionScope.film}"/>
        <h1><c:out value="${film.titolo}"/></h1>
        <table  style="width:100%">
            <tr>
                <td><h4>Titolo</h4></td>
                <td><h4>Genere</h4></td>
                <td><h4>Regista</h4></td>
                <td><h4>Durata</h4></td>
                <td><h4>Trama</h4></td>
                <td><h4>3D</h4></td>
                <td><h4>Trailer</h4></td>
                <td><h4>Locandina</h4></td>
            </tr>
            <tr> 
                <td><c:out value="${film.titolo}"/></td>
                <td><c:out value="${film.genere.descrizione}"/></td>
                <td><c:out value="${film.regista}"/></td>
                <td><c:out value="${film.durata}"/></td>
                <td><c:out value="${film.trama}"/></td>  
                <td><c:choose><c:when test="${film.is3D==0}">No</c:when><c:otherwise>Sì</c:otherwise></c:choose></td>
                <td><a href="${film.url_trailer}">Trailer</a></td>
                <td><img src="${film.uri_locandina}" style="width:200px;height:280px;"/></td>
            </tr>
        </table>
                <a href="prenotazione.jsp?id=${param.id}&titolo=${film.titolo}"><button title="Prenota">Prenota</button></a>
    </body>
</html>
