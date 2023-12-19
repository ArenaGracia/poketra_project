<%-- 
    Document   : ajoutTaille
    Created on : 19 déc. 2023, 14:26:04
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
        <h4 class="card-title">Ajout Unite</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_taille" method="post">
          <div class="form-group">
            <label for="exampleInputUsername1">Taille</label>
            <input type="text" class="form-control" id="exampleInputUsername1" name="taille" placeholder="taille">
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
