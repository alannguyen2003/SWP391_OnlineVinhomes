/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CategoryEntity;
import entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import service.ResidentService;
import service.OrderService;
import entity.OrderHeaderEntity;
import entity.SaleEntity;
import java.util.List;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    private ResidentService rs = new ResidentService();
    private OrderService os = new OrderService();
    
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
                        load_Admindashboard(request, response);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "resident-tables":
                        ArrayList<UserEntity> entity = rs.getAllResident();
                        request.setAttribute("list", entity);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/user/login.do");
        }

    }

    protected void load_Admindashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        List<OrderHeaderEntity> orderList = os.recentOrder();
        int count = os.completeOrder();
        int revenue = os.Revenue();
        int countacc = os.countAcc();
        List<SaleEntity> listSale = os.recentSale();
        List<SaleEntity> cateTra = os.cateTraffic();
        request.setAttribute("list", orderList);
        request.setAttribute("count", count);
        request.setAttribute("income", revenue);
        request.setAttribute("countacc", countacc);
        request.setAttribute("listSale", listSale);
        request.setAttribute("cateTra", cateTra);

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
