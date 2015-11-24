<%-- 
    Document   : index
    Created on : 12-ago-2015, 18.36.35
    Author     : Paolo
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="generalcode" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>




<!DOCTYPE html>
<html>
    <head>
        <title>Index page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="css/my.css" href="./lib/mycss.css">


        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <!-- carousel -->
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <link href="./lib/css/carousel.css" rel="stylesheet">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <generalcode:navbar_header/>
    </head>
    <body>
        
        <div class="container">
           
            <jsp:include page="navbar.jsp" />
            
            <!-- Carousel
            ==================================================
                sessionScope.filmInProgrammaLength = numero film in filmInProgramma
                sessionScope.filmInProgramma = lista di film da visualizzare nel carosel
            -->
            <div class="row" style="padding-top: 80px;">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <c:forEach var="i" begin="0" end="${sessionScope.filmInProgrammaLength-1}" varStatus = "status">
                        <li data-target="#myCarousel" data-slide-to="${i}" class="${status.first ? 'active' : ''}"></li>
                        </c:forEach>
                    </ol>
                    <div class="carousel-inner" role="listbox">        
                        <c:forEach items="${sessionScope.filmInProgramma}" var="film" varStatus = "status">
                        <div class="item ${status.first ? 'active' : ''}">
                            <img class="second-slide" src="${film.f.uri_locandina}" alt="${film.f.titolo}">
                            <div class="container">
                                <div class="carousel-caption">
                                    <h1>${film.f.titolo}</h1>
                                    <p>non so cosa mettere</p>
                                    <p><a class="btn btn-lg btn-primary" href="http://localhost:8080/MyCinema/descrizionefilm.jsp?id=${film.f.id_film}" role="button">Scheda film</a></p>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div><!-- /.carousel -->
            </div>
            
            <hr>
            <div class="row">
            <!--  ----------------------------------- -->
                <c:forEach items="${sessionScope.filmInProgramma}" var="film" varStatus = "status">
                <div class="col-sm-4 col-lg-4 col-md-4">      
                    <div class="thumbnail">
                        <a href="filmdettaglio.html"><img src="${film.f.uri_locandina}"></a>
                            <div class="caption">
                                <!-- ================================================== 
                                QUI VANNO LA LOCANDINA, TITOLO, GENERE, DURATA, REGISTA, ATTORI E TRAMA IN BREVE
                                ================================================== -->
                                <p><b><a href="filmdettaglio.html">Titolo Film</a></b></p>
                                <p>${film.f.genere}, <br> ${film.f.durata}, <br> ${film.f.regista}, <br> ${film.f.trama}!</p>
                            </div>
                    </div>
                </div>
                </c:forEach>    
            <!--  ----------------------------------- -->
            </div>
            
            
            
            
            <div class="row">
                <h1>Index Page</h1><br>
                <c:if test="${sessionScope.utente.getEmail()!=null}">
                    Benvenuto <c:out value="${sessionScope.utente.getNome()}"/><br>
                </c:if>
                <a href="oggialcinema.jsp"> - Oggi al cinema</a><br>
                <a href="filminprogramma.jsp"> - Tutti i film in programma</a><br>
                <c:if test="${sessionScope.utente.getEmail()!=null}">
                    <a href="logout.jsp"> - Log Out</a><br>
                </c:if>
                <c:if test="${sessionScope.utente.getEmail()==null}">
                    <a href="registrazione.jsp"> - Registrazione Test</a><br>
                    <a href="LoginServlet"> - Login Test</a>
                </c:if>
            </div>
             
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
