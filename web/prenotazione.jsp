<%-- 
    Document   : prenotazione
    Created on : 14-ago-2015, 11.36.30
    Author     : Francesco
--%>

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
            <select name="data" id="data" onchange="cambia(this)">
                <c:set var="dataScelta" value="null"/>
                <c:forEach items="${sessionScope.orariPrenotazione}" var="orari">
                    <option value="${orari.data}"><c:out value="${orari.data}"/></option>
                </c:forEach> 
            </select>
            <select name="ora" id="ora">
            </select>
        </form>
        <script>
            var orari = '<%= session.getAttribute("orariPrenotazione") %>';
            function cambia(sel){
                var value = sel.value;
                for(i=0; i<orari.length(); i++){
                    if(orari[i].getData()==value){
                        $("#ora").append("<option>Mona!<option>");
                    } 
                } 
            }   
        </script>
    </body>
</html>
