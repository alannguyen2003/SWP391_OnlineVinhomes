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
import entity.ResidentEntity;
import entity.EmployeeEntity;
import entity.ToastEntity;
import service.ResidentService;
import service.EmployeeService;
import org.apache.tomcat.jni.SSLContext;
import service.UserService;

/**
 *
 * @author vsngh
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    private ResidentService residentService = new ResidentService();
    private EmployeeService employeeService = new EmployeeService();
    private UserService userService = new UserService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String controller = (String) request.getAttribute("controller");
            String action = (String) request.getAttribute("action");
            HttpSession session = request.getSession();
            UserEntity user = (UserEntity) session.getAttribute("user");
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
                case "signup":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;

                case "signup_handler":
                    signUpHandler(request, response);
                    break;
                case "change-password":
                    try {
                    ToastEntity toast = null;
                    String message = null;
                    String password = request.getParameter("password");
                    String newPassword = request.getParameter("newPassword");
                    String confirmPassword = request.getParameter("confirmPassword");

                    if (!user.getPassword().equals(password)) {
                        if (newPassword != null) {
                            message = "Current password does not correct!";
                            toast = new ToastEntity("Current password does not correct!", "failed");
                        }
                    } else if (!newPassword.equals(confirmPassword)) {
                        message = "Confirmation is differ from new password";
                        toast = new ToastEntity("Confirmation is differ from new password", "failed");
                    } else {
                        String aid = Integer.toString(user.getAID());
                        userService.changePass(aid, newPassword);
                        user.setPassword(newPassword);
                        session.setAttribute("user", user);
                        message = "Changed password successfully";
                        toast = new ToastEntity("Changed password successfully", "success");
                    }
                    if (message != null) {
                        request.setAttribute("message", message);
                    }
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                case "profile":
                    try {
                    String aid = Integer.toString(user.getAID());
                    if (user.getRoleID() == 1) {
                        user = userService.getUser(aid);
                        request.setAttribute("res", user);
                        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    } else {
                        admindashboard(request, response);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                case "updateRoom":
                    update(request, response);
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

    protected void signUpHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            int blockId = Integer.parseInt(request.getParameter("bid"));
            UserEntity entity = new UserEntity();
            entity.setEmail(email);
            entity.setName(name);
            entity.setPhone(phone);
            entity.setBID(blockId);
            entity.setPassword(password);
            boolean check = userService.addNewResident(entity);
            if (check) {
                request.setAttribute("message", "Please login again.");
                request.getRequestDispatcher("/user/login.do").forward(request, response);
            } else {
                request.setAttribute("message", "Email exist! Please choose another one!!");
                request.getRequestDispatcher("/user/signup.do").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                    if (user.getRoleID() == 4 || user.getRoleID() == 3) {
                        response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/home/index.do");
                    }
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

    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String room = request.getParameter("room");
        int AID = Integer.parseInt(request.getParameter("AID"));
        residentService.updateRoom(room, AID);
        response.sendRedirect(request.getContextPath() + "/user/profile.do");
    }
    
    protected void admindashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        String aid = Integer.toString(user.getAID());
        if (user.getRoleID() == 4 || user.getRoleID()== 3 ){
                        UserEntity entity;
                        entity = userService.getUser(aid);
                        request.setAttribute("user", entity);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/user/login.do");
                    }
    }

}
