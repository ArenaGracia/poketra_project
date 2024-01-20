<%-- 
    Document   : ajoutType
    Created on : 19 déc. 2023, 14:28:04
    Author     : ASUS
--%>
<%
  String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
<div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Ajout Genre</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_genre" method="post">
          <div class="form-group">
            <label for="exampleInputUsername1">Genre</label>
            <input type="text" class="form-control" id="exampleInputUsername1" name="genre" placeholder="genre">
          </div>
          <%if(message != null){ %>
            <div class="alert alert-success" role="alert">
                <p><%= message %></p>
            </div>
          <%   } %>
          <%if(erreur != null){ %>
            <div class="alert alert-danger" role="alert">
                <p><%= erreur %></p>
            </div>
          <%   } %>
          <input type="submit" value="Valider" class="btn btn-primary mr-2" />
        </form>
      </div>
    </div>
  </div> 
<jsp:include page="footerAdmin.jsp" />

