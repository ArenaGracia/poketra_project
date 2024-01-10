<%-- 
    Document   : recherchePrix
    Created on : 9 janv. 2024, 14:35:57
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Inserer deux prix</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/recherche_modele" method="post">
           <div class="form-group">
            <label for="exampleInputPassword1">Prix 1</label>
            <input type="text" class="form-control" name="min">
          </div>  
          <div class="form-group">
            <label for="exampleInputPassword1">Prix 2</label>
            <input type="text" class="form-control" name="max">
          </div>             
          <input type="submit" value="Valider" class="btn btn-primary mr-2" />
        </form>
      </div>
    </div>
  </div>    
<jsp:include page="footerAdmin.jsp" />

