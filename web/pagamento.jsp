<%-- 
    Document   : pagamento
    Created on : Oct 12, 2015, 11:39:27 AM
    Author     : Paolo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pagamento</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
     
        <link rel="stylesheet" type="text/css" href="./lib/css/seat-charts2.css">
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        
        <script src="./lib/js/seat-charts.min.js"></script> 

        <link rel="stylesheet" href="./lib/css/mycss.css">
    </head>
    <body>
        <div class="container">
            
            <jsp:include page="navbar.jsp" />
            
            <h3>Totale: ${sessionScope.totale}€</h3>
            <h3>Posti Interi:
            <c:forEach items="${sessionScope.postiInteri}" var = "pi">
                <c:out value="${pi.riga}-${pi.colonna} "/>
            </c:forEach>
            </h3>
            <h3>Posti Ridotti: 
            <c:forEach items="${sessionScope.postiRidotti}" var = "pr">
                <c:out value="${pr.riga}-${pr.colonna} "/>
            </c:forEach>
            </h3>
            <h2 class="text-center"><b>Dati carta:</b></h2>
            
            <form id="modulo" class="form-inline" name="modulo"  onSubmit="return controllo();" action="pagamentoEffettuato.jsp" method="POST">    
                <div class="row">
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
                        <dd>
                                <select id="daydropdown" class="form-control" name="Giorno"></select> 
                                <select id="monthdropdown" class="form-control" name="Mese"></select> 
                                <select id="yeardropdown" class="form-control" name="Anno"></select> 
                            </dd>
                        <dt><h4>Numero Carta:</h4></dt>
                        <dd><input type="text" class="form-control" id="InputNumber" placeholder="xxxx-xxxx-xxxx-xxxx"></dd>
                        <dt><h4>Codice di controllo:</h4></dt>
                        <dd><input type="password" class="form-control" id="InputName" placeholder="xxxx"></dd>
                    </dl>  
                </div>
                
                <div class="row">
                    <div class="col-lg-12">
                        <button type="submit" class="btn btn-lg center-block btn-success">Procedi al pagamento</button>
                    </div>
                </div>
                
              
            </form>
            
            
            
            
            
            

            <jsp:include page="footer.jsp" />
        </div>
        
        <script type="text/javascript">
            window.onload = function () {
                populatedropdown("daydropdown", "monthdropdown", "yeardropdown");
            };
            
            
            var monthtext = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];

            function populatedropdown(dayfield, monthfield, yearfield) {
                var today = new Date();
                var dayfield = document.getElementById(dayfield);
                var monthfield = document.getElementById(monthfield);
                var yearfield = document.getElementById(yearfield);
                for (var i = 0; i < 31; i++)
                    dayfield.options[i] = new Option(i + 1, i + 1);
                dayfield.options[0] = new Option(1, 1, true, true);
                for (var m = 0; m < 12; m++)
                    monthfield.options[m] = new Option(monthtext[m], m + 1);
                monthfield.options[0] = new Option("Jan", 1, true, true);
                var thisyear = today.getFullYear();
                for (var y = 0; y < 16; y++) {
                    yearfield.options[y] = new Option(thisyear + 15, thisyear);
                    thisyear -= 1;
                }
                yearfield.options[0] = new Option(today.getFullYear() + 15, today.getFullYear(), true, true);
            }
        </script>
        
    </body>
</html>
