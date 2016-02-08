<%-- 
    Document   : account
    Created on : 8-feb-2016, 11.20.38
    Author     : Francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Account</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="./lib/css/mycss.css">
        <title>Account</title>
    </head>
    <body>
        <div class="container">

            <h1>Il tuo account</h1>
            <table class="table">
                <thead>
                    <tr>
                        <td><b>Nome</b></td>
                        <td><b>Cognome</b></td>
                        <td><b>Email</b></td>
                        <td><b>Credito</b></td>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${sessionScope.utente.getNome()}</td>
                        <td>${sessionScope.utente.getCognome()}</td>
                        <td>${sessionScope.utente.getEmail()}</td>
                        <td>${sessionScope.utente.getCredito()}€</td>

                    </tr>
                </tbody>
            </table>

        </div>
    </body>
</html>
