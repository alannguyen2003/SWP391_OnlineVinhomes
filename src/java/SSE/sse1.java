/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package SSE;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import service.OrderService;

/**
 *
 * @author ASUS
 */
public class sse1 extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int waitTimeBetweenPosts = 1;
//        OrderRepository oRepo = new OrderRepository();
//        int statement1=0;
//        try {
//            statement1 = oRepo.getNumberOfPendingOrders(1);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
            OrderService oService = new OrderService();
            String blockId = (String) request.getParameter("bId");
            int numberOfPeningOrders = oService.getPendingOrders(Integer.parseInt(blockId));
            String statement1 = "" + numberOfPeningOrders;
//            String statement2 = ""+blockId;
            // content type must be set to text/event-stream
            resp.setContentType("text/event-stream");

            // encoding must be set to UTF-8
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write("data: " + statement1 + "\n");
            try {
                Thread.sleep(waitTimeBetweenPosts * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.write("data: " + "" + "\n");
            writer.write("\n");
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
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
