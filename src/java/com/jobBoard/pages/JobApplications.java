/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.jobBoard.pages;

import com.jobBoard.JobApplication;
import com.jobBoard.JobApplicationDAO;
import com.jobBoard.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nahom
 */
public class JobApplications extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("./login.jsp");
        }
        //String param = request.getParameter("job_id")!= null ? request.getParameter("page"): null;
        String param = request.getParameter("job_id");
        int userId = user != null ? user.getId() : -1;
        Integer job_id = param != null? Integer.valueOf(param): null;
        

        List<JobApplication> jobApplications = null;
        if(job_id != null){
            try {
                jobApplications = JobApplicationDAO.getJobApplicationsByEmployerId(userId, job_id);
                
            } catch (Exception ex) {
                Logger.getLogger(JobApplications.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            try {
                jobApplications = JobApplicationDAO.getJobApplicationsByEmployerId(userId);
            } catch (Exception ex) {
                Logger.getLogger(JobApplications.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        request.setAttribute("jobApplications", jobApplications);
        request.getRequestDispatcher("./jobApplications.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

 
}
