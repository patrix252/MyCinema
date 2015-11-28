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
        <link rel="stylesheet" type="css/my.css" href="./lib/mycss.css">

        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <generalcode:navbar_header/>
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
                        <th class="col-md-1" data-field="3D">3D</th>
                    </tr>
                    <tr class="active">
                        <td>INTERO - DOMENICA E FESTIVI</td>
                        <td>€ 5,50</td>
                        <td>€ 8,50</td>
                    </tr> 
                    <tr class="active">
                        <td>INTERO - DA LUNEDÌ A SABATO</td> 
                        <td>€ 4,50</td>
                        <td>€ 7,50</td>
                    </tr> 
                    <tr class="danger">
                        <td>RIDOTTO (fino a 14 anni) - TUTTI I GIORNI</td>
                        <td>€ 4,00</td>
                        <td>€ 7,00</td>
                    </tr> 
                    <tr class="danger">
                        <td>DISABILI - TUTTI I GIORNI</td>
                        <td>€ 4,00</td>
                        <td>€ 7,00</td>
                    </tr> 
                  </table>
                </div>
            </div>

             
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
