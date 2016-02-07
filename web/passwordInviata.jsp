<%-- 
    Document   : passwordInviata
    Created on : Feb 7, 2016, 3:23:25 PM
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
        <c:if test="${sessionScope.errorePassword==true}">
            <h1>C'è stato un errore nell'invio della password, la preghiamo di riprovare inserendo nuovamente la sua email!</h1>
            <script>
            setTimeout(function(){ window.location.replace("passwordDimenticata.jsp"); }, 3000);
            </script>
        </c:if>
        <c:if test="${sessionScope.errorePassword==false}">
            <h1>La sua password è stata inviata per email, la preghiamo di controllare la sua casella postale!</h1>
            <script>
            setTimeout(function(){ window.location.replace("index.jsp"); }, 3000);
            </script>
        </c:if>
    </body>
</html>
