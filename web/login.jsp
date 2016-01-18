<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <title>Login</title>
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
          
            
            <h1>Nuovo cliente?</h1>

                
            <form id="modulo" class="form-inline" name="modulo" action="LoginServlet" method="POST">
                <div class="form-group">
                    <div class="row">
                        <h2 class="text-center"><b>Impostazioni account</b></h2>
                    </div>
                    
                    <div class="row">
                        <dl class="dl-horizontal">
                            <label style="display: block;">Mail:</label><input id="mail" name="Mail" size="10"/><br>
                            <label style="display: block;">Password:</label><input type="password" id="password" name="Password" size="10"/><br>
                            <div class="row">
                            <div class="col-lg-12">
                                <button type="submit" class="btn btn-lg center-block btn-success">Login</button>
                            </div>
                        </div>
                        </dl>
                    </div>
                    
                    <c:if test="${sessionScope.EmailErrata==true}">
                        <p style="color: red;">Email non valida, reinserisci i dati</p>
                        <c:set var="EmailErrata" value="false" scope="session"/>
                    </c:if>
                    
                   
                </div>

            </form>
            
            <c:if test="${sessionScope.loginError}">
                <p style="color: red;">Email o password non valide, reinserisci i dati!</p>
                <c:set var="loginError" value="false" scope="session"/>
            </c:if>
            <c:if test="${sessionScope.problemaConnessione}">
                <p style="color: red;">Problema a contattare il server, riprova tra qualche minuto!</p>
                <c:set var="problemaConnessione" value="false" scope="session"/>
            </c:if>
            <div>Nuovo utente? Registrati <a href="registrazione.jsp">qui</a></div>
            
            <jsp:include page="navbar.jsp" />
            <jsp:include page="footer.jsp" />
        </div>
        
       
    </body>
</html>
