
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
                       <th>Data prenotazione</th>
                       <th>Ora prenotazione</th>
                       <th>Cancella</th>
                   </tr>
               </thead>
               <tbody>
                <c:forEach items="${sessionScope.prenotazioni}" var = "pr">
                    <tr>
                       <td> ${pr.id_prenotazione}</td>
                       <td> ${pr.id_utente}</td>
                       <td> ${pr.data} </td>
                       <td> ${pr.ora}</td>
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
