<%-- 
    Document   : cancellaprenotazioni
    Created on : 7-feb-2016, 15.06.48
    Author     : Francesco
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Cancella prenotazione </title>
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>Selezionare prenotazione da cancellare </h1>
        <table>
            <tr>
                <td>Email</td>
                <td>Data prenotazione</td>
                <td>Ora prenotazione</td>
                <td>Cancella</td>
            </tr>
    <c:forEach items="${sessionScope.prenotazioni}" var = "pr">
        <tr>
            <td> ${pr.id_utente}</td>
            <td> ${pr.data} </td>
            <td> ${pr.ora}</td>
            <td><a class="btn btn-default" href="DeletePrenotation?id=${pr.id_prenotazione}">Cancella</a> </td>
        </tr>
        
    </c:forEach>
        </table>
    </body>
</html>
