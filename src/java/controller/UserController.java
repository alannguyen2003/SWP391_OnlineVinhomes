/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entity.UserEntity;
import repository.UserRepository;

/**
 *
 * @author vsngh
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String controller = (String) request.getAttribute("controller");
            String action = (String) request.getAttribute("action");
            switch (action) {
                case "login":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "login_handler":
                    login_handler(request, response);
                    break;
                case "logout":
                    logout_handler(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", "error");
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }

    }

    protected void login_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String op = request.getParameter("op");
        switch (op) {
            case "submit_login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String rem = (String) request.getParameter("rem");
                Cookie cu = new Cookie("cEmail", email);
                Cookie cp = new Cookie("cPass", password);
                Cookie cr = new Cookie("cRem", rem);
                if (rem != null) {
                    cu.setMaxAge(60 * 60 * 24 * 7);
                    cp.setMaxAge(60 * 60 * 24 * 7);
                    cr.setMaxAge(60 * 60 * 24 * 7);
                } else {
                    cu.setMaxAge(0);
                    cp.setMaxAge(0);
                    cr.setMaxAge(0);
                }
                response.addCookie(cu);
                response.addCookie(cp);
                response.addCookie(cr);
                UserRepository uf = new UserRepository();
                UserEntity user = uf.Login(email, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/home/index.do");
                } else {
                    request.setAttribute("message", "Incorrect email or password");
                    request.getRequestDispatcher("/user/login.do").forward(request, response);
                }
                break;
            case "cancle":
                response.sendRedirect(request.getContextPath() + "/home/index.do");
        }
    }
    
    protected void logout_handler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //xoa session
        HttpSession session = request.getSession();
        session.invalidate();
        //quay ve home
        response.sendRedirect(request.getContextPath() + "/home/index.do");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
