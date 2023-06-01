/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.BlockResourceEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import service.ResourceService;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AdminResourceController", urlPatterns = {"/admin-resource"})
public class AdminResourceController extends HttpServlet {

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
                case "table-resource":
                    tableResource(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected void tableResource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        ResourceService rService = new ResourceService();
        String op = (String) request.getParameter("op");
        String indexPage = request.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
        List<BlockResourceEntity> list = new ArrayList<>();
        
        switch(op) {
            case "getAll":
                list = rService.getAll(page, 10, 6);
                break;
            case "search":
                String searchValue = (String) request.getParameter("txtSearch");
                list = rService.getResourceBySearched(page, 10, searchValue, 6);
                request.setAttribute("searchValue", searchValue);
                break;
            case "filter" :
                String optionQuantity = (String) request.getParameter("optionQuantity");
                
                break;
        }
        int endPage = list.size() / 8;
        //Lay tong so luong san pham trong db
        if (list.size() % 8 != 0) {
            endPage++;
        }
        
        request.setAttribute("op", op);
        request.setAttribute("page", page - 1);
        request.setAttribute("list", list);
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);

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
