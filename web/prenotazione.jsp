<%-- 
    Document   : prenotazione
    Created on : 14-ago-2015, 11.36.30
    Author     : Francesco
--%>

<%@page import="java.util.LinkedHashSet"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.Spettacolo"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //Questo passaggio serve per eliminare le date duplicate, tanto per la medesima data vengono
    //prese tutte le ore nella funzione in javascript
    List<Spettacolo> temp = (ArrayList<Spettacolo>) (session.getAttribute("orariPrenotazione"));
   
    //Uso un LinkedHashSet per non avere date ripetute ma per mantenere l'ordine di inserimento
    //Visto che nella mia ArrayList le ho già ordinate
    Set<Date> insieme = new LinkedHashSet<>();
    for (int i = 0; i < (int) ((ArrayList) (session.getAttribute("orariPrenotazione"))).size(); i++) {
        if (i == 0) {
            session.setAttribute("primaData", temp.get(i).getData());
        }
        insieme.add(temp.get(i).getData());
    }
    session.setAttribute("orari", insieme);
%>

<%
    StringBuffer values = new StringBuffer();
    for (int i = 0; i < (int) ((ArrayList) (session.getAttribute("orariPrenotazione"))).size(); ++i) {
        if (values.length() > 0) {
            values.append(',');
        }
        values.append('"').append(((Spettacolo) ((ArrayList) (session.getAttribute("orariPrenotazione"))).get(i)).getData()).append('"');
    }
    StringBuffer values1 = new StringBuffer();
    for (int i = 0; i < (int) ((ArrayList) (session.getAttribute("orariPrenotazione"))).size(); ++i) {
        if (values1.length() > 0) {
            values1.append(',');
        }
        values1.append('"').append(((Spettacolo) ((ArrayList) (session.getAttribute("orariPrenotazione"))).get(i)).getOra()).append('"');
    }
