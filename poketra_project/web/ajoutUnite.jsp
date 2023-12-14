<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% String message = (String) request.getAttribute("message");%>
    <body>
        <h1><%= message %></h1>
    </body>
</html>
