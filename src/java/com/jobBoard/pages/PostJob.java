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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class PostJob extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("./login.jsp");
    }else{

    String notification = null;
    request.setAttribute("notification", notification);
    request.getRequestDispatcher("./postJob.jsp").forward(request,response);
    }
    
    //out.print(localDate.getDayOfWeek() + " " + localDate.getMonth()+" "+ localDate.getDayOfMonth() + ""+ localDate.getYear() );
    
    

}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String notification = (String) request.getAttribute("notification");
        String title = request.getParameter("title");
        String requirements = request.getParameter("requirements");
        String responsibility = request.getParameter("responsibility");
        String company = request.getParameter("company");

        String date = request.getParameter("date");
        if (date != null && !date.isEmpty()) {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate localDate = LocalDate.parse(date, formatter);

            Job job = new Job(title, requirements, responsibility, company, localDate, user.getId());
            

            try {
                JobDAO.createJob(job);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            request.setAttribute("notification", "Job created Successfully !");
            
            
        } else {
            request.setAttribute("notification", "Invalid date given !");
        }
        
        response.sendRedirect("./board");
    
        
}


}
