<%-- 
    Document   : prenotazione
    Created on : 14-ago-2015, 11.36.30
    Author     : Francesco
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.Spettacolo"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prenotazione</title>
    </head>
    <body>
        <h1>Titolo film : <c:out value="${param.titolo}" /></h1>
        <table  style="width:100%">
            <tr>
                <td><h4>Data</h4></td>
                <td><h4>Ora</h4></td>
            </tr>
        </table>
        <form id="prenota" name="prenota">
            <%
                //Questo passaggio serve per eliminare le date duplicate, tanto per la medesima data vengono
                //prese tutte le ore nella funzione in javascript
                List<Spettacolo> temp = (ArrayList<Spettacolo>)(session.getAttribute("orariPrenotazione"));
                Set<Date> insieme = new HashSet<>();

                for (int i = 0; i < (int) ((ArrayList) (session.getAttribute("orariPrenotazione"))).size(); i++) {
                    insieme.add(temp.get(i).getData());
                }
                session.setAttribute("orari", insieme);
            %>
            <select name="data" id="data" onchange="cambia(this)">
                <c:set var="dataScelta" value="null"/>
                <c:forEach items="${sessionScope.orari}" var="orari">
                    <option value="${orari}"><c:out value="${orari}"/></option>
                </c:forEach>
            </select>
            <select name="ora" id="ora">
            </select>
        </form>
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
                    var value = sel.value;
                    $("#ora").empty();
                    for (i = 0; i < date.length; i++) {
                        if (date[i] == value) {
                            $("#ora").append("<option>" + orari[i] + "<option>");
                        }
                    }
                }
        </script>
    </body>
</html>
