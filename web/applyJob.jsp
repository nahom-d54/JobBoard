<%-- 
    Document   : applyJob
    Created on : 27 Jan 2024, 15:25:28
    Author     : nahom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="./WEB-INF/jspf/header.jsp">
    <jsp:param name="title" value="Apply for a job"/>
</jsp:include>
<jsp:include page="./WEB-INF/jspf/navbar.jspf"/>
<%@page import="com.jobBoard.Job" %>

<%
    Job job = (Job) request.getAttribute("job");
    int job_id = (int) request.getAttribute("job_id");
%>


<div class="container">
    <h1 class="my-3 text-center text-light">Apply for a job</h1>
  <div class="row justify-content-center my-4">
    <div class="col-md-6  justify-content-center d-flex flex-column mx-auto">
        <div class="card w-100 bg-secondary text-light">
            <div class="card-body">
                <h5 class="card-title"><%= job.getTitle() %></h5>
                <h6 class="card-subtitle mb-2 text-muted"><%= job.getCompany() %></h6>
                
                <a class="link-info" data-bs-toggle="collapse" href="#collapse-req-<%= job.getId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                    <h6 class="card-text mb-2">Requirements</h6>
                     </a>               
                       <div class="collapse" id="collapse-req-<%= job.getId()%>">
                          <p>
                            <c:out value=${job.requirements} />
                            <%= job.getRequirements() %>
                          </p>
                       </div>                    
                         <a class="link-info" data-bs-toggle="collapse" href="#collapse-res-<%= job.getId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                             <h6 class="card-text mb-2">Responsibility</h6>
                         </a>
                       <div class="collapse" id="collapse-res-<%= job.getId()%>">
                            <p>
                                <%= job.getResponsibility()%>
                            </p>
                        </div>
                       <hr>
                
                
                <p class="card-text mt-3">Deadline: January 31, 2024</p>
            </div>
        </div>
      <form method="post">
        <div class="mb-3">
          <label for="application" class="form-label">Application Letter</label>
          <input type="hidden" name="job_id" value="<%= job_id %>">
          <textarea name="application_letter" class="form-control" id="application" rows="5" placeholder="Enter Your application letter" required></textarea>
        </div>
        <button type="submit" class="btn btn-outline-info col-12">Apply</button>
      </form>
    </div>
  </div>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
