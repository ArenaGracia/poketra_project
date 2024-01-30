<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Skydash Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/feather/feather.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/css/vendor.bundle.base.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/mdi/css/materialdesignicons.min.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/feather/feather.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.png" />

</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" ><img src="<%= request.getContextPath() %>/assets/images/logo.svg" class="mr-2" alt="logo"/></a>
        <a class="navbar-brand brand-logo-mini" ><img src="<%= request.getContextPath() %>/assets/images/logo-mini.svg" alt="logo"/></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <div class="navbar-toggler navbar-toggler align-self-center " type="div">
          <span class="mdi mdi-logout menu-icon"></span>
        </div>
      </div>
    </nav>

    <div class="container-fluid page-body-wrapper">
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
              <i class="icon-layout menu-icon"></i>
              <span class="menu-title">Ajouts</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-basic">
              <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_unite">Unité</a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_matiere">Matières</a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_look">Look</a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_taille">Taille</a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_type">Type</a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_matiere_look">Matières par look</a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/list_type_taille">Modèle et détails</a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_matiere_prix">Prix d'une matière </a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_matiere_stock">Stock d'une matiere</a></li>
                <li class="nav-item"> <a class="nav-link" href="<%= request.getContextPath() %>/add_fabrication">Fabrication</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_specialite_modele">Specialités utiles</a></li>

              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#tables" aria-expanded="false" aria-controls="tables">
              <i class="icon-columns menu-icon"></i>
              <span class="menu-title">Listes</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="tables">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/list_matiere">Matières par look</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/list_matiere_produit">Détails par matière</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/recherche_modele">Rechercher</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/recherche_benefice">Bénéfice</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/stock_matiere">Matières</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/list_employe_status">Employés</a></li>

              </ul>
            </div>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#icons" aria-expanded="false" aria-controls="icons">
              <i class="icon-columns menu-icon"></i>
              <span class="menu-title">RH</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="icons">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_genre">Ajout Genre</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_client">Ajout Client</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_specialite">Ajout Specialité</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_specialite_prix">Salaire par Specialité</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_employe">Ajout Personne</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_date_embauche">Embaucher</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_prix_vente">Ajouter un prix</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_status">Ajouter un status</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/add_vente">Ajouter un vente</a></li>
              </ul>
            </div>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#charts" aria-expanded="false" aria-controls="charts">
              <i class="icon-columns menu-icon"></i>
              <span class="menu-title">Statistiques</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="charts">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/stat_vente">Statistique de vente</a></li>
              </ul>
            </div>
          </li>
          

        </ul>
      </nav>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
              

