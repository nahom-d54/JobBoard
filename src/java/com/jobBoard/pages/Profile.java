/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.jobBoard.pages;

import com.jobBoard.PasswordOprations;
import com.jobBoard.User;
import com.jobBoard.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nahom
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("./login.jsp");
            return;
        }
        request.getRequestDispatcher("./profile.jsp").forward(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            User user = (User) request.getSession().getAttribute("user");
            User toUpdate = new User();
            toUpdate.setEmail(email);
            toUpdate.setName(name);
            toUpdate.setUsername(username);
            String hashedPassword;
            if(password != null){
                hashedPassword = PasswordOprations.hashPassword(password);
            }else{
                hashedPassword = user.getPassword();
            }
            
            toUpdate.setPassword(hashedPassword);
            toUpdate.setId(user.getId());
            UserDAO.updateUser(user);
        try {
            User usr = new UserDAO().getUserById(user.getId());
            request.getSession().setAttribute("user", usr);
        } catch (Exception ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            response.sendRedirect("./board");
            
            
    }


}
