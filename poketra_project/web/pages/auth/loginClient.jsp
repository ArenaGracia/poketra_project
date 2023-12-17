<%-- 
    Document   : login
    Created on : 11 déc. 2023, 12:16:07
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="row w-100 mx-0">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
              <div class="brand-logo">
                <img src="<%= request.getContextPath() %>/assets/images/logo.svg" alt="logo">
              </div>
              <h4>Bonjour! Client </h4>
              <h6 class="font-weight-light">Connectez-vous pour voir plus.</h6>
              <form class="pt-3">
                <div class="form-group">
                  <input type="email" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Username">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="mt-3">
                  <input type="submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" value="Se connecter"</a>
                </div>
                <div class="text-center mt-4 font-weight-light">
                  <a href="./Auth?status=admin" class="text-primary">Se connecter en tant qu'administrateurs</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="<%= request.getContextPath() %>/assets/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="<%= request.getContextPath() %>/assets/js/off-canvas.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/hoverable-collapse.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/template.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/settings.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/todolist.js"></script>
  <!-- endinject -->
</body>

</html>

