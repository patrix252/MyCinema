<%-- 
    Document   : postazioni
    Created on : 14-ago-2015, 15.31.15
    Author     : Francesco
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prenotazione posto</title>
</head>
<body>
<table>
<%for(int i=0;i<10;i++){%><tr>
<%for(int j=i*10;j<(i+1)*10;j++){%>

<td>
   
    <input type="submit" value="<%=(j+1) %>" >
</td>

<%}%></tr><%} %>
</table>
</body>
</html>
