/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.BlockVinEntity;
import entity.EmployeeEntity;
import entity.MyOrderEntity;
import entity.OrderDetailEntity;
import entity.UserEntity;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import service.ResidentService;
import service.OrderService;
import entity.OrderHeaderEntity;
import entity.SaleEntity;
import entity.ServiceEntity;
import entity.SupplierEntity;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import payload.request.OrderHeaderRequest;
import payload.request.UpdateOrderServicePriceRequest;
import service.BlockVinService;
import service.CategoryService;
import service.RoleService;
import service.ServiceService;
import service.SupplierService;
import service.UserService;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    private ResidentService rs = new ResidentService();

    private OrderService os = new OrderService();

    private UserService us = new UserService();

    private ServiceService ss = new ServiceService();

    private CategoryService cs = new CategoryService();

    private SupplierService supplierService = new SupplierService();

    private RoleService rss = new RoleService();

    private BlockVinService bs = new BlockVinService();

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
        if (user != null && (user.getRoleID() == 4 || user.getRoleID() == 3 || user.getRoleID() == 2)) {
            try {
                List<BlockVinEntity> blockList = bs.getAllBlock();
                int OID = 0;
                switch (action) {
                    case "admin-dashboard":
                        load_Admindashboard(request, response);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "resident-tables":
                        residentTables(request, response);
                        break;
                    case "resident-detail":
                        int AID = Integer.parseInt(request.getParameter("AID"));
                        UserEntity u = rs.getOne(AID);
                        request.setAttribute("u", u);
                        request.setAttribute("userBlockId", user.getBID());
                        request.setAttribute("blockList", blockList);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "service-list":
                        serviceList(request, response);
                        break;
                    case "service-detail":
                        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
                        ServiceEntity se = ss.getServiceById(serviceID);
                        request.setAttribute("se", se);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "user-tables":
                        userTables(request, response);
                    case "account-create":
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "user-detail":
                        user_detail(request, response);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "updateUser":
                        updateUser(request, response);
                        break;
//                    case "accountCreate":
//                        create(request, response);
//                        break;
                    case "service-create":
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "serviceCreate":
                        serviceCreate(request, response);
                        break;
                    case "updateResident":
                        updateResident(request, response);
                        break;
                    case "updateService":
                        updateService(request, response);
                        break;
                    case "admin-supplier":
                        loadSupplierList(request, response);
                        break;
                    case "order-list":
                        loadOrderList(request, response);
                        break;
                    case "pending-order":
                        loadOrderList(request, response);
                        break;
                    case "updateOrderPending":
                        updateOrderPending(request, response);
                        break;
                    case "updateEmployeeOrder":
                        updateEmployeeOrder(request, response);
                        break;
                    case "updatePrice":
                        updatePrice(request, response);
                    case "employee-order":
                        loadEmployeeOrderList(request, response);
                        break;
                    case "employee-order-detail": {
                        OID = Integer.parseInt(request.getParameter("OID"));
                        OrderHeaderEntity oh = os.getOne(OID);
                        List<UserEntity> empList = us.getEmployee();
                        List<String> statusList = us.getStatus();
                        request.setAttribute("oh", oh);
                        request.setAttribute("empList", empList);
                        request.setAttribute("blockList", blockList);
                        request.setAttribute("statusList", statusList);
                        request.setAttribute("userBlockId", user.getBID());
                        request.setAttribute("OID", OID);
                        request.setAttribute("activeTab", "employeeOrder");
                        request.setAttribute("activation", "employee-order-detail");
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    }
                    case "add-employee-order":
                        OID = Integer.parseInt(request.getParameter("OID"));
                        OrderHeaderEntity oh = os.getOne(OID);
                        List<UserEntity> empList = us.getEmployee();
                        List<String> statusList = us.getStatus();
                        request.setAttribute("oh", oh);
                        request.setAttribute("empList", empList);
                        request.setAttribute("blockList", blockList);
                        request.setAttribute("statusList", statusList);
                        request.setAttribute("userBlockId", user.getBID());
                        request.setAttribute("OID", OID);
                        request.setAttribute("activeTab", "pendingOrder");
                        request.setAttribute("activation", "add-employee-order");
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;

                    case "add-price-order":
                        OID = Integer.parseInt(request.getParameter("OID"));
                        List<UpdateOrderServicePriceRequest> list = os.selectOrderDetailWithNameService(OID);
                        request.setAttribute("list", list);
                        request.setAttribute("OID", OID);
                        request.setAttribute("activeTab", "employeeOrder");
                        request.setAttribute("activation", "add-price-order");
                        request.getRequestDispatcher("WEB-INF/layouts/admin.jsp").forward(request, response);
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

        List<SaleEntity> orderList = os.recentOrder();
        int count = os.completeOrder();
        int revenue = os.Revenue();
        int countacc = os.countAcc();
        List<SaleEntity> listSale = os.recentSale();
        List<SaleEntity> cateTra = os.cateTraffic();
        List<SaleEntity> topsell = os.topsell();
        request.setAttribute("activeTab", "dashboard");
        request.setAttribute("list", orderList);
        request.setAttribute("count", count);
        request.setAttribute("income", revenue);
        request.setAttribute("countacc", countacc);
        request.setAttribute("listSale", listSale);
        request.setAttribute("cateTra", cateTra);
        request.setAttribute("topsell", topsell);

    }

//  ----------------------------------------
//  Orders Function
//  ----------------------------------------
    protected void loadOrderList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            String op = (String) request.getParameter("op");
            String indexPage = request.getParameter("page");
            HttpSession session = request.getSession();
            int currentPage = 1;
            if (indexPage != null) {
                currentPage = Integer.parseInt(indexPage);
            }
            ArrayList<OrderHeaderRequest> list = new ArrayList<>();
            int totalItems = 0;
            int totalPages = 0;
            int pageSize = 10;
            switch (op) {
                case "getall":
                    list = os.getAllOrders();
                    // Calculate the total number of pages
                    totalItems = list.size();
                    totalPages = (int) Math.ceil((double) totalItems / pageSize);
                    break;
            }
            // Cắt danh sách dữ liệu theo phân trang            

            if (request.getAttribute("action").equals("pending-order")) {
                request.setAttribute("activeTab", "pendingOrder");
            } else {
                request.setAttribute("activeTab", "order");
            }
            request.setAttribute("op", op);
            request.setAttribute("list", list);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void loadEmployeeOrderList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            String op = (String) request.getParameter("op");
            String indexPage = request.getParameter("page");
            HttpSession session = request.getSession();
            int currentPage = 1;
            if (indexPage != null) {
                currentPage = Integer.parseInt(indexPage);
            }
            List<MyOrderEntity> empOrderList = new ArrayList<>();
            int eId = Integer.parseInt(request.getParameter("AID"));
            int totalItems = 0;
            int totalPages = 0;
            int pageSize = 10;
            switch (op) {
                case "getAll":

                    empOrderList = os.selectEmployeeOrders(eId);
                    // Calculate the total number of pages
                    totalItems = empOrderList.size();
                    totalPages = (int) Math.ceil((double) totalItems / pageSize);
                    break;
            }
            // Cắt danh sách dữ liệu theo phân trang            

            request.setAttribute("activeTab", "employeeOrder");
            request.setAttribute("op", op);
            request.setAttribute("empOrderList", empOrderList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateOrderPending(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("OID"));
        String status = request.getParameter("status");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        os.updateStatus(orderId, employeeId, status);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/add-employee-order.do?OID=" + orderId).forward(request, response);

    }

    private void updateEmployeeOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("OID"));
        String status = request.getParameter("status");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        os.updateStatus(orderId, employeeId, status);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/employee-order-detail.do?OID=" + orderId).forward(request, response);

    }

    private void updatePrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("OID"));

        // Retrieve the list of order details with name and service information
        List<UpdateOrderServicePriceRequest> orderDetails = os.selectOrderDetailWithNameService(orderId);

        // Loop through each order detail to get the updated price and update it in the database
        for (UpdateOrderServicePriceRequest od : orderDetails) {
            int id = od.getId();
            double price = Double.parseDouble(request.getParameter("price_" + id));
            os.updatePrice(id, price);
        }

        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/employee-order-detail.do?OID=" + orderId).forward(request, response);
    }

