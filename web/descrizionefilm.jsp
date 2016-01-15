<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="film" value="${sessionScope.film}"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Descrizione film</title>
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
            
            <div class="row" style="padding-bottom: 80px;">
                <h1 style="margin-bottom: 30px"><c:out value="${film.titolo}"/></h1>
            </div>
            
            <div class="row">

                <div class="col-lg-6">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="${film.url_trailer}"></iframe>
                    </div>
                </div>

                <div class="col-lg-6">
                    <p class="text-left"><b>Genere: </b>${film.genere.descrizione}</p>
                    <p class="text-left"><b>Durata: </b>${film.durata}</p>
                    <p class="text-left"><b>Regista: </b>${film.regista}</p>
                    <p class="text-left"><b>3D: </b><c:choose><c:when test="${film.is3D==0}">No</c:when><c:otherwise>Sì</c:otherwise></c:choose></p>

                    <p class="text-left"><b>Trama: </b>${film.trama}</p>
                    <p><a class="btn btn-success" role="button" href="PrenotationServlet?id=${param.id}&titolo=${film.titolo}" style="margin-top: 20px">Prenota subito!</a></p>
                </div>
            </div>
            
            <jsp:include page="footer.jsp" />
        </div>
    </body>
</html>
