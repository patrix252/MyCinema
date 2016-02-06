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
        

        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link href="./lib/css/carousel.css" rel="stylesheet">
        
        <generalcode:navbar_header/>
        <link rel="stylesheet" href="./lib/css/mycss.css">
    </head>
    <body>
        
        <div class="container">
           
            <jsp:include page="navbar.jsp" />

            <div class="row">
            <!--  ----------------------------------- -->
                <c:forEach items="${sessionScope.filmInProgramma}" var="film" varStatus = "status">
                <div class="col-sm-4 col-lg-4 col-md-4">      
                    <div class="thumbnail">
                        <a href="descrizionefilm.jsp?id=${film.f.id_film}"><img src="${film.f.uri_locandina}"></a>
                            <div class="caption">
                                <!-- ================================================== 
                                QUI VANNO LA LOCANDINA, TITOLO, GENERE, DURATA, REGISTA, ATTORI E TRAMA IN BREVE
                                ================================================== -->
                                <p><b><a href="descrizionefilm.jsp?id=${film.f.id_film}">${film.f.titolo}</a></b></p>
                                <p>${film.f.genere}, <br> ${film.f.durata}, <br> ${film.f.regista}, <br> ${film.f.trama}!</p>
                            </div>
                    </div>
                </div>
                </c:forEach>    
            <!--  ----------------------------------- -->
            </div>
            
            
             
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
