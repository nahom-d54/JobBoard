<%-- 
    Document   : board
    Created on : 26 Jan 2024, 11:35:08
    Author     : nahom
--%>

<%@page import="com.jobBoard.Job"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>

<%
    List<Job> jobList = (List<Job>) request.getAttribute("jobList");
    boolean search = (boolean)request.getAttribute("search");
%>
<jsp:include page="./WEB-INF/jspf/header.jsp">
    <jsp:param name="title" value="Filega | Dashboard" />
</jsp:include>
<%@include file="/WEB-INF/jspf/header.jspf" %>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        
        
        <div class="container d-flex justify-content-center ">
            <div class="mt-2" >
                <div class="row justify-content-center" id="job-list"id="job-list">
                <% for(Job job: jobList) { %>
                
                    <div class="card col-lg-3 d-flex col-md-4 col-sm-12 mb-2 gap-2 mx-2 bg-secondary text-light">
                        <div class="card-header text-center">JOB</div>
                        <div class="card-body">
                            <h5>Title: <%= job.getTitle()%></h5>
                            
                            
                            <a class="link-info" data-bs-toggle="collapse" href="#collapse-req-<%= job.getId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                               <h6>Requirements</h6>
                            </a>
                            
                            <div class="collapse" id="collapse-req-<%= job.getId()%>">
                                <p>
                                    <%= job.getRequirements() %>
                                </p>
                            </div>
                            
                            <a class="link-info" data-bs-toggle="collapse" href="#collapse-res-<%= job.getId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                               <h6>Responsibility</h6>
                            </a>
                            <div class="collapse" id="collapse-res-<%= job.getId()%>">
                                <p>
                                    <%= job.getResponsibility()%>
                                </p>
                            </div>
                            <hr>
                            <h5>Company: <%= job.getCompany()%></h5>
                            
                            <small>Deadline: <% 
                                String dayOfWeek = job.getDeadline().getDayOfWeek().toString().substring(0, 3); 
                                String monthOfYear = job.getDeadline().getMonth().toString().substring(0, 3);
                                int dayOfMonth = job.getDeadline().getDayOfMonth();
                                int year = job.getDeadline().getYear();
                                out.print(dayOfWeek + " " + monthOfYear + " " + dayOfMonth + " - "+ year);
                            %></small>
                            <a href="./applyJob?job_id=<%= job.getId() %>" _blank="true" class="my-3 col col-12 btn btn-outline-info">Apply</a><!<!-- google form link -->
                        </div>
                    </div>
                
                
                <% } %> 
                </div>
            </div>
           
        </div>
 <% if(!search){ %>
                    
  <script>
  var pageNumber = 1;
  window.addEventListener('scroll', function() {
    var scrollPosition = window.pageYOffset || document.documentElement.scrollTop;
    var totalHeight = document.documentElement.scrollHeight;
    var windowHeight = window.innerHeight;
    
    if (scrollPosition + windowHeight >= totalHeight) {
        // Perform an HTTP request
        var request = new XMLHttpRequest();
        request.open('POST', "http://localhost:8080/jobBoard/board", true);
        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        request.onreadystatechange = function() {
            if (request.readyState === 4 && request.status === 200) {
                var jobl = document.querySelector("#job-list");
                
                jobl.innerHTML += request.responseText;
                //jobl.appendChild(newElement);
                console.log(request.responseText, jobl)
            }
        };
        var params = new URLSearchParams(window.location.search)
        pageNumber = pageNumber+1;
        request.send("page="+ pageNumber);
    }
});
</script>
<% } %>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
