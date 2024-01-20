<%-- 
    Document   : ajoutType
    Created on : 19 déc. 2023, 14:28:04
    Author     : ASUS
--%>
<%@page import="modele.Specialite"%>
<%@page import="modele.Genre"%>
<%@page import="java.util.ArrayList"%>
<%
  String message=(String) request.getAttribute("message");
   String erreur=(String) request.getAttribute("erreur");
   String modele=(String) request.getAttribute("modele");
   ArrayList<Specialite> specs=(ArrayList<Specialite>) request.getAttribute("specs");
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
<div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Ajout des besoins </h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_modele_specialite" method="post">
          <div class="form-group">
            <input type="hidden" class="form-control" id="exampleInputUsername1" name="modele" value="<%= modele %>" />
          </div>
          
          <div class="form-group">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th>Specialité</th>
                        <th>Nombre</th>
                        <th>Durée</th>
                    </tr>
                    <% for(int i=0;i<specs.size();i++) { %>
                        <tr>
                            <td><label><%= specs.get(i).getNom() %></label></td>
                            <td><input type="text" class="form-control" name="nb" style="width:70px" /></td>
                            <td><input type="text" class="form-control" name="duree" style="width:70px" /></td>
                        </tr>
                        
                    <% } %>
                </table>
              
            </div>
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