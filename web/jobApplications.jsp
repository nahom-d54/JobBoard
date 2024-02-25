<%-- 
    Document   : jobApplications
    Created on : 28 Jan 2024, 10:03:52
    Author     : nahom
--%>
<%@page import="java.util.List" %>
<%@page import="com.jobBoard.JobApplication" %>

<jsp:include page="./WEB-INF/jspf/header.jsp">
    <jsp:param name="title" value="Job Applicants"/>
</jsp:include>
<jsp:include page="/WEB-INF/jspf/navbar.jspf"></jsp:include>
<%
   List<JobApplication> jobApplications = (List<JobApplication>) request.getAttribute("jobApplications");
%>
<div class="container">
    <% for(JobApplication ja : jobApplications) {
    %>
        <div class="card col-6 mx-auto my-2 border-0 shadow-sm">
            <div class="card-title mx-3 py-1 border-bottom border-1"><%= ja.getJob_seeker().getUsername() %></div>
            <div class="card-body">
                <p>
                    <%= ja.getApplication_letter() %>

                </p>
            </div>
        </div>
    <%}%>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
