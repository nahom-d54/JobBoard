<%-- 
    Document   : jobpage
    Created on : 19 Feb 2024, 21:12:51
    Author     : nahom
--%>
<%@page import="java.util.List"%>
<%@page import="com.jobBoard.Job"%>
<%
    List<Job> jobList = (List<Job>) request.getAttribute("jobList");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <a href="./applyJob?job_id=<%= job.getId() %>" _blank="true" class="my-3 col col-12 btn btn-outline-primary">Apply</a><!<!-- google form link -->
                        </div>
                    </div>
                
                
                <% } %> 
