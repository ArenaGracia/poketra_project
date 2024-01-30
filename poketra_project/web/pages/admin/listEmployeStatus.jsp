<%-- 
    Document   : listEmployeStatus
    Created on : 23 janv. 2024, 15:02:27
    Author     : ASUS
--%>
<%@page import="modele.Employe"%>
<%@page import="java.util.ArrayList"%>
<%
   ArrayList<Employe> liste=(ArrayList<Employe>) request.getAttribute("employes");
   
//   out.print(stock.size());
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerAdmin.jsp" />
<div class="col-md-12 grid-margin stretch-card">
<div class="card">
    <div class="card-body">
        <h4 class="card-title">Liste des salaires des employés</h4>
        <form class="form-inline" action="<%= request.getContextPath() %>/list_employe_status" method="post">
            
          <div class="form-group">
            <input type="date" class="form-control" id="exampleInputUsername1" name="embauche">
          </div>
          <input type="submit" value="Valider" class="btn btn-primary mr-2" />
        </form>
        <% if(liste!=null) { %>
            <div class="table-responsive">
              <table class="table">
                <thead>
                  <tr>
                    <th>Nom</th>
                    <th>Status</th>
                    <th>Specialite</th>
                    <th>Taux horaires</th>
                  </tr>
                </thead>
                <tbody>
                  <% for(int i=0;i<liste.size();i++) { %>
                      <tr>
                        <td><%=liste.get(i).getNom()%> <%=liste.get(i).getPrenom()%></td>
                        <td><%=liste.get(i).getStatus().getNom()%></td>
                        <td><%=liste.get(i).getSpecialite().getNom()%></td>
                        <td><%=liste.get(i).getSpecialite().getSalaire()%></td>
                      </tr>
                  <% } %>
                </tbody>
              </table>
               </div>
        <% } %>
            
    </div>
</div>
</div>
<jsp:include page="footerAdmin.jsp" />