%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="noi">
        <link rel="icon" type="image/ico" href="img/logo.ico">
        <title>Prenotazioni on-line!</title>
        
        <link rel="stylesheet" type="text/css" href="./lib/seat-charts.css">
        <link rel="stylesheet" type="text/css" href="./lib/seat-charts2.css">
        <link rel="stylesheet" type="css/my.css" href="./lib/mycss.css">
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
        <script src="./lib/seat-charts.min.js"></script> 
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
    </head>
    <body>
       
        <!-- INSERIRE NAVBAR -->
        
        <div class="container">
            <div class="row">   
                <c:set var="film" value="${param.titolo}"/>
                <h1>Titolo film : <c:out value="${film}" /></h1>
            </div>
            
            <div class="row">
                <table style="width:26%">
                    <tr>
                        <td><h4>Data</h4></td>
                        <td><h4>Ora</h4></td>
                    </tr>
                </table>
            
                <form id="prenota" name="prenota">
                    <select name="data" id="data" onchange="cambia(this)">
                        <option disabled selected> -- Seleziona una data -- </option>
                        <c:set var="dataScelta" value="null"/>
                        <c:forEach items="${sessionScope.orari}" var="orari">
                            <option value="${orari}"><c:out value="${orari}"/></option>
                        </c:forEach>
                    </select>
                    <select name="ora" id="ora" onchange="mappa()" hidden></select>   
                </form>
            </div>
                        
            
            <div class="row" style="padding-top: 5%;">
                <div class="col-sm-6 col-lg-6 col-md-6">
                    <div id="seat-map" class="noselect">
                        <div class="front-indicator">Front</div>
                    </div>
                    
                    <div id="legend"></div>
                </div>

                <div class="col-sm-3 col-lg-3 col-md-3">    
                    <p><b>Riepilogo:</b></p>
                    <p><b>n° posti interi: </b><span id="counter_intero">0</span></p>
                    <p><b>n° posti ridotti: </b><span id="counter_ridotto">0</span></p>
                    <p><b>Prezzo totale: </b>€ <span id="total">0</span></p>
                    <a href="pagamento.jsp" id="link"><button class="btn center-block btn-success">Acquista!</button></a>
                    
                    
                    
                </div>
                <br>
                <div class="col-sm-3 col-lg-3 col-md-3">

                    <p><b>Credito disponibile:</b> 0</p>
                 
                    <p>Ricarica di:</p>
                        <div class="col-xs-4">
                        </div>
                        <div class="form-group col-xs-4" style="padding: 0;">
                            <select class="form-control" id="InputType">
                            <option>€ 5,00</option>
                            <option>€ 10,00</option>
                            <option>€ 20,00</option>
                            <option>€ 50,00</option>
                            </select>
                        </div>
                        <div class="col-xs-4">
                        </div>    
                    <div class="col-xs-12">
                        <button type="submit"  class="btn center-block btn-danger">Ricarica!</button>
                    </div>

                </div>
            </div>
                        
        </div>
            
        <!-- footer -->    
            
            
        <script>
            var price_normale = 10;
            var price_ridotto = 5;
            var firstSeatLabel = 1;
		
            function mappa() {
                    var $cart = $('#selected-seats'),
                    $counter_intero = $('#counter_intero'),
                    $counter_ridotto = $('#counter_ridotto'),
                    $total = $('#total'),
                    sc = $('#seat-map').seatCharts({
                        map: [
                                'ffffffffff',
                                'ffffffffff',
                                'ffffffffff',
                                'ffeffffeff',
                                '__________',
                                'ffffffffff',
                                'ffffffffff',
                                'ffffffffff',
                                'ffffffffff',
                        ],
                        seats: {
                            f: {
                                    price   : 100,
                                    classes : 'first-class', //your custom CSS class
                                    category: 'First Class'
                            },
                            e: {
                                    price   : 40,
                                    classes : 'first-class economy-class', //your custom CSS class
                                    category: 'Economy Class'
                            }					

                        },
                        naming : {
                            top : false,
                            getLabel : function (character, row, column) {
                                    return firstSeatLabel++;
                            },
                        },
                        legend : {
                            node : $('#legend'),
                            items : [
                                        [ 'f', 'available',   'First Class' ],
                                        [ 'e', 'available',   'Economy Class'],
                                        [ 'f', 'unavailable', 'Already Booked']
                            ]					
                        },
                        click: function () {
                            if (this.status() == 'available') {
                                    
                                
                                    //let's create a new <li> which we'll add to the cart items
                                    $('<li>'+this.data().category+' Seat # '+this.settings.label+': <b>$'+this.data().price+'</b> <a href="#" class="cancel-cart-item">[cancel]</a></li>')
                                            .attr('id', 'cart-item-'+this.settings.id)
                                            .data('seatId', this.settings.id)
                                            .appendTo($cart);

                                    /*
                                     * Lets update the counter and total
                                     *
                                     * .find function will not find the current seat, because it will change its stauts only after return
                                     * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.

     *             */
                                    $counter_intero.text(sc.find('selected').length+1);
                                    $total.text(recalculateTotal(sc)+price_normale);

                                    return 'selected';
                            } else if (this.status() == 'selected') {
                                    //update the counter
                                    $counter_intero.text(sc.find('selected').length-1);
                                    $counter_ridotto.text(sc.find('selected_ridotto').length+1);
                                    //and total
                                    $total.text(recalculateTotal(sc)-price_normale);
                                    $total.text(recalculateTotal(sc)+price_ridotto);


                                    //seat has been vacated
                                    return 'selected_ridotto';
                            } else if (this.status() == 'selected_ridotto') {
                                    //update the counter
                                    $counter_ridotto.text(sc.find('selected_ridotto').length-1);
                                    //and total
                                    $total.text(recalculateTotal(sc)-price_ridotto);

                                    //seat has been vacated
                                    return 'available';
                            } else if (this.status() == 'unavailable') {
                                    //seat has been already booked
                                    return 'unavailable';
                            } else {
                                    return this.style();
                            }
                        }
                    });

                    //this will handle "[cancel]" link clicks
                    $('#selected-seats').on('click', '.cancel-cart-item', function () {
                            //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
                            sc.get($(this).parents('li:first').data('seatId')).click();
                    });

                    //let's pretend some seats have already been booked

                    //DA SOSTITUIRE CON QUERY AL DB PER VEDERE QUALI POSTI SONO PRENOTATI!!!!!!!!!

                    sc.get(['1_2', '4_1', '7_1', '7_2']).status('unavailable');

            }

            function recalculateTotal(sc) {
                var total = 0;

                //basically find every selected seat and sum its price
                sc.find('selected').each(function () {
                    total += price_normale;
                });
                sc.find('selected_ridotto').each(function () {
                    total += price_ridotto;
                });

                return total;
            }
		
            var numeroSpettacolo;
            /***********************************/
            $("#ora").click(function(){
                $("#link").attr("href", "pagamento.jsp?ns="+$("#ora").val());
            });
            
            var date = [<%= values.toString()%>];
            var orari = [<%= values1.toString()%>];
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
