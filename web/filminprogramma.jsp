<%-- 
    Document   : filminprogramma
    Created on : 14-ago-2015, 10.14.10
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
        <h1>Oggi al cinema!</h1>
        
        <table style="width:100%">
            <tr>
                <td><h4>Titolo</h4></td>
                <td><h4>Genere</h4></td>
                <td><h4>Ora</h4></td>
                <td><h4>Sala</h4></td>
                <td><h4>Regista</h4></td>
                <td><h4>Durata</h4></td>
                <td><h4>Data</h4></td>
                <td><h4>Trama</h4></td>
                <td><h4>3D</h4></td>
                <td><h4>Trailer</h4></td>
                <td><h4>Locandina</h4></td>
            </tr>
                <c:forEach items="${sessionScope.filmInProgramma}" var="film">
                <tr> 
                    <td><a href="descrizionefilm.jsp?id=${film.f.id_film}"><c:out value="${film.f.titolo}"/></a></td>
                    <td><c:out value="${film.f.genere.descrizione}"/></td>
                    <td><c:out value="${film.s.ora}"/></td>
                    <td><c:out value="${film.s.id_sala}"/></td>
                    <td><c:out value="${film.f.regista}"/></td>
                    <td><c:out value="${film.f.durata}"/></td>
                    <td><c:out value="${film.s.data}"/></td>  
                    <td><c:out value="${film.f.trama}"/></td>  
                    <td><c:choose><c:when test="${film.f.is3D==0}">No</c:when><c:otherwise>Sì</c:otherwise></c:choose></td>
                    <td><a href="${film.f.url_trailer}">Trailer</a></td>
                    <td><img src="${film.f.uri_locandina}" style="width:200px;height:280px;"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
