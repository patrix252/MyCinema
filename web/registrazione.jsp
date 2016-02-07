<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registrazione</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" type="css/my.css" href="./lib/css/mycss.css">
    </head>
    <body>
        
        
        <div class="container">
            <jsp:include page="navbar.jsp" />
            
            <h1>Nuovo cliente?</h1>

                
            <form id="modulo" class="form-inline" name="modulo"  onSubmit="return controllo();" action="registrazione.jsp" method="POST">
                <div class="form-group">
                    <div class="row">
                        <h2 class="text-center"><b>Impostazioni account</b></h2>
                    </div>
                    
                    <div class="row">
                        <dl class="dl-horizontal">
                            <dt><h4>Nome:</h4></dt>
                            <dd><input id="nome" class="form-control" type="text" name="Nome" size="10" placeholder="Nome" style="width: 100%"/></dd>
                            <dt><h4>Cognome:</h4></dt>
                            <dd><input id="cognome" class="form-control" type="text" name="Cognome" size="10" placeholder="Cognome" style="width: 100%"/></dd>

                            <dt><h4>Data di nascita:</h4></dt>
                            <dd>
                                <select id="daydropdown" class="form-control" name="Giorno"></select> 
                                <select id="monthdropdown" class="form-control" name="Mese"></select> 
                                <select id="yeardropdown" class="form-control" name="Anno"></select> 
                            </dd>
                            <dt><h4>Mail:</h4></dt>
                            <dd><input id="mail" class="form-control" type="text" name="Mail" placeholder="yourmail@sample.com" size="10" style="width: 100%"/></dd>
                            <dt><h4>Password:</h4></dt>
                            <dd><input id="password" class="form-control" type="password" name="Password" size="10" placeholder="Password" style="width: 100%"/></dd>
                        </dl>
                    </div>
                    
                    <c:if test="${sessionScope.EmailErrata==true}">
                        <p style="color: red;">Email non valida, reinserisci i dati</p>
                        <c:set var="EmailErrata" value="false" scope="session"/>
                    </c:if>
                    
                    <div class="row">
                        <div class="col-lg-12">
                            <button type="submit" class="btn btn-lg center-block btn-success">Crea Account!</button>
                        </div>
                    </div>
                </div>

            </form>
            
            <jsp:include page="footer.jsp" />
        </div>

        
        <script type="text/javascript">
            window.onload = function () {
                populatedropdown("daydropdown", "monthdropdown", "yeardropdown");
            };
            
            function controllo() {
                with (document.modulo) {
                    if (nome.value == "") {
                        alert("Errore: compilare il campo NOME");
                        nome.focus();
                        return false;
                    }
                    if (cognome.value == "") {
                        alert("Errore: compilare il campo COGNOME");
                        cognome.focus();
                        return false;
                    }
                    if (mail.value == "") {
                        alert("Errore: compilare il campo MAIL");
                        cognome.focus();
                        return false;
                    }
                    if (password.value == "") {
                        alert("Errore: compilare il campo PASSWORD");
                        cognome.focus();
                        return false;
                    }
                }
                return true;
            }
            
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
                for (var y = 0; y < 80; y++) {
                    yearfield.options[y] = new Option(thisyear - 15, thisyear - 15);
                    thisyear -= 1;
                }
                yearfield.options[0] = new Option(today.getFullYear() - 15, today.getFullYear() - 15, true, true);
            }
        </script>

    </body>
</html>
