<%-- 
    Document   : registroPrenotazioni
    Created on : Jan 19, 2016, 11:46:12 AM
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
        <c:if test="${sessionScope.prenotazioniUtente==null}">
            Nessuna Prenotazione!
        </c:if>
        <table>
            <c:if test="${sessionScope.prenotazioniUtente!=null}">
                <tr>
                    <td>ID Prenotazione</td>
                    <td>Titolo Film</td>
                    <td>Sala</td>
                    <td>Data Spettacolo</td>
                    <td>Ora Spettacolo</td>
                    <td>Riga Posto</td>
                    <td>Colonna Posto</td>
                    <td>Prezzo</td>
                    <td>Data Prenotazione</td>
                    <td>Ora Prenotazione</td>
                </tr>
                <c:forEach items="${sessionScope.prenotazioniUtente}" var = "pu">
                    <tr>
                        <td><c:out value="${pu.id_prenotazione}"/></td>
                        <td><c:out value="${pu.titoloFilm}"/></td>
                        <td><c:out value="${pu.id_sala}"/></td>
                        <td><c:out value="${pu.data_spettacolo}"/></td>
                        <td><c:out value="${pu.ora_spettacolo}"/></td>
                        <td><c:out value="${pu.riga_posto}"/></td>
                        <td><c:out value="${pu.colonna_posto}"/></td>
                        <td><c:out value="${pu.prezzo}"/></td>
                        <td><c:out value="${pu.data_prenotazione}"/></td>
                        <td><c:out value="${pu.ora_prenotazione}"/></td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </body>
</html>
