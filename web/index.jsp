
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Job Board</title>
  <link rel="stylesheet" href="./assets/bootstrap.min.css" >
  <style>
      .hero {
          background: linear-gradient(to bottom right,#0b0c10, #1f2833);
          height: 91vh;
      }
      .hero h1 {
          font-size: 4rem;
          color: #66fcf1;
      }
      .hero p {
          font-size: 1.5rem;
          color: #fff;
      }
      .hero button {
          
      }
  </style>
  
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="#">Filega</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Services</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
       
      </ul>
    </div>
  </div>
</nav>
<section class="hero d-flex align-items-center text-center">
  <div class="container">
    <h1>Filega</h1>
    <p>Building Bridges between Talent and Companies: Your Trusted Job Board Companion</p>
    <a href="./login.jsp" class="btn btn-outline-info p-3 px-5" >Login</a>
    <a href="./register.jsp" class="btn btn-outline-info p-3 px-5" >Register</a>
  </div>
</section>

  <script src="./assets/bootstrap.bundle.min.js"></script>

</body>
</html>
