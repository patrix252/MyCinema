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

            <c:forEach items="${sessionScope.orariPrenotazione}" var="orari">
                <tr>
                    <td><c:out value="${orari.data}"/> </td>  
                    <td><c:out value="${orari.ora}"/> </td>      



                </tr>    
            </c:forEach>       




        </table>    










    </body>
</html>
