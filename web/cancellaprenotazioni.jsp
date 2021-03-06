
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="generalcode" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<html>
    <head>
        <title> Cancella prenotazione </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
          
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" href="./lib/css/mycss.css">
        <script src="./lib/js/gestioneFilmAdmin.js"></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        
        <div class="container">
            <h1>Selezionare prenotazione da cancellare </h1>
            <table class="table">
                <thead>
                    <tr>
                       <th>ID Prenotazione</th>
                       <th>Email</th>
                       <th>Film</th>
                       <th>Posto</th>
                       <th>Sala</th>
                       <th>Data spettacolo</th>
                       <th>Ora spettacolo</th>
                       <th>Cancella</th>
                   </tr>
               </thead>
               <tbody>
                <c:forEach items="${sessionScope.prenotazioni}" var = "pr" varStatus="myIndex">
                    <tr>
                       <td> ${pr.id_prenotazione}</td>
                       <td> ${pr.id_utente}</td>
                       <td> ${sessionScope.filmPrenotazioni[myIndex.index].titolo}</td>
                       <td> ${sessionScope.postiPrenotazioni[myIndex.index].riga}-${sessionScope.postiPrenotazioni[myIndex.index].colonna}</td>
                       <td> ${sessionScope.spettacoliPrenotazioni[myIndex.index].id_sala}</td>
                       <td> ${sessionScope.spettacoliPrenotazioni[myIndex.index].data} </td>
                       <td> ${sessionScope.spettacoliPrenotazioni[myIndex.index].ora}</td>
                       <td><a class="btn btn-default" href="DeletePrenotation?id=${pr.id_prenotazione}">Cancella</a> </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
        
</html>
