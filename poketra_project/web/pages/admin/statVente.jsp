 <%-- 
    Document   : statVente
    Created on : 25 janv. 2024, 15:15:54
    Author     : ITU
--%>

<%@page import="modele.Statistique"%>
<%@page import="modele.Modele"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  Modele modele=(Modele) request.getAttribute("modele");
  ArrayList<Statistique> stats=modele.getStatistique(null);
%>

<jsp:include page="headerAdmin.jsp" />
    <script src="<%= request.getContextPath() %>/assets/vendors/chart.js/Chart.min.js"></script>
    
        <div class="col-md-12 grid-margin stretch-card">
        <div class="col-lg-6 grid-margin grid-margin-lg-0 stretch-card">     
            <div class="card">
              <div class="card-body">
                  <h4 class="card-title">
                      <% if(modele.getId().equals("all")) { %>
                        Toutes les modèles
                      <% } else { %>
                        <%= modele.getType().getNom() %> <%= modele.getLook().getNom() %> <%= modele.getTaille().getNom() %>
                      <% } %>
                  </h4>
                  <div class="table-responsive">
                    <table class="table">
                      <thead>
                        <tr>
                          <th>Genre</th>
                          <th>Nombre</th>
                          <th>Pourcentage</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% for(int i=0;i<stats.size();i++) { %>
                            <tr>
                                <td><%= stats.get(i).getGenre().getNom() %></td>
                                <td><%= stats.get(i).getNombre() %></td>
                                <td><%= Math.round(stats.get(i).getPourcentage()) %> %</td>
                            </tr>
                        <% } %>
                      </tbody>
                    </table>
                   </div>
                </div>
            </div>
        </div>
                  
        <div class="col-lg-6 grid-margin grid-margin-lg-0 stretch-card">     
            <div class="card">
              <div class="card-body">
                    <h4 class="card-title">
                      <% if(modele.getId().equals("all")) { %>
                        Toutes les modèles
                      <% } else { %>
                        <%= modele.getType().getNom() %> <%= modele.getLook().getNom() %> <%= modele.getTaille().getNom() %>
                      <% } %>
                    </h4>
                  <canvas id="<%= modele.getId().concat("pieChart") %>"></canvas>
              </div>
            </div>
        </div>
       </div>
    
     
        <script>
                
                var doughnutPieData = {
                  datasets: [{
                    data: [
                        <% 
                            for(int i=0;i<stats.size();i++)
                            {
                                out.println(Math.round(stats.get(i).getPourcentage())+",");
                            }
                        %>
                        ],
                     

                    backgroundColor: [
                      <% 
                            for(int i=0;i<stats.size();i++)
                            {
                                out.println("'"+stats.get(i).getGenre().getCouleur()+"',");
                            }
                        %>
                    ],
                    borderColor: [
                        <%
                            for(int i=0;i<stats.size();i++)
                            {
                                out.println("'"+stats.get(i).getGenre().getCouleur()+"',");
                            }
                        %>
                    ],
                  }],

                  // These labels appear in the legend and in the tooltips when hovering different arcs
                  labels: [
                    <%
                            for(int i=0;i<stats.size();i++)
                            {
                                out.println("'"+stats.get(i).getGenre().getNom()+" "+Math.round(stats.get(i).getPourcentage())+"% ',");
                            }
                        %>
                  ]
                };

                var doughnutPieOptions = {
                  responsive: true,
                  animation: {
                    animateScale: true,
                    animateRotate: true
                  }
                };

                var pieChartCanvas = document.getElementById("<%= modele.getId().concat("pieChart") %>").getContext("2d");
                var pieChart = new Chart(pieChartCanvas, {
                  type: 'pie',
                  data: doughnutPieData,
                  options: doughnutPieOptions
                });

</script>
<jsp:include page="footerAdmin.jsp" />
