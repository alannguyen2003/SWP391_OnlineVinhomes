/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.ResetPassCodeEntity;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import repository.UserRepository;
import service.GmailService;
import service.UserService;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/reset-pass"})
public class ResetPasswordController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        try {
            switch (action) {
                case "reset-pass":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "reset-pass-handler":
                    resetPassHandler(request, response);
                    break;
                case "reset-pass-handler-commit":
                    resetPassCommit(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void resetPassHandler(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
        UserService userService = new UserService();
        request.setAttribute("action", "reset-pass");
        String email = (String) request.getParameter("email");
        if (userService.checkEmailExist(email) != null) {
            int index = email.indexOf("@");
            GmailService gmailer = new GmailService();
            String code = gmailer.getRandomCode();
            ResetPassCodeEntity rsCode = new ResetPassCodeEntity();
            rsCode.setCode(code);
            String emailName = email.substring(0, index);
            HttpSession session = request.getSession();
            session.setAttribute("validator", rsCode);
            String link = "http://localhost:8080/vsos/reset-pass/reset-pass-handler-commit.do?email=" + emailName + "%40gmail.com&op=newCommit&code=" + code;
            String subject = "This is automated email. Do not response.";
            String body = "Click this link to reset your account password: " + link;
            gmailer.sendEmail(subject, body, email);
            String message = "We have sent you a link in your email account. Please check your Email.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } else {
            String message = "Email does not exist.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void resetPassCommit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String email = (String) request.getParameter("email");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        ResetPassCodeEntity rsCode = (ResetPassCodeEntity) session.getAttribute("validator");
        if (rsCode.getCode().equals(code)) {
            String op = (String) request.getParameter("op");
            switch (op) {
                case "newCommit":
                    request.setAttribute("email", (String) request.getParameter("email"));
                    request.setAttribute("action", "reset-pass-handler");
                    request.setAttribute("code", rsCode.getCode());
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "changeCommit":
                    String newPassword = (String) request.getParameter("newPassword");
                    String reEnter = (String) request.getParameter("reEnter");

                    if (newPassword.equals(reEnter)) {
                        UserService userService = new UserService();
                        userService.resetPass(email, newPassword);
                        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    } else {
                        request.setAttribute("action", "reset-pass-handler");
                        request.setAttribute("code", (String) request.getParameter("code"));
                        request.setAttribute("message", "You need to correctly re-enter the new password");
                        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    }
                    break;
            }
        } else {
            request.setAttribute("message", "Unauthorized accessing detected!");
            request.setAttribute("action", "reset-pass");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
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
