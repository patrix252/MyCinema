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
        <link href="./lib/css/carousel.css" rel="stylesheet">
    </head>
    <body>
        <h1>Totale: totale preso dal valore della sessione</h1>
        <h1>I posti sono: posti presi dalla list(Posto) presa dalla sessione</h1>
        <h2 class="text-center"><b>Dati carta:</b></h2>
        <br>
        <dl class="dl-horizontal">
            <dt><h4>Tipo di Carta:</h4></dt>
            <dd><div class="form-group">
                    <select class="form-control" id="InputType">
                        <option>PayPal</option>
                        <option>MasterCard</option>
                        <option>Maestro</option>
                        <option>Visa</option>
                    </select>
                </div>
            </dd>
            <dt><h4>Nome del titolare:</h4></dt>
            <dd><input type="text" class="form-control" id="InputTitolare" placeholder="nome cognome"></dd>
            <dt><h4>Data di Scadenza:</h4></dt>
            <dd><input type="text" class="form-control" id="InputDate" placeholder="FARE DATEPICKER!!"></dd>
            <dt><h4>Numero Carta:</h4></dt>
            <dd><input type="text" class="form-control" id="InputNumber" placeholder="xxxx-xxxx-xxxx-xxxx"></dd>
            <dt><h4>Codice di controllo:</h4></dt>
            <dd><input type="password" class="form-control" id="InputName" placeholder="xxxx"></dd>
        </dl>  
        <br>
        <br>
        <form action="pagamentoEffettuato.jsp" method="POST">
            <input type="submit" value="Conferma Dati e procedi al PAGAH!mento"/>
        </form>
    </body>
</html>
