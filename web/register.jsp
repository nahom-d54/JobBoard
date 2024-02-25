<%-- 
    Document   : register
    Created on : 25 Jan 2024, 16:45:16
    Author     : nahom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.jobBoard.UserDAO"%>
<%@page import="com.jobBoard.User" %>

<%
        User usr = (User) session.getAttribute("user");
        if(usr != null){
            response.sendRedirect("./board");
        }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registration Page</title>
  <link rel="stylesheet" href="./assets/bootstrap.min.css">
</head>
<body>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-5">
        <div class="card border-0">
            <div class="my-3 text-center">
                <img src="./assets/icons/radar.svg" width="50" height="50" alt="Logo"/>
            </div>
          <div class="card-body">
            <h5 class="card-title text-center">Register</h5>
            <form method="Post">
                <%
  
    // Check if the request method is POST
    if (request.getMethod().equalsIgnoreCase("POST")) {
        // Perform your logic here for POST requests
        // ...

        // Example: Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Example: Validate the form data
        if (username != null && password != null && email != null ) {
            User user = new User(username, password, email);
            UserDAO userDao = new UserDAO();
            userDao.createUser(user);
            out.println("Registered Successfully go to login.");
        } else {
            out.println("Invalid form data!");
        }
    }
%>
              <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" name="username" placeholder="Enter your username">
              </div>
              <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" name="email" placeholder="Enter your email address">
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" name="password" placeholder="Enter your password">
              </div>
              <div class="mb-3">
                <label for="confirm-password" class="form-label">Confirm Password</label>
                <input type="password" class="form-control" name="confirm-password" placeholder="Confirm your password">
              </div>
              <div class="text-center">
                <button type="submit" class="btn btn-primary col-12">Register</button>
              </div>
              <p class="p text-center my-2">Already have an account ? <a href="./login.jsp">Login</a></p>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="./assets/bootstrap.bundle.min.js"></script>
</body>
</html>
