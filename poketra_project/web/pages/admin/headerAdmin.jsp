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
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#form-elements" aria-expanded="false" aria-controls="form-elements">
              <i class="icon-columns menu-icon"></i>
              <span class="menu-title">Listes</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="form-elements">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/list_matiere">Matières par look</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/list_matiere_produit">Détails par matière</a></li>
                <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/recherche_modele">Rechercher</a></li>
              </ul>
            </div>
          </li>
          

        </ul>
      </nav>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
              

