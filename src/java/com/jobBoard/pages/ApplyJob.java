/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.jobBoard.pages;

import com.jobBoard.Job;
import com.jobBoard.JobApplication;
import com.jobBoard.JobApplicationDAO;
import com.jobBoard.JobDAO;
import com.jobBoard.User;
import java.io.IOException;

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
public class ApplyJob extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("./login.jsp");
        }
        
        int job_id = Integer.parseInt(request.getParameter("job_id"));
        Job job = null;
        try{
            job = JobDAO.getJobById(job_id);
        }catch(Exception e){}
        if(job == null){
            response.sendRedirect("./board.jsp");
        }
        request.setAttribute("notification", "mesaage");
        //String notification = (String) request.getAttribute("notification");
        request.setAttribute("job", job);
        request.setAttribute("job_id", job_id);
        request.getRequestDispatcher("./applyJob.jsp").forward(request, response);
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("./login.jsp");
        }
        else {
            String application_letter = request.getParameter("application_letter");
            int job_id = Integer.parseInt(request.getParameter("job_id"));
            JobApplication jobApplication = new JobApplication();
            jobApplication.setApplication_letter(application_letter);
            jobApplication.setJob_seeker(user.getId());
            jobApplication.setJob_id(job_id);
            JobApplicationDAO jobApplicationDAO = new JobApplicationDAO();

            try {
                jobApplicationDAO.applyJob(jobApplication);
                request.setAttribute("notification", "Job applied Successfully !");
            } catch (Exception ex) {
                Logger.getLogger(ApplyJob.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("notification", "Job application Error !");
            }

            response.sendRedirect("./board");
        }
        
    }


}
