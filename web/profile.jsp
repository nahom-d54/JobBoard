<%@page import="com.jobBoard.User"%>
<jsp:include page="./WEB-INF/jspf/header.jsp">
    <jsp:param name="title" value="Felega | Profile"/>
</jsp:include>
<jsp:include page="./WEB-INF/jspf/navbar.jspf"/>
<% User user = (User) session.getAttribute("user"); %>
<form class="container w-25 row g-3 bg-secondary text-light mx-auto p-3 my-2 rounded border border-info" method="post">
   <div class="col-md-12">
    <label for="inputName" class="form-label">Name</label>
    <input type="text" class="form-control" id="inputName" name="name" value="<%= user.getName() %>">
  </div>
   <div class="col-md-12">
    <label for="inputUsername" class="form-label">username</label>
    <input type="text" class="form-control" id="inputUsername" name="username" value="<%= user.getUsername() %>">
  </div>
  <div class="col-md-12">
    <label for="inputEmail4" class="form-label">Email</label>
    <input type="email" class="form-control" id="inputEmail4" name="email" value="<%= user.getEmail() %>">
  </div>
  <div class="col-md-12">
    <label for="inputPassword4" class="form-label">Password</label>
    <input type="password" class="form-control" id="inputPassword4" name="password">
  </div>

  <div class="col-12">
    <button type="submit" class="btn btn-primary">Submit</button>
  </div>
</form>
<jsp:include page="./WEB-INF/jspf/footer.jspf"/>
