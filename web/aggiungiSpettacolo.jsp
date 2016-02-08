
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="generalcode" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<html>
    <head>
        <title> Aggiungi spettacolo </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
          
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" href="./lib/css/mycss.css">
        <script src="./lib/js/gestioneFilmAdmin.js"></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        
        <div class="container">
            
            <div class="row">
                
                <form action="AddSpettacolo" method="POST">
                    <h4>Film:</h4>
                    <div class="form-group">
                        <select class="form-control" name="film">
                            <option value="null" disabled> -- Seleziona un film -- </option>
                            <c:forEach items="${sessionScope.films}" var="film">
                            <option value="${film.id_film}"><c:out value="${film.titolo}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <h4>Sala:</h4>
                    <div class="form-group">
                        <select class="form-control" name="sala">
                            <option value="null" disabled> -- Seleziona una sala -- </option>
                            <option value="1"> Sala 1</option>
                            <option value="2"> Sala 2</option>
                            <option value="3"> Sala 3</option>
                            <option value="4"> Sala 4</option>
                        </select>
                    </div>
                    
                    
                    <h4>Data:</h4>
                    
                    <jsp:useBean id="now" class="java.util.Date"/>    
                    
                    
                    

                    <input class="form-control" type="text" name="data" placeholder="2017-08-14" value="<fmt:formatDate value="${now}" pattern="dd-MM-yyyy" />">
                    
                    <h4>Ora:</h4>
                    <input class="form-control" type="text" name="ora" placeholder="21:00:00" value="<fmt:formatDate value="${now}" pattern="HH:mm:ss" />">
                    
                    <input class="btn btn-default" type="submit" value="Submit" style="margin-top: 20px">

                </form>
            </div>
            
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
        
</html>
