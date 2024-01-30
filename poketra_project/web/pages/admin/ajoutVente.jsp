<%@page import="modele.*"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Modele> listeModele=(ArrayList<Modele>) request.getAttribute("modele");
    ArrayList<Client> listeClient=(ArrayList<Client>) request.getAttribute("client");
    String message = (String)request.getAttribute("message");
    String erreur = (String)request.getAttribute("erreur");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
    <div class="col-md-6 grid-margin stretch-card">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Inserer vente</h4>
        <form class="forms-sample" action="<%= request.getContextPath() %>/add_vente" method="post">
                <div class="form-group">
                    <label for="exampleInputUsername1">Modele </label>
                    <select class="form-control" name="modele" id="modele">
                    <% if(listeModele!=null) { %>
                        <% for(int i=0;i<listeModele.size();i++) { %>
                        <%  String temp = listeModele.get(i).getTaille().getNom() + " " + listeModele.get(i).getLook().getNom() + " " + listeModele.get(i).getType().getNom(); %>
                            <option value="<%= listeModele.get(i).getId()%>"><%= temp %></option>
                        <% } %>
                    <% } %>
                    </select>
                </div>
            <div class="form-group">
                <label for="client">Client </label>
                <select class="form-control" name="client" id = "client">
                <% if(listeClient!=null) { %>
                    <% for(int i=0;i<listeClient.size();i++) { %>
                    <%  String temp = listeClient.get(i).getNom(); %>
                        <option value="<%= listeClient.get(i).getId()%>"><%= temp %></option>
                    <% } %>
                <% } %>
                </select>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Nombre</label>
              <input type="number" class="form-control" name="nombre">
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
