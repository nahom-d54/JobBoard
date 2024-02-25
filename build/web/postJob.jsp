<%-- 
    Document   : postJob
    Created on : 26 Jan 2024, 12:25:14
    Author     : nahom
--%>
<%@page import="com.jobBoard.User" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>

<%@page import="com.jobBoard.Job" %>
<%@page import="com.jobBoard.JobDAO" %>


<%
    String notification = (String) request.getAttribute("notification");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./WEB-INF/jspf/header.jsp">
    <jsp:param name="title" value="Post Job"/>
</jsp:include>
<%@include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="container">
    <% if(notification != null){ %>
            <jsp:include page="/WEB-INF/jspf/toast.jsp">
                <jsp:param name="notification" value="<%= notification%>"/>
                <jsp:param name="title" value="info"/>
            </jsp:include>
        <% }%>
    <h1 class="my-3 text-center text-light">Post a job</h1>
  <div class="row justify-content-center my-4">
    <div class="col-md-6 text-light">
      <form method="post">
        <div class="mb-3">
          <label for="jobTitle" class="form-label">Job Title</label>
          <input name="title" type="text" class="form-control" id="jobTitle" placeholder="Enter job title" required>
        </div>
        <!--<div class="mb-3">
          <label for="jobTitle" class="form-label">Salary / compensation </label>
          <input name="salary" type="text" class="form-control" id="jobTitle" placeholder="Enter job title" required>
        </div> -->
        <div class="mb-3">
          <label for="requirements" class="form-label">Requirements</label>
          <textarea name="requirements" class="form-control" id="requirements" rows="4" placeholder="Enter job requirements" required></textarea>
        </div>
        <div class="mb-3">
          <label for="responsibility" class="form-label">Responsibility</label>
          <textarea name="responsibility" class="form-control" id="responsibility" rows="4" placeholder="Enter job responsibility" required></textarea>
        </div>
        <div class="mb-3">
          <label for="company" class="form-label">Company</label>
          <input name="company" type="text" class="form-control" id="company" placeholder="Enter company name" required>
        </div>
        <div class="mb-3">
          <label for="postedAt" class="form-label">Deadline</label>
          <input name="date" type="date" class="form-control" id="postedAt" required min="<% out.print(LocalDate.now()); %>">
        </div>
        <button type="submit" class="btn btn-outline-primary col-12">Post Job</button>
      </form>
    </div>
  </div>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>