<%-- 
    Document   : oggialcinema
    Created on : 13-ago-2015, 15.11.19
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
        <h1>Hello World!</h1>
        
        <table id="table" class="table table-hover table-mc-light-blue">
            <tr>
                <td><h4>Titolo</h4></td>
                <td><h4>Genere</h4></td>
                <td><h4>Ora</h4></td>
                <td><h4>Sala</h4></td>
                <td><h4>Regista</h4></td>
                <td><h4>Trama</h4></td>
            </tr>
             <c:forEach items="${sessionScope.filmsOggi}" var="films"> 
                <tr>
                    <td><c:out value="${util.Classi.films.f.getTitolo()}"/></td>
                    <td><c:out value="${util.Classi.films.f.getGenere().getDescrizione()}"/></td>
                    <td><c:out value="${util.Classi.films.s.getOra()}"/></td>
                    <td><c:out value="${util.Classi.films.s.getId_sala()}"/></td>
                    <td><c:out value="${util.Classi.films.f.getRegista()}"/></td>
                    <td><c:out value="${util.Classi.films.f.getTrama()}"/></td>
                    
                  
                   
                </tr> 
                  </c:forEach> 
        </table>
    </body>
</html>
