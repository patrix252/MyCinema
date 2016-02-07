<%-- 
    Document   : pagamentoEffettuato
    Created on : Oct 13, 2015, 2:46:32 PM
    Author     : Paolo
--%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
       <title>Pagamento effettuato</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" type="css/my.css" href="./lib/css/mycss.css">
    </head>
    <body>
        
        <c:if test="${sessionScope.errorePagamento==true}">
            <div class="container">
            <h1>Errore nel prenotare i posti selezionati, riprovi tra qualche minuto!</h1><br>
            </div>
        </c:if>
        <c:if test="${sessionScope.errorePagamento==false}">
            <div class="container">
            <h1>Transizione avvenuta con successo, controlli la sua email per la ricezione del ticket che potrà</h1><br>
            <h1>presentare direttamente alle casse del cinema! Grazie per aver scelto myCinema!</h1><br>
            <h1>La preghiamo di attendere, verrà reindirizzato automaticamente alla home del sito!</h1>
            </div>
        </c:if>
        <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 10000);
        </script>
        
    </body>
</html>
