<%@page import="java.lang.String"%>
<%@page import="beans.Posto"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.Spettacolo"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
    <head>
        <title>Prenotazione</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
     
        <link rel="stylesheet" type="text/css" href="./lib/css/seat-charts2.css">
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        
        <script src="./lib/js/seat-charts.min.js"></script> 
        <script src="./lib/js/prenotazione.js"></script>


        <link rel="stylesheet" href="./lib/css/mycss.css">
        
    </head>
    <body>
        <!-- INSERIRE NAVBAR -->
        
        <div class="container">
            
            <jsp:include page="navbar.jsp" />
            
            <div class="row">   
                <c:set var="film" value="${param.titolo}"/>
                <h1>Titolo film : <c:out value="${film}" /></h1>
            </div>
            
            <div class="row" style="margin-top: 20px">
                <div class="col-lg-6">
                    <dl class="dl-horizontal">
                        <dt><h4>Data spettacolo:</h4></dt>
                        <dd>
                            <div class="form-group">
                                <!-- ==================================================
                                DATE PICKER // NON DISABILITA LE DATE PRECEDENTI A "OGGI"
                                ================================================== -->
                                <select class="form-control" name="data" id="data" onchange="cambia(this)">
                                    <option value="InfoData" disabled> -- Seleziona una data -- </option>
                                    <c:set var="dataScelta" value="null"/>
                                    <c:forEach items="${sessionScope.orari}" var="orari">
                                    <option value="${orari}"><c:out value="${orari}"/></option>
                                    </c:forEach>
                                </select>
                                
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="col-lg-6">
                    <dl class="dl-horizontal">
                        <dt><h4>Ora spettacolo:</h4></dt>
                        <dd>
                            <div class="form-group">
                                <select class="form-control" name="ora" id="ora" onchange="mappa(posti)" hidden>
                                    
                                </select>
                            </div>
                        </dd>
                    </dl>
                </div>
            </div>
            
            <div class="row" style="padding-top: 5%;" id="acquista" hidden>
                <h4 align="center">Per selezionare un posto ridotto (bambini fino a 14 anni, disabili, studenti o militari) cliccare due volte su un posto</h4>                       
                <div class="col-md-1"></div>
                <div class="col-md-5">
                    <div class="row">
                        <div id="seat-map" class="noselect">
                            <div class="front-indicator">Front</div>
                        </div>
                    </div>
                    <div class="row">
                        <div id="legend"></div>
                    </div>
                </div>
                
                
                <div class="col-md-5" style="padding-top: 5%;">    
                    <div class="row" style="margin-bottom: 20px">
                        <p style="font-size: 30px"><b>Riepilogo:</b> </p>
                    </div>
                    <div class="row">
                    <p><b>n° posti interi: </b><span id="counter_intero">0</span></p>
                    </div>
                    <div class="row">
                    <p><b>n° posti ridotti: </b><span id="counter_ridotto">0</span></p>
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                    <p><b>Prezzo totale: </b>€ <span id="total">0</span></p>
                    </div>
                    <div class="row">
                    <a href="PagamentoServlet" id="link"><button class="btn center-block btn-success">Acquista!</button></a>
                    </div>
                </div>
                <br>
            </div>    
                                    
            <jsp:include page="footer.jsp" />
        </div>           
       
        
        <script>
            var posti = <%= ((ArrayList<List<String>>)(session.getAttribute("postiOccupati"))).toString() %>; 
            var date = [<%= ((StringBuffer)session.getAttribute("dateSpettacoli")).toString() %>];
            var orari = [<%= ((StringBuffer)session.getAttribute("orariSpettacoli")).toString() %>];
            var mappe = [<%= ((String[][])(session.getAttribute("mappePosti")))[0][0] %>];
            var i = 0;
            function cambia(sel) {
                document.getElementById("ora").removeAttribute("hidden");
                var value = sel.value;
                $("#ora").empty();
                $("#ora").append("<option disabled selected> -- Seleziona un'ora -- </option>");
                for (i = 0; i < date.length; i++) {
                    if (date[i] === value) {
                        $("#ora").append("<option value=\""+i+"\">" + orari[i] + "</option>");
                    }
                }
            }            
        </script>      
    </body>
</html>
