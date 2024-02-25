/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.jobBoard.pages;

import com.jobBoard.Job;
import com.jobBoard.JobDAO;
import com.jobBoard.User;
import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nahom
 */
public class MyJobs extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("./login.jsp");
        }else {
            List<Job> jobList = null;
            int userId = user.getId();
            try {
                jobList = JobDAO.getJobsByUserId(userId);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            request.setAttribute("jobList", jobList);
            request.getRequestDispatcher("./myJobs.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
       
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        int deleteParam = Integer.parseInt(request.getParameter("delete"));
        try {
            JobDAO.deleteJob(deleteParam);
            String notification = "Job deleted successfully!";
            request.setAttribute("notification", notification);
            processRequest(request, response);
        } catch (Exception ex) {
            String notification = "Job deletion failed!";
            request.setAttribute("notification", notification);
            processRequest(request, response);
            ex.printStackTrace(); 
        }
  
        
           
    }

}
