<%-- 
    Document   : index
    Created on : 14 déc. 2023, 10:13:17
    Author     : Toky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><strong>Ajout Unite</strong></h1>
        <form action="/add_unite" method="POST">
            <label>Nom de l'unite :  <input type="text" name="nom"></label><br>
            <input type="submit" value="Valider">
        </form>
    </body>
</html>
