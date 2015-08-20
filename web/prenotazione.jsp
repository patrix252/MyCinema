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


<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mystyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prenotazione</title>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>        
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
        <script>
            var date = [<%= values.toString()%>];
            var orari = [<%= values1.toString()%>];
            var i = 0;
            function cambia(sel) {
                document.getElementById("ora").removeAttribute("hidden");
                var value = sel.value;
                $("#ora").empty();
                $("#ora").append("<option disabled selected> -- Seleziona un'ora -- </option>");
                for (i = 0; i < date.length; i++) {
                    if (date[i] == value) {
                        $("#ora").append("<option>" + orari[i] + "</option>");
                    }
                }
            }

        </script>
        
    </head>
    <body>
        <h1>Titolo film : <c:out value="${param.titolo}" /></h1>
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
            <select name="ora" id="ora" hidden>
            </select>   
         </form>
                
                
                
        <%--         
            Per fare la mappa dei posti studiarsi il codice di sti 2 siti qua
            https://github.com/mateuszmarkowski/jQuery-Seat-Charts
            http://www.goocode.net/js/73-jquery-election-seat-reservations-online-theater-piece.html
        --%>
        
        
    </body>
</html>
