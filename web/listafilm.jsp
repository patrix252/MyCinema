<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="generalcode" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<html>
    <head>
        <title>Lista Film</title>
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
        
        <div class="container">
                    
            <jsp:include page="navbar.jsp" />
            
            <h2>Gestisci film</h2>
            <div class='form-group'>
                <select  id="films" class="form-control" onchange="showSpettacoli(this.value)" >
                    <label for="sel1">Select list:</label>
                    <option id="first_film">-- Seleziona un film --</option>
                    <c:forEach items="${sessionScope.filmsAll}" var = "fa">
                        <option value="${fa.id_film}"><c:out value="${fa.titolo}"/></option>
                    </c:forEach>    
                </select>
            </div>

            <div class='form-group'>
                <select id="spettacoli" class="form-control"  onchange="getIncassoSpettacolo(this.value)" hidden>
                </select>
            </div>
            <div class='row' id='info-container'>
                <div id="incasso_film"></div>
                <div id="incasso_spettacolo"></div>
                
            </div>
        </div>
    </body>
</html>