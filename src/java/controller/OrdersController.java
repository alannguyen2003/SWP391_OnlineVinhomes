/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.MyOrderEntity;
import entity.OrderHeaderEntity;
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
import service.OrderService;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import payload.request.AdminOrderListRequest;
import service.CoordinatorService;

@WebServlet(name = "OrdersController", urlPatterns = {"/order"})
public class OrdersController extends HttpServlet {

    private OrderService orderService = new OrderService();
    private CoordinatorService coordinatorService = new CoordinatorService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        HttpSession session = request.getSession();
        try {
            switch (action) {
                case "myorder":
                    //Processing code here
                    int uId = Integer.parseInt(request.getParameter("aid"));
                    List<MyOrderEntity> myOrderList = orderService.selectMyOrders(uId);
                    request.setAttribute("myOrderlist", myOrderList);
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "order-information":
                    //Processing code here
                    //Forward request & response to the main layout
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "cancel-order":
                    int oid = Integer.parseInt(request.getParameter("oid"));
                    OrderHeaderEntity oh = orderService.getOne(oid);

                    // oh.getDate() trả về một đối tượng Date
                    Date date = oh.getDelivery_time();

                    // Lấy thời điểm hiện tại
                    Date now = new Date();

                    // Tính khoảng thời gian giữa hai thời điểm theo giờ
                    long millisecondsDifference = date.getTime() - now.getTime();
                    long hoursDifference = millisecondsDifference / (60 * 60 * 1000);
                    if (hoursDifference >= 6 && oh.getStatus().equals("Pending")) {
                        orderService.cancelOrder(oid);
                        List<AdminOrderListRequest> listOrderCoordinator = orderService.selectOrdersCoordinator(oh.getCoordinatorID());
                        boolean pending = false;
                        for (AdminOrderListRequest adminOrderListRequest : listOrderCoordinator) {
                            if (adminOrderListRequest.getStatus().equals("Pending")) {
                                pending = true;
                                break;
                            }
                        }
                        if (pending == false) {
                            coordinatorService.updateEnableCoordinattor(true, oh.getCoordinatorID());
                        }
                        response.sendRedirect(request.getContextPath() + "/order/myorder.do?aid=" + request.getParameter("aid"));
                    } else {
                        request.setAttribute("message", "The Difference hour must be larger than or equal 6 hours and Status must be Pending");
                        response.sendRedirect(request.getContextPath() + "/order/myorder.do?aid=" + request.getParameter("aid"));
                    }
                    break;
                default:
                    //Show error page
                    request.setAttribute("controller", "error");
                    request.setAttribute("action", "error404");
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("controller", "error");
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
