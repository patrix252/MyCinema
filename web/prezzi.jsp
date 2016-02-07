<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="generalcode" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>


<!DOCTYPE html>
<html>
    <head>
        <title>Prezzi</title>
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
            
            <div class="row">
                <div class="table-responsive">
                  <table class="table table-striped">
                     <tr>
                        <th class="col-md-3" data-field="giorno"></th>
                        <th class="col-md-1" data-field="2D">2D</th>
                    </tr>
                    <tr class="active">
                        <td>INTERO - DOMENICA E FESTIVI</td>
                        <td>€ 8</td>
                    </tr> 
                    <tr class="danger">
                        <td>RIDOTTO (fino a 14 anni) - DISABILI - SOLDATI - STUDENTI</td>
                        <td>€ 5,00</td>
                    </tr>
                  </table>
                </div>
            </div>

             
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
