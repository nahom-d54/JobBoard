/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.jobBoard.pages;

import com.jobBoard.Job;
import com.jobBoard.JobDAO;
import com.jobBoard.User;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Board extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("./login.jsp");
               return;
        }
        request.setAttribute("search", false);
        //todo: convert to static method
            JobDAO jobs = new JobDAO();
            int pageSize = 5;
            
            List<Job> jobList = null;
            try {
                jobList = jobs.getJobsByPage(1, pageSize);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String notification = (String) request.getAttribute("notification");

            request.setAttribute("notification", notification);
            request.setAttribute("jobList", jobList);
            request.getRequestDispatcher("./board.jsp").forward(request, response);
        
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("./login.jsp");
               return;
        }
        
        //todo: convert to static method
            JobDAO jobs = new JobDAO();
            int pageSize = 5;
            Integer pageNumber;
            pageNumber = Integer.valueOf(request.getParameter("page") != null ? request.getParameter("page"): "1");

            if(pageNumber < 1){
                pageNumber = 1;
            }
            List<Job> jobList = null;
            try {
                jobList = jobs.getJobsByPage(pageNumber, pageSize);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String notification = (String) request.getAttribute("notification");

            request.setAttribute("notification", notification);
            request.setAttribute("jobList", jobList);
            request.getRequestDispatcher("./WEB-INF/jspf/jobpage.jsp").forward(request, response);
    }


}
