/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.ResourceEntity;
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
import java.util.List;
import service.ResourceService;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ResourceController", urlPatterns = {"/resource"})
public class ResourceController extends HttpServlet {

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
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user.getRoleID() == 4) {
            try {
                switch (action) {
                    case "resource-list":
                        resourceList(request, response);
                        break;
                    case "resource-update":
                        resourceUpdate(request, response);
                        break;
                    case "resource-update-handler":
                        resourceUpdateHandler(request, response);
                        break;
                    case "resource-add":
                        resourceAdd(request, response);
                        break;
                    case "resource-add-handler":
                        resourceAddHandler(request, response);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/home/index.do");
        }
    }

    protected void resourceAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void resourceAddHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        ResourceService rService = new ResourceService();
        String name = (String) request.getParameter("resourceName");
        String message = "";
        if (rService.addResource(name)) {
            message = "Add successfully";
        } else {
            message = "Add failed";
        }
        request.setAttribute("message", message);
        request.setAttribute("action", "resource-add");
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void resourceList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ResourceService rService = new ResourceService();
        List<ResourceEntity> list = rService.getResourceList();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void resourceUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        ResourceService rService = new ResourceService();
        String id = "";
        id = (String) request.getParameter("resourceId");
        String message = "";
        if (!id.isBlank()) {
            request.setAttribute("resource", rService.getResource(Integer.parseInt(id)));
        } else {
            message = "Error in getting resource list";
        }
        if (!message.isBlank()) {
            request.setAttribute("message", message);
            request.setAttribute("action", "resource-list");
        }

        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void resourceUpdateHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        ResourceService rService = new ResourceService();
        String id = (String) request.getParameter("id");
        String name = (String) request.getParameter("resourceName");
        if (!id.isBlank() || rService.updateResource(Integer.parseInt(id), name)) {
            rService.updateResource(Integer.parseInt(id), name);
        }
        response.sendRedirect(request.getContextPath() + "/resource/resource-list.do");
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
