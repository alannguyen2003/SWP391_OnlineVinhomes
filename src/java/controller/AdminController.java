/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import service.ResidentService;
import service.UserService;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    private ResidentService rs = new ResidentService();
    private UserService us = new UserService();

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
        HttpSession adminSession = request.getSession(true);
        UserEntity user = (UserEntity) adminSession.getAttribute("user");
        if (user != null && (user.getRoleID() == 4 || user.getRoleID() == 3)) {
            try {
                switch (action) {
                    case "admin-dashboard":
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "resident-tables":
                        residentTables(request, response);
                        break;
                    case "account-create":
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "accountCreate":
                        create(request, response);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/user/login.do");
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

    protected void residentTables(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String op = (String) request.getParameter("op");
        String indexPage = request.getParameter("page");
        HttpSession session = request.getSession();
        int currentPage = 1;
        if (indexPage != null) {
            currentPage = Integer.parseInt(indexPage);
        }
        List<UserEntity> list = new ArrayList<>();
        int totalItems = 0;
        int totalPages = 0;
        int pageSize = 10;
        switch (op) {
            case "getAll":
                list = rs.getAllResident();

                // Calculate the total number of pages
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);

                break;
            case "filter":
                String filterOption = (String) request.getParameter("optionBlock");
                list = rs.getAllResident();

                if (filterOption.equals("blockAsc")) {
                    Collections.sort(list, (e1, e2) -> {
                        return e1.getBID() - e2.getBID();
                    });
                } else {
                    Collections.sort(list, (e1, e2) -> {
                        return e2.getBID() - e1.getBID();
                    });
                }
                // Calculate the total number of pages
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);

                request.setAttribute("optionBlock", filterOption);
                break;
            case "search":
                String searchValue = (String) request.getParameter("txtSearch");
                list = rs.getAllResidentByName(searchValue);
                // Calculate the total number of pages
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);
                request.setAttribute("searchValue", searchValue);
                break;
        }

        // Get the subset of items to be displayed on the current page
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);
        List<UserEntity> sublist = list.subList(startIndex, endIndex);

        request.setAttribute("op", op);
        request.setAttribute("list", sublist);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        int blockId = Integer.parseInt(request.getParameter("blockId"));
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        us.createAccount(phone, email, password, name, blockId, roleId);
        request.setAttribute("message", "Account has been added!");
        response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
    }

}
