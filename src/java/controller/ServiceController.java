/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import entity.CategoryEntity;

import repository.ServiceRepository;

import entity.FeedbackEntity;
import entity.ResidentEntity;
import entity.ServiceEntity;
import entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repository.FeedbackRepository;
import service.*;

/**
 *
 * @author admin
 */
@WebServlet(name = "ServiceServlet", urlPatterns = {"/service"})
public class ServiceController extends HttpServlet {

    private CategoryService categoryService = new CategoryService();
    private ServiceService serviceService = new ServiceService();
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
        try{
            switch(action){
                case "service":
                    ArrayList<CategoryEntity> categoryList = categoryService.getAllCategory();
                    request.setAttribute("list", categoryList);
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "service-list":
                    int categoryId = Integer.parseInt(request.getParameter("id"));
                    ArrayList<ServiceEntity> serviceList = serviceService.getServiceByCategory(categoryId);
                    request.setAttribute("list", serviceList);
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "service-detail":
                    detail(request, response);
                    break;
                case "addFeedback":
                    addFeedback(request, response);
                    break;
                case "list":
                    searchByName(request, response);
                    break;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", "error");
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    private void addFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        ServiceEntity service = (ServiceEntity) session.getAttribute("service");
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        if(user == null){
            request.setAttribute("message", "Log in to comment");
            request.getRequestDispatcher("/service/service-detail.do?id=" + serviceID).forward(request, response);
        }else{
            int UID = Integer.parseInt(request.getParameter("UID"));
            String message = request.getParameter("message");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");
            double rated = Double.parseDouble(request.getParameter("rated"));
            FeedbackService fs = new FeedbackService();
            fs.addFeedback(UID, serviceID, message, name, contact, email, rated);
            response.sendRedirect(request.getContextPath() + "/service/service-detail.do?id=" + serviceID);
        }
    }
    
    

    private void detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        int serviceID = Integer.parseInt(request.getParameter("id"));
        ArrayList<CategoryEntity> categoryList = categoryService.getAllCategory();
        FeedbackService fs = new FeedbackService();
        ServiceService ss = new ServiceService();
        CategoryService cs = new CategoryService();
        CategoryEntity cate = cs.getServiceByCategoryId(serviceID);
        ServiceEntity service = ss.getServiceById(serviceID);
        List<FeedbackEntity> listFeedback = fs.getFeedbackOfService(serviceID);
        request.setAttribute("noFeedbacks", listFeedback.size());
        request.setAttribute("feedbacks", listFeedback);     
        request.setAttribute("service", service);
        request.setAttribute("cate", cate);
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }
    
    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String txtSearch = request.getParameter("txt"); 
        ServiceService service = new ServiceService();
        ArrayList<ServiceEntity> listS = service.searchByName(txtSearch);
        
        request.setAttribute("listS", listS);
        request.setAttribute("txtSearch", txtSearch);
        
        
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }
    

}
