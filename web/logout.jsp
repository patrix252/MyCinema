<%-- 
    Document   : logout
    Created on : 8-ott-2015, 13.39.55
    Author     : Paolo
--%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sei stato disconnesso con successo!</h1>
        <%session.invalidate();%>
        <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 1500);
        </script>
    </body>
</html>
