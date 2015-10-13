<%-- 
    Document   : pagamento
    Created on : Oct 12, 2015, 11:39:27 AM
    Author     : Paolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Totale: totale preso dal valore della sessione</h1>
        <h1>I posti sono: posti presi dalla list(Posto) presa dalla sessione</h1>
        <h1>Inserisci i dati della tua carta</h1>
        <form action="EmailQrCode" method="POST">
            <input type="submit" value="Conferma Dati e procedi al PAGAH!mento"/>
        </form>
    </body>
</html>
