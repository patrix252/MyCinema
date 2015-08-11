<%-- 
    Document   : registrazione
    Created on : 11-ago-2015, 16.12.04
    Author     : Paolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script type="text/javascript">
            var monthtext = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];

            function populatedropdown(dayfield, monthfield, yearfield) {
                var today = new Date();
                var dayfield = document.getElementById(dayfield);
                var monthfield = document.getElementById(monthfield);
                var yearfield = document.getElementById(yearfield);
                for (var i = 0; i < 31; i++)
                    dayfield.options[i] = new Option(i+1, i + 1);
                dayfield.options[today.getDate()] = new Option(today.getDate(), today.getDate(), true, true); //select today's day
                for (var m = 0; m < 12; m++)
                    monthfield.options[m] = new Option(monthtext[m], m+1);
                monthfield.options[today.getMonth()] = new Option(monthtext[today.getMonth()], monthtext[today.getMonth()], true, true); //select today's month
                var thisyear = today.getFullYear();
                for (var y = 0; y < 80; y++) {
                    yearfield.options[y] = new Option(thisyear-15, thisyear-15);
                    thisyear -= 1;
                }
                yearfield.options[0] = new Option(today.getFullYear()-15, today.getFullYear()-15, true, true); //select today's year
            }

        </script>

    </head>
    <body>
        <div>TODO write content</div>
        <form action="RegisterServlet" method="POST">
            <label style="display: block;">Nome:</label><input id="nome" name="Nome" size="10"/><br>
            <label style="display: block;">Cognome:</label><input id="cognome" name="Cognome" size="10"/><br>
            <label style="display: block;">Data di Nascita:</label>
            <select id="daydropdown" name="Giorno"></select> 
            <select id="monthdropdown" name="Mese"></select> 
            <select id="yeardropdown" name="Anno"></select> 
            <label style="display: block;">Mail:</label><input id="mail" name="Mail" size="10"/><br>
            <label style="display: block;">Password:</label><input type="text" id="password" name="Password" size="10"/><br>
            <input type="submit" value="Submit"/>
        </form>
        <script type="text/javascript">
            window.onload = function () {
                populatedropdown("daydropdown", "monthdropdown", "yeardropdown");
            };
        </script>
    </body>
</html>
