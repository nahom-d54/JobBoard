<%-- 
    Document   : myJobs
    Created on : 26 Jan 2024, 12:37:32
    Author     : nahom
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./WEB-INF/jspf/header.jsp">
    <jsp:param name="title" value="My job list"/>
</jsp:include>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@page import="com.jobBoard.Job" %>
<%@page import="java.util.List" %>

<%
    String notification = (String) request.getAttribute("notification");
    List<Job> jobList = (List<Job>) request.getAttribute("jobList");
%>

<% if(notification != null){ %>
            <jsp:include page="/WEB-INF/jspf/toast.jsp">
                <jsp:param name="notification" value="<%= notification%>"/>
                <jsp:param name="title" value="info"/>
            </jsp:include>
        <% }%>
<jsp:include page="/WEB-INF/jspf/navbar.jspf"></jsp:include>
<% for(Job job: jobList) { %>
<div class="container col-md-4 my-2">
          <div class="card flex-md-row mb-4 box-shadow h-md-250 text-light">
            <div class="card-body d-flex flex-column align-items-start">
              <h5 class="mb-0">
                <a class="text-dark" href="#"><%= job.getTitle()%></a>
              </h5>
              <div class="mb-1 text-muted"><small>
                      Deadline: <% 
                                String dayOfWeek = job.getDeadline().getDayOfWeek().toString().substring(0, 3); 
                                String monthOfYear = job.getDeadline().getMonth().toString().substring(0, 3);
                                int dayOfMonth = job.getDeadline().getDayOfMonth();
                                int year = job.getDeadline().getYear();
                                out.print(dayOfWeek + " " + monthOfYear + " " + dayOfMonth + " - "+ year);
                            %></small></div>
              <a class="link-secondary" data-bs-toggle="collapse" href="#collapse-req-<%= job.getId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                               <h6>Requirements</h6>
                            </a>
                            
                            <div class="collapse" id="collapse-req-<%= job.getId()%>">
                                <p>
                                    <%= job.getRequirements() %>
                                </p>
                            </div>
            </div>
                                <div class="card-right">
                                    <a class="btn" href="./jobApplications?job_id=<%= job.getId()%>">Applications</a>
                                    <form method="post">
                                        <div class="card-right border border-top-0 border-bottom-0 flex-auto d-flex text-white justify-center align-center text-center">
                                            <input type="hidden" name="delete" value="<% out.print(job.getId());%>">
                                            <button type="submit" role='button' class="btn d-flex justify-self-center align-self-center link-danger link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover mx-3">Delete</button>
                                        </div>
                                      </form>
                                </div>
              
                    
          </div>
</div>
<% } %> 
<%@include file="/WEB-INF/jspf/footer.jspf" %>
