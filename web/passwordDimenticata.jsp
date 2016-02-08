
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Password dimenticata</title>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" type="css/my.css" href="./lib/css/mycss.css">
    </head>
    
    
    
    
    <body>
        
        <jsp:include page="navbar.jsp" />
        
        <div class="container" style="margin-bottom: 100px">
                
            
            <h3> Inserisci la tua mail qua sotto, ti sarà rimandata la password all'indirizzo indicato! </h3>
            
             
            <div class="row">
                <div class="col-md-6 col-offset-3">
                    <form class="form-inline" action="PasswordReminder">
                        <input id="Mail" class="form-control" name="Mail" type="text" placeholder="email@google.com" style="width:100%;margin-top: 20px"> <br>
                        <input class="btn btn-default" type="submit" style="margin-top: 20px">
                    </form>
                </div>
            </div>
        
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
