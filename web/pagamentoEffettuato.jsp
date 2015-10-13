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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Transizione avvenuta con successo, controlli la sua email per la ricezione del ticket che potrà</h1><br>
        <h1>presentare direttamente alle casse del cinema! Grazie per aver scelto myCinema!</h1><br>
        <h1>La preghiamo di attendere, verrà reindirizzato automaticamente alla home del sito!</h1>
        
        <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 10000);
        </script>
    </body>
</html>
