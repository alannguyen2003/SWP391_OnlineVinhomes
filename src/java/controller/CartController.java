/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CartEntity;
import entity.ItemEntity;
import entity.ServiceEntity;
import entity.UserEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CartService;
import service.GmailService;
import service.OrderService;
import service.ServiceService;
import service.SupplierService;

/**
 *
 * @author admin
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    private CartService cs = new CartService();

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
                case "cart":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "addToCart":
                    addToCart(request, response);
                    break;
                case "removeFromCart":
                    removeFromCart(request, response);
                    break;
                case "cart-contact":
                    request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
                    break;
                case "cart-completion":
                    cartCompletion(request, response);
                    break;
            }
        } catch (SQLException ex) {
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

    protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        HttpSession session = request.getSession(true);
        CartEntity cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (CartEntity) o;
        } else {
            cart = new CartEntity();
        }
        String serviceID = request.getParameter("id");
        CartService cs = new CartService();
        ServiceEntity se = cs.getServiceById(Integer.parseInt(serviceID));
        ItemEntity item = new ItemEntity(se, se.getLowerPrice());
        cart.addItem(item);
        List<ItemEntity> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        // lỗi khúc này vì ServiceID nếu get thì sẽ trùng với CategoryID ở service.jsp -> bug
        request.getRequestDispatcher("/service/service.do").forward(request, response);
    }

    protected void removeFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session3 = request.getSession(true);
        CartEntity cart3 = null;
        Object o3 = session3.getAttribute("cart");
        if (o3 != null) {
            cart3 = (CartEntity) o3;
        } else {
            cart3 = new CartEntity();
        }
        String id3 = request.getParameter("id");
        cart3.removeItem(Integer.parseInt(id3));
        List<ItemEntity> list2 = cart3.getItems();
        request.setAttribute("controller", "cart");
        request.setAttribute("action", "cart");
        session3.setAttribute("cart", cart3);
        session3.setAttribute("size", list2.size());
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }

//    protected void contact(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            HttpSession session = request.getSession(true);
//            CartEntity cart = null;
//            Object o = session.getAttribute("cart");
//            if (o != null) {
//                cart = (CartEntity) o;
//            } else {
//                cart = new CartEntity();
//            }
//            UserEntity user = null;
//            Object o1 = session.getAttribute("user");
//            int size = (Integer) session.getAttribute("size");
//            if (o1 != null && size != 0) {
//                user = (UserEntity) o1;
//                OrderFacade of = new OrderFacade();
//                of.addOrder(user, cart);
//                session.removeAttribute("cart");
//                session.setAttribute("size", 0);
//                request.getRequestDispatcher("/cart/cart.do").forward(request, response);
//
//            } else if (o1 != null && size == 0) {
//                request.setAttribute("noItem", "No product in your cart!");
//                request.getRequestDispatcher("/cart/cart.do").forward(request, response);
//            } else {
//                request.setAttribute("message", "You need to log in to check out!");
//                request.getRequestDispatcher("/auth/login.do").forward(request, response);
//            }
//        } catch (Exception ex) {
//        }
//    }
    protected void cartCompletion(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            CartEntity cart = (CartEntity) session.getAttribute("cart");
            UserEntity user = (UserEntity) session.getAttribute("user");
            OrderService oService = new OrderService();
            oService.addOrder(user, cart);
            String itemNeeded = "";
            ServiceService sService = new ServiceService();
            SupplierService supService = new SupplierService();
            for(ItemEntity item : cart.getItems()) {
               itemNeeded = sService.checkResource(item.getService(), user.getBID());
               if(!itemNeeded.isBlank()) {
                   String email = supService.getSupplierEmail(item.getService().getSupplierID());
                   GmailService gmailer = new GmailService();
                   String body = "Short in Resource";
                   String message = "We are running out of the following resources:\n" + itemNeeded + "Please gather these resources for us. Best regard."; 
                   gmailer.sendEmail(body, message, email);
               }
               itemNeeded = "";
            }
            session.removeAttribute("cart");
            session.removeAttribute("size");
            request.setAttribute("orderMessage", "Thank you for your supporting. Our employee will contact you via Phone soon.");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