//  ----------------------------------------
//  Suppliers Function 
//  ----------------------------------------
    protected void loadSupplierList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            String op = (String) request.getParameter("op");
            String indexPage = request.getParameter("page");
            HttpSession session = request.getSession();
            int currentPage = 1;
            if (indexPage != null) {
                currentPage = Integer.parseInt(indexPage);
            }
            ArrayList<SupplierEntity> list = new ArrayList<>();
            int totalItems = 0;
            int totalPages = 0;
            int pageSize = 10;
            switch (op) {
                case "getall":
                    list = supplierService.getAllSupplier();
                    // Calculate the total number of pages
                    totalItems = list.size();
                    totalPages = (int) Math.ceil((double) totalItems / pageSize);
                    break;
                case "search":
                    String name = request.getParameter("txtSearch");
                    list = supplierService.searchByName(name);
                    request.setAttribute("searchValue", name);
                    // Calculate the total number of pages
                    totalItems = list.size();
                    totalPages = (int) Math.ceil((double) totalItems / pageSize);
                    break;
            }

            request.setAttribute("activeTab", "supplier");
            request.setAttribute("op", op);
            request.setAttribute("list", list);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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

    /*
    -------------------------------------------------------------------------------------------------------------------------------
    
        RESIDENT TABLE FUNCTION
    
    -------------------------------------------------------------------------------------------------------------------------------
     */
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
        int filterValue = 0;
        switch (op) {
            case "getAll":
                list = rs.getAllResident();
                // Calculate the total number of pages
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);
                break;
            case "filterBlock":
                list = rs.getAllResident();
                filterValue = Integer.parseInt(request.getParameter("filterValue"));
                list = filterListResident(list, "block", filterValue, 0);
                request.setAttribute("filterOption", "block");
                break;
            case "filterStatus":
                list = rs.getAllResident();
                filterValue = Integer.parseInt(request.getParameter("filterValue"));
                list = filterListResident(list, "status", 0, filterValue);
                request.setAttribute("filterOption", "status");
                break;
        }
        // Cắt danh sách dữ liệu theo phân trang
