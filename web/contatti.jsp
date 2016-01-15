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

        <link rel="stylesheet" type="css/my.css" href="./lib/css/mycss.css">
    </head>
    <body>
        
        <div class="container">
           
            <jsp:include page="navbar.jsp" />
            
            <div class="row">
                
                <div class="thumbnail" id="googleMap" style="width:100%;height:300px;margin:0 auto;">
                    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
                    <div style="overflow:hidden;height:100%;width:100%;">
                        <div id="gmap_canvas" style="height:290px;width:100%;"></div>
                        <style>
                            #gmap_canvas img{
                                max-width:none!important;
                                background:none!important
                            }
                        </style>
                        <a class="google-map-code" href="http://www.map-embed.com" id="get-map-data">http://www.map-embed.com</a>
                    </div>
                    <script type="text/javascript">
                        function init_map(){var myOptions = {zoom:12,center:new google.maps.LatLng(46.0681429,11.149906900000019),mapTypeId: google.maps.MapTypeId.ROADMAP};map = new google.maps.Map(document.getElementById("gmap_canvas"), myOptions);marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(46.0681429, 11.149906900000019)});infowindow = new google.maps.InfoWindow({content:"<b>MyCinema</b><br/>Via sommarive 9<br/>38121 Povo" });google.maps.event.addListener(marker, "click", function(){infowindow.open(map,marker);});infowindow.open(map,marker);}google.maps.event.addDomListener(window, 'load', init_map);
                    </script>
                </div>

                <p><b>Via Sommarive 9, Povo TN </b></p>
                <p><b>Numero di telefono: </b>0461 123456 </p>
                <p><b>Email: </b>mycinemastar@gmail.com</p>
                <p><b>Orari: </b>Lun-Ven: 15:30-00:00<br>Sab-Dom: 15:30-02:00</p>
                <p>Per rimanere sempre aggiornato sui film in proiezione, seguici su <b><a href="https://it-it.facebook.com/login">Facebook!</b></p></a>

            </div>
             
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
