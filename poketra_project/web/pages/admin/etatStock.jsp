<%-- 
    Document   : etatStock
    Created on : 11 janv. 2024, 14:18:44
    Author     : ASUS
--%>
<%@page import="modele.Stock"%>
<%@page import="modele.Matiere"%>
<%@page import="modele.Modele"%>
<%@page import="java.util.ArrayList"%>
<%
   ArrayList<Matiere> liste=(ArrayList<Matiere>) request.getAttribute("matieres");
   ArrayList<Stock> stock=(ArrayList<Stock>) request.getAttribute("liste");
//   out.print(stock.size());
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
<div class="col-md-12 grid-margin stretch-card">
<div class="card">
    <div class="card-body">
        <h4 class="card-title">Etat de stock des matières</h4>
        <form class="form-inline" action="<%= request.getContextPath() %>/stock_matiere" method="post">
            <div class="input-group mb-2 mr-sm-2">
                <select class="form-control" name="matiere">
                <% if(liste!=null) { %>
                    <option value="%">Tout</option>
                    <% for(int i=0;i<liste.size();i++) { %>
                        <option value="<%= liste.get(i).getId()%>"><%= liste.get(i).getNom() %></option>
                    <% } %>
                <% } %>
                </select>
            </div>
           
          <input type="submit" value="Valider" class="btn btn-primary mr-2" />
        </form>
     
    
    <% if(stock!=null) { %>
            <div class="table-responsive">
              <table class="table">
                <thead>
                  <tr>
                    <th>Matiere</th>
                    <th>Nombre</th>
                  </tr>
                </thead>
                <tbody>
                  <% for(int i=0;i<stock.size();i++) { %>
                      <tr>
                        <td><%= stock.get(i).getMatiere().getNom() %></td>
                        <td><%= stock.get(i).getReste()%></td>
                      </tr>
                  <% } %>
                </tbody>
              </table>
        <% } %>
            </div>
    </div>
</div>
</div>
<jsp:include page="footerAdmin.jsp" />