//        List<UserEntity> subList = paginateListResident(list, currentPage, pageSize);

        request.setAttribute("activeTab", "resident");
        request.setAttribute("op", op);
        request.setAttribute("filterOption", request.getParameter("filterOption"));
        request.setAttribute("filterValue", filterValue);
        request.setAttribute("blockList", bs.getAllBlock());
        request.setAttribute("list", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    private List<UserEntity> filterListResident(List<UserEntity> list, String filterOption, int filterValue1, int filterValue2) {
        List<UserEntity> filteredList = new ArrayList<>();

        // Lọc dữ liệu dựa trên filterOption và dữ liệu lọc
        if (filterOption.equals("block")) {
            filteredList = list.stream()
                    .filter(resident -> resident.getBID() == filterValue1)
                    .collect(Collectors.toList());
        } else if (filterOption.equals("status")) {
            filteredList = list.stream()
                    .filter(resident -> resident.getStatus() == filterValue2)
                    .collect(Collectors.toList());
        }
        // Trả về danh sách đã lọc
        return filteredList;
    }


    protected void updateResident(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String room = request.getParameter("room");
        int BID = Integer.parseInt(request.getParameter("BID"));
        int status = Integer.parseInt(request.getParameter("status"));
        int AID = Integer.parseInt(request.getParameter("AID"));
        rs.updateResident(room, BID, status, AID);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/resident-detail.do?AID=" + AID).forward(request, response);
    }

    

    /*
    -------------------------------------------------------------------------------------------------------------------------------
    
        SERVICE TABLE FUNCTION
    
    -------------------------------------------------------------------------------------------------------------------------------
     */
    private void serviceList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String indexPage = request.getParameter("page");
        HttpSession session = request.getSession();
        List<ServiceEntity> list = new ArrayList<>();
        // Lấy tất cả dữ liệu Service ban đầu
        // Xử lý dữ liệu nếu có yêu cầu
        String op = (String) request.getParameter("op");
        int filterValue = 0;
        switch (op) {
            case "getAll":
                list = ss.getAllService();
                break;
            case "filterCategory":
                list = ss.getAllService();
                filterValue = Integer.parseInt(request.getParameter("filterValue"));
                list = filterList(list, "category", filterValue, 0);
                request.setAttribute("filterOption", "category");
                break;

            case "filterSupplier":
                list = ss.getAllService();
                filterValue = Integer.parseInt(request.getParameter("filterValue"));
                list = filterList(list, "supplier", 0, filterValue);
                request.setAttribute("filterOption", "supplier");
                break;
        }
        // Cắt danh sách dữ liệu theo phân trang

        request.setAttribute("activeTab", "service");
        request.setAttribute("op", op);
        request.setAttribute("filterOption", request.getParameter("filterOption"));
        request.setAttribute("filterValue", filterValue);
        request.setAttribute("categoryList", cs.getAllCategory());
        request.setAttribute("supplierList", supplierService.getAllSupplier());
        request.setAttribute("list", list);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    private List<ServiceEntity> filterList(List<ServiceEntity> list, String filterOption, int filterValue1, int filterValue2) {
        List<ServiceEntity> filteredList = new ArrayList<>();

        // Lọc dữ liệu dựa trên filterOption và dữ liệu lọc
        if (filterOption.equals("category")) {
            filteredList = list.stream()
                    .filter(service -> service.getCategoryID() == filterValue1)
                    .collect(Collectors.toList());
        } else if (filterOption.equals("supplier")) {
            filteredList = list.stream()
                    .filter(service -> service.getSupplierID() == filterValue2)
                    .collect(Collectors.toList());
        }
        // Trả về danh sách đã lọc
        return filteredList;
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int service_id = Integer.parseInt(request.getParameter("service_id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double lowerPrice = Double.parseDouble(request.getParameter("lowerPrice"));
        double upperPrice = Double.parseDouble(request.getParameter("upperPrice"));
        double rated = Double.parseDouble(request.getParameter("rated"));
        int supplierId = Integer.parseInt(request.getParameter("supplierID"));
        int categoryId = Integer.parseInt(request.getParameter("categoryID"));
        ss.updateService(service_id, name, description, lowerPrice, upperPrice, rated, supplierId, categoryId);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/service-detail.do?serviceID=" + service_id).forward(request, response);
    }

    private void serviceCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String op = request.getParameter("op");
        switch (op) {
            case "create":
                String serviceName = request.getParameter("name");
                String description = request.getParameter("description");
                double lowerPrice = Double.parseDouble(request.getParameter("lowerPrice"));
                double upperPrice = Double.parseDouble(request.getParameter("upperPrice"));
                double rated = Double.parseDouble(request.getParameter("rated"));
                int supplierID = Integer.parseInt(request.getParameter("supplierID"));
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                ss.addService(serviceName, description, lowerPrice, upperPrice, rated, supplierID, categoryID);
                response.sendRedirect(request.getContextPath() + "/admin/service-list.do?op=getAll");
                break;
            case "cancel":
                response.sendRedirect(request.getContextPath() + "/admin/service-list.do?op=getAll");
                break;
        }
    }


    /*
    -------------------------------------------------------------------------------------------------------------------------------
    
        USER TABLE FUNCTION
    
    -------------------------------------------------------------------------------------------------------------------------------
     */
    //    protected void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String phone = request.getParameter("phone");
//        int blockId = Integer.parseInt(request.getParameter("blockId"));
//        int roleId = Integer.parseInt(request.getParameter("roleId"));
//        us.createAccount(phone, email, password, name, blockId, roleId);
//        request.setAttribute("message", "Account has been added!");
//        response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
//    }
    private void userTables(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        int filterValue = 0;
        String filterValue1 = "";
        switch (op) {
            case "getAll":
                list = us.getAllUser();
                // Calculate the total number of pages
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);

                break;
            case "filterGender":
                list = us.getAllUser();
                filterValue1 = request.getParameter("filterValue1");
                list = filterListUser(list, "gender", filterValue1, 0, 0);
                request.setAttribute("filterOption", "gender");
                break;
            case "filterRole":
                list = us.getAllUser();
                filterValue = Integer.parseInt(request.getParameter("filterValue"));
                list = filterListUser(list, "role", "", filterValue, 0);
                request.setAttribute("filterOption", "role");
                break;
            case "filterStatus":
                list = us.getAllUser();
                filterValue = Integer.parseInt(request.getParameter("filterValue"));
                list = filterListUser(list, "status", "", 0, filterValue);
                request.setAttribute("filterOption", "status");
                break;
        }

        request.setAttribute("activeTab", "user");
        request.setAttribute("op", op);
        request.setAttribute("filterOption", request.getParameter("filterOption"));
        request.setAttribute("filterValue", filterValue);
        request.setAttribute("filterValue1", filterValue1);
        request.setAttribute("roleList", rss.getAllRole());
        request.setAttribute("list", list);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    

    private List<UserEntity> filterListUser(List<UserEntity> list, String filterOption, String filterValue1, int filterValue2, int filterValue3) {
        List<UserEntity> filteredList = new ArrayList<>();

        // Lọc dữ liệu dựa trên filterOption và dữ liệu lọc
        if (filterOption.equals("gender")) {
            filteredList = list.stream()
                    .filter(service -> service.getGender().equalsIgnoreCase(filterValue1))
                    .collect(Collectors.toList());
        } else if (filterOption.equals(
                "role")) {
            filteredList = list.stream()
                    .filter(user -> user.getRoleID() == filterValue2)
                    .collect(Collectors.toList());
        } else if (filterOption.equals(
                "status")) {
            filteredList = list.stream()
                    .filter(user -> user.getStatus() == filterValue3)
                    .collect(Collectors.toList());
        }
        // Trả về danh sách đã lọc
        return filteredList;
    }

    private void user_detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String aid = request.getParameter("AID");

        UserEntity user = us.getUser(Integer.parseInt(aid));

        request.setAttribute("u", user);

    }
    
    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String room = request.getParameter("room");
        int BID = Integer.parseInt(request.getParameter("BID"));
        int status = Integer.parseInt(request.getParameter("status"));
        int AID = Integer.parseInt(request.getParameter("AID"));
        rs.updateResident(room, BID, status, AID);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/user-detail.do?AID=" + AID).forward(request, response);
    }

    //é đù ăng seng //m quay ha
}
