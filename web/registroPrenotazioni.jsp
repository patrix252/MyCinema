
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="generalcode" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>


<head>
    <title>Account di ${sessionScope.utente.getNome()}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" href="./lib/css/mycss.css">
    </head>
    <body>
         
        <div class="container">
            <jsp:include page="navbar.jsp" />
            <jsp:include page="account.jsp"/>
            <h1>Le tue prenotazioni</h1>
                <c:if test="${sessionScope.prenotazioniUtente==null}">
                Nessuna Prenotazione!   
            </c:if>
            <table class="table">
                <c:if test="${sessionScope.prenotazioniUtente!=null}">
                    <thead>
                        <tr>
                            <td><b>ID Prenotazione</b></td>
                            <td><b>Titolo Film</b></td>
                            <td><b>Sala</b></td>
                            <td><b>Data Spettacolo</b></td>
                            <td><b>Ora Spettacolo</b></td>
                            <td><b>Riga Posto</b></td>
                            <td><b>Colonna Posto</b></td>
                            <td><b>Prezzo</b></td>
                            <td><b>Data Prenotazione</b></td>
                            <td><b>Ora Prenotazione</b> </td>
                        </tr>
                    </thead>
                    <tbody>
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
                    </tbody>
                </c:if>
            </table>
            
            <jsp:include page="footer.jsp" />
        </div>

      
    </body>
</html>