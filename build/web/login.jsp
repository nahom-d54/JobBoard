<%-- 
    Document   : login
    Created on : 25 Jan 2024, 16:41:29
    Author     : nahom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.jobBoard.UserDAO"%>
<%@page import="com.jobBoard.User" %>
<%@page import="com.jobBoard.PasswordOprations" %>
<%
        User user = (User) session.getAttribute("user");
        if(user != null){
            response.sendRedirect("./board");
        }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
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
            <h5 class="card-title text-center">Login</h5>
            <form method="post">
                <%
  
    // Check if the request method is POST
    if (request.getMethod().equalsIgnoreCase("POST")) {
        

        // Example: Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Example: Validate the form data
        if (username != null && password != null) {
            UserDAO userDao = new UserDAO();
            User thisUser = userDao.getUserByUsername(username);
            boolean login = PasswordOprations.verifyPassword(password, thisUser.getPassword());
            
            if(login == true){
                session.setAttribute("user", thisUser);
                response.sendRedirect("./board");
            }else{
            out.println("Incorrect username or password");
            }
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
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" name="password" placeholder="Enter your password">
              </div>
              <div class="text-center">
                <button type="submit" class="btn btn-primary col-12">Login</button>
              </div>
              <p class="p text-center my-2">You don't have an account ? <a href="./register.jsp">Register</a></p>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="./assets/bootstrap.bundle.min.js"></script>
</body>
</html>