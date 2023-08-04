/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.BlockVinEntity;
import entity.CoordinatorEntity;
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
import entity.RoleEntity;
import entity.SaleEntity;
import entity.ServiceEntity;
import entity.SupplierEntity;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import payload.request.AdminResidentListRequest;
import payload.request.AdminServiceListRequest;
import payload.request.AdminUserListRequest;
import payload.request.AdminOrderListRequest;
import payload.request.ResidentProfileRequest;
import payload.request.AdminServiceDetailRequest;
import payload.request.OrderDetailRequest;
import payload.request.UpdateOrderServicePriceRequest;
import service.BlockVinService;
import service.CategoryService;
import service.CoordinatorService;
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

    private CoordinatorService cds = new CoordinatorService();

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
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user != null && (user.getRoleID() != 1)) {
            try {
                List<BlockVinEntity> blockList = bs.getAllBlock();
                List<RoleEntity> rolelist = rss.get2Role();
                int OID = 0;
                switch (action) {
                    case "admin-dashboard":
                        load_Admindashboard(request, response);
                        if (user.getRoleID() == 2) {
                            response.sendRedirect(request.getContextPath() + "/admin/coordinator-order.do?op=getAll&AID=" + user.getAID());
                            break;
                        }
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "resident-tables":
                        if (user.getRoleID() == 2) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        residentTables(request, response);
                        break;
                    case "resident-detail":
                        if (user.getRoleID() == 2) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        int AID = Integer.parseInt(request.getParameter("AID"));
                        ResidentProfileRequest u = us.getAdminResident(AID);
                        request.setAttribute("u", u);
//                        request.setAttribute("userBlockId", user.getBID());
                        request.setAttribute("blockList", blockList);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "updateResident":
                        if (user.getRoleID() == 4) {
                            updateAdminResident(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "service-list":
                        if (user.getRoleID() == 4) {
                            serviceList(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "service-detail":
                        if (user.getRoleID() == 4) {
                            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
                            AdminServiceDetailRequest se = ss.getServiceByIdForAdminServiceDetail(serviceID);
                            request.setAttribute("se", se);
                            request.setAttribute("categoryList", cs.getAllCategory());
                            request.setAttribute("activeTab", "service");
                            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "user-tables":
                        userTables(request, response);
                        if (user.getRoleID() == 4) {
                            userTables(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "user-create":
                        request.setAttribute("blockList", blockList);
                        request.setAttribute("roleID", rolelist);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "account-create":

                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "user-detail":
                        if (user.getRoleID() == 4) {
                            user_detail(request, response);
                            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "updateUser":
                        if (user.getRoleID() == 4) {
                            updateUser(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "create":
                        create(request, response);
                        break;
                    case "service-create":
                        if (user.getRoleID() == 4) {
                            request.setAttribute("categoryList", cs.getAllCategory());
                            request.setAttribute("supplierList", supplierService.getAllSupplier());
                            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "serviceCreate":
                        serviceCreate(request, response);
                        break;
                    case "updateService":
                        if (user.getRoleID() == 4) {
                            updateService(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "admin-supplier":
                        if (user.getRoleID() == 4) {
                            loadSupplierList(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "coordinator-list":
                        if (user.getRoleID() != 2) {
                            loadCoordinatorList(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "coordinator-detail":
                        if (user.getRoleID() != 2) {
                            loadCoordinatorDetail(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "updateCoordinator":
                        updateCoordinator(request, response);
                        break;
                    case "order-list":
                        if (user.getRoleID() == 2) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        loadOrderList(request, response);
                        break;
                    case "order-detail":
                        loadOrderDetailList(request, response);
                        break;
                    case "pending-order":
                        if (user.getRoleID() != 3) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        loadOrderList(request, response);
                        break;
                    case "updateOrderPending":
                        if (user.getRoleID() != 3) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        updateOrderPending(request, response);
                        break;
                    case "updateEmployeeOrder":
                        if (user.getRoleID() != 2) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        updateEmployeeOrder(request, response);
                        break;
                    case "updatePrice":
                        if (user.getRoleID() == 4) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        updatePrice(request, response);
                        break;
                    case "coordinator-order":
                        if (user.getRoleID() == 2) {
                            loadCoordinatorOrderList(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;
                    case "coordinator-order-detail": {
                        OID = Integer.parseInt(request.getParameter("OID"));
                        OrderHeaderEntity oh = os.getOne(OID);
                        UserEntity resident = os.getResidentNameFromOrder(OID);
                        UserEntity coordinator = os.getNameFromOrder(OID);
                        List<CoordinatorEntity> coorList = cds.getAvailableCoordinator();
                        List<String> statusList = os.getStatus();
                        request.setAttribute("oh", oh);
                        request.setAttribute("coorList", coorList);
                        request.setAttribute("rs", resident);
                        request.setAttribute("cr", coordinator);
                        request.setAttribute("statusList", statusList);
                        request.setAttribute("OID", OID);
                        request.setAttribute("activeTab", "coordinatorOrder");
                        request.setAttribute("activation", "coordinator-order-detail");
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    }
                    case "add-coordinator-order":
                        if (user.getRoleID() != 3) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        OID = Integer.parseInt(request.getParameter("OID"));
                        OrderHeaderEntity oh = os.getOne(OID);
                        UserEntity us = os.getNameFromOrder(OID);
                        List<CoordinatorEntity> coorList = cds.getAvailableCoordinator();
                        List<String> statusList = os.getStatus();
                        request.setAttribute("us", us);
                        request.setAttribute("oh", oh);
                        request.setAttribute("coorList", coorList);
                        request.setAttribute("blockList", blockList);
                        request.setAttribute("statusList", statusList);
                        request.setAttribute("OID", OID);
                        request.setAttribute("activeTab", "pendingOrder");
                        request.setAttribute("activation", "add-coordinator-order");
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;

                    case "add-price-order":
                        if (user.getRoleID() == 4) {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        OID = Integer.parseInt(request.getParameter("OID"));
                        List<UpdateOrderServicePriceRequest> list = os.selectOrderDetailWithNameService(OID);
                        ArrayList<SupplierEntity> supplierList = supplierService.getAllSupplier();
                        request.setAttribute("listSupplier", supplierList);
                        request.setAttribute("list", list);
                        request.setAttribute("OID", OID);
                        request.setAttribute("activeTab", "coordinatorOrder");
                        request.setAttribute("activation", "add-price-order");
                        request.getRequestDispatcher("WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "supplier-detail":
                        supplier_detail(request, response);
                        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
                        break;
                    case "updateSupplier":
                        if (user.getRoleID() == 4) {
                            updateSupplier(request, response);
                        } else {
                            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard.do");
                        }
                        break;

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            if (user != null) {
                response.sendRedirect(request.getContextPath() + "/home/index.do");
            } else {
                response.sendRedirect(request.getContextPath() + "/user/login.do");
            }
        }

    }

    protected void loadOrderDetailList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int id = Integer.parseInt(request.getParameter("orderID"));
            ArrayList<OrderDetailRequest> list = os.getAllOrderDetailById(id);
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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
            ArrayList<AdminOrderListRequest> list = new ArrayList<>();
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

    protected void loadCoordinatorOrderList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            String op = (String) request.getParameter("op");
            String indexPage = request.getParameter("page");
            HttpSession session = request.getSession();
            int currentPage = 1;
            if (indexPage != null) {
                currentPage = Integer.parseInt(indexPage);
            }
            List<AdminOrderListRequest> corOrderList = new ArrayList<>();
            int CID = Integer.parseInt(request.getParameter("AID"));
            int totalItems = 0;
            int totalPages = 0;
            int pageSize = 10;
            switch (op) {
                case "getAll":

                    corOrderList = os.selectOrdersCoordinator(CID);
                    // Calculate the total number of pages
                    totalItems = corOrderList.size();
                    totalPages = (int) Math.ceil((double) totalItems / pageSize);
                    break;
            }
            // Cắt danh sách dữ liệu theo phân trang            

            request.setAttribute("activeTab", "coordinatorOrder");
            request.setAttribute("op", op);
            request.setAttribute("corOrderList", corOrderList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateOrderPending(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String op = request.getParameter("op");
        switch (op) {
            case "update": {
                int orderID = Integer.parseInt(request.getParameter("OID"));
                String status = request.getParameter("status");
                int coorID = Integer.parseInt(request.getParameter("coorId"));
                String note = request.getParameter("note");
                os.updateStatus(orderID, coorID, status, note);
                String message = "Update successfully";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/admin/add-coordinator-order.do?OID=" + orderID).forward(request, response);
                break;
            }
            case "change": {
                int orderID = Integer.parseInt(request.getParameter("OID"));
                String status = request.getParameter("status");
                String note = request.getParameter("note");
                CoordinatorService cService = new CoordinatorService();
                int coordinatorId = cService.autoAssignCoordinator();
                cService.updateEnableCoordinattor(false, coordinatorId);
                os.updateStatus(orderID, coordinatorId, status, note);
                String message = "Update successfully";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/admin/add-coordinator-order.do?OID=" + orderID).forward(request, response);
                break;
            }
        }

    }

    private void updateEmployeeOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        int orderId = Integer.parseInt(request.getParameter("OID"));
        String status = request.getParameter("status");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String note = request.getParameter("note");
        os.updateStatus(orderId, employeeId, status, note);
        List<AdminOrderListRequest> listOrderCoordinator = os.selectOrdersCoordinator(employeeId);
        boolean pending = false;
        for (AdminOrderListRequest adminOrderListRequest : listOrderCoordinator) {
            if (adminOrderListRequest.getStatus().equals("Pending")) {
                pending = true;
                break;
            }
        }
        if (pending == false) {
            cds.updateEnableCoordinattor(true, employeeId);
        }
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/coordinator-order-detail.do?OID=" + orderId).forward(request, response);

    }

    private void updatePrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("OID"));

        // Retrieve the list of order details with name and service information
        List<UpdateOrderServicePriceRequest> orderDetails = os.selectOrderDetailWithNameService(orderId);

        // Loop through each order detail to get the updated price and update it in the database
        for (UpdateOrderServicePriceRequest od : orderDetails) {
            int id = od.getId();
            double price;
            int supplierId;
            try {
                price = Double.parseDouble(request.getParameter("price_" + id));
                supplierId = Integer.parseInt(request.getParameter("supplier_" + id));
            } catch (NumberFormatException ex) {
                price = 0;
                supplierId = 1;
            }
            os.updateSupplier(id, supplierId);
            os.updatePrice(id, price);
        }

        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/coordinator-order-detail.do?OID=" + orderId).forward(request, response);
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
    
        COORDINATOR TABLE FUNCTION
    
    -------------------------------------------------------------------------------------------------------------------------------
     */
    protected void loadCoordinatorList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            String op = (String) request.getParameter("op");
            String indexPage = request.getParameter("page");
            HttpSession session = request.getSession();
            int currentPage = 1;
            if (indexPage != null) {
                currentPage = Integer.parseInt(indexPage);
            }
            ArrayList<CoordinatorEntity> list = new ArrayList<>();
            int totalItems = 0;
            int totalPages = 0;
            int pageSize = 10;
            switch (op) {
                case "getall":
                    list = cds.getAllCoordinator();
                    // Calculate the total number of pages
                    totalItems = list.size();
                    totalPages = (int) Math.ceil((double) totalItems / pageSize);
                    break;
            }

            request.setAttribute("activeTab", "coordinator");
            request.setAttribute("op", op);
            request.setAttribute("list", list);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCoordinatorDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String cid = request.getParameter("CID");
        CoordinatorEntity coordinator = cds.getCoordinatorByID(Integer.parseInt(cid));
        request.setAttribute("coor", coordinator);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    protected void updateCoordinator(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        boolean enable = Boolean.parseBoolean(request.getParameter("enable"));
        int CID = Integer.parseInt(request.getParameter("CID"));
        cds.updateEnableCoordinattor(enable, CID);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/coordinator-detail.do?CID=" + CID).forward(request, response);
    }

    /*
    -------------------------------------------------------------------------------------------------------------------------------
    
        RESIDENT TABLE FUNCTION
    
    -------------------------------------------------------------------------------------------------------------------------------
     */
    protected void residentTables(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String op = request.getParameter("op");
        String indexPage = request.getParameter("page");
        HttpSession session = request.getSession();
        int currentPage = 1;
        if (indexPage != null) {
            currentPage = Integer.parseInt(indexPage);
        }

        ArrayList<AdminResidentListRequest> list = new ArrayList<>();
        int totalItems = 0;
        int totalPages = 0;
        int pageSize = 10;
        int filterValue = 0;

        switch (op) {
            case "getAll":
                list = us.getAllResident();
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);
                break;

//            case "filterBlock":
//                list = us.getAllResident();
//                filterValue = Integer.parseInt(request.getParameter("filterValue"));
////                list = filterListResident(list, "block", filterValue, 0);
//                request.setAttribute("filterOption", "block");
//                break;
//
//            case "filterStatus":
//                list = us.getAllResident();
//                filterValue = Integer.parseInt(request.getParameter("filterValue"));
////                list = filterListResident(list, "status", 0, filterValue);
//                request.setAttribute("filterOption", "status");
//                break;
        }

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

    private List<AdminResidentListRequest> filterListResident(List<AdminResidentListRequest> list, String filterOption, int filterValue1, int filterValue2) {
        List<AdminResidentListRequest> filteredList = new ArrayList<>();

//        if (filterOption.equals("block")) {
//            filteredList = list.stream()
////                    .filter(resident -> resident.getBID() == filterValue1)
//                    .collect(Collectors.toList());
//        } else if (filterOption.equals("status")) {
//            filteredList = list.stream()
//                    .filter(resident -> resident.getStatus() == filterValue2)
//                    .collect(Collectors.toList());
//        }
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
        List<AdminServiceListRequest> list = new ArrayList<>();
        // Lấy tất cả dữ liệu Service ban đầu
        // Xử lý dữ liệu nếu có yêu cầu
        String op = (String) request.getParameter("op");
        int filterValue = 0;
        switch (op) {
            case "getAll":
                list = ss.getServicesListForAdminPage();
                break;
            case "filterCategory":
                list = ss.getServicesListForAdminPage();
                filterValue = Integer.parseInt(request.getParameter("filterValue"));
                list = filterList(list, "category", filterValue, 0);
                request.setAttribute("filterOption", "category");
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

    private List<AdminServiceListRequest> filterList(List<AdminServiceListRequest> list, String filterOption, int filterValue1, int filterValue2) {
        List<AdminServiceListRequest> filteredList = new ArrayList<>();

        // Lọc dữ liệu dựa trên filterOption và dữ liệu lọc
        if (filterOption.equals("category")) {
            filteredList = list.stream()
                    .filter(service -> service.getCategoryID() == filterValue1)
                    .collect(Collectors.toList());
        } else if (filterOption.equals("supplier")) {
            filteredList = list.stream()
                    .collect(Collectors.toList());
        }
        // Trả về danh sách đã lọc
        return filteredList;
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        int service_id = Integer.parseInt(request.getParameter("service_id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double lowerPrice = Double.parseDouble(request.getParameter("lowerPrice"));
        double upperPrice = Double.parseDouble(request.getParameter("upperPrice"));
        double rated = Double.parseDouble(request.getParameter("rated"));
        int categoryId = Integer.parseInt(request.getParameter("categoryID"));

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String formattedRated = decimalFormat.format(rated);
        rated = Double.parseDouble(formattedRated.replace(',', '.')); // Replace comma with dot for decimal separator

        ss.updateService(service_id, name, description, lowerPrice, upperPrice, rated, categoryId);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.setAttribute("categoryId", categoryId);
        request.getRequestDispatcher("/admin/service-detail.do?serviceID=" + service_id).forward(request, response);
    }

    private void serviceCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, Exception {
        String op = request.getParameter("op");
        switch (op) {
            case "create":
                String serviceName = request.getParameter("name");
                String description = request.getParameter("description");
                double lowerPrice = Double.parseDouble(request.getParameter("lowerPrice"));
                double upperPrice = Double.parseDouble(request.getParameter("upperPrice"));
                double rated = Double.parseDouble(request.getParameter("rated"));
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                ss.addService(serviceName, description, lowerPrice, upperPrice, rated, categoryID);
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
    protected void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, Exception {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        int roleid = Integer.parseInt(request.getParameter("role"));
        UserEntity entity = new UserEntity();
        entity.setEmail(email);
        entity.setName(name);
        entity.setPhone(phone);
        entity.setPassword(password);
        entity.setRoleID(roleid);
        entity.setGender(gender);

        us.createAccount(entity);
        cds.addCoordinator(us.checkEmailExist(email).getAID());
        
        request.setAttribute("message", "Account has been added!");
        request.getRequestDispatcher("/admin/user-create.do").forward(request, response);
    }

    private void userTables(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String op = request.getParameter("op");
        String indexPage = request.getParameter("page");
        HttpSession session = request.getSession();
        int currentPage = 1;
        if (indexPage != null) {
            currentPage = Integer.parseInt(indexPage);
        }

        List<AdminUserListRequest> list = new ArrayList<>();
        int totalItems = 0;
        int totalPages = 0;
        int pageSize = 10;
        int filterValue = 0;
        String filterValue1 = "";

        switch (op) {
            case "getAll":
                list = us.getAllUser();
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

    private List<AdminUserListRequest> filterListUser(List<AdminUserListRequest> list, String filterOption, String filterValue1, int filterValue2, int filterValue3) {
        List<AdminUserListRequest> filteredList = new ArrayList<>();

        if (filterOption.equals("gender")) {
            filteredList = list.stream()
                    .filter(user -> user.getGender().equalsIgnoreCase(filterValue1))
                    .collect(Collectors.toList());
        } else if (filterOption.equals("role")) {
            filteredList = list.stream()
                    //                    .filter(user -> user.getRole()== filterValue2)
                    .collect(Collectors.toList());
        } else if (filterOption.equals("status")) {
            filteredList = list.stream()
                    //                    .filter(user -> user.get() == filterValue3)
                    .collect(Collectors.toList());
        }

        return filteredList;
    }

    private void user_detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String aid = request.getParameter("AID");
//        List<BlockVinEntity> blockList = bs.getAllBlock();
        UserEntity user = us.getUser(Integer.parseInt(aid));
//        request.setAttribute("blockList", blockList);
        request.setAttribute("u", user);

    }

    private void supplier_detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String sid = request.getParameter("SID");

        SupplierEntity supplier = supplierService.getSupplier(Integer.parseInt(sid));

        request.setAttribute("u", supplier);

    }

    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int status = Integer.parseInt(request.getParameter("status"));
        int AID = Integer.parseInt(request.getParameter("AID"));
        us.updateUser(status, AID);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/user-detail.do?AID=" + AID).forward(request, response);
    }

    protected void updateAdminResident(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int status = Integer.parseInt(request.getParameter("status"));
        int AID = Integer.parseInt(request.getParameter("AID"));
        us.updateUser(status, AID);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/resident-detail.do?AID=" + AID).forward(request, response);
    }

    protected void updateSupplier(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String address = request.getParameter("address");
        int SID = Integer.parseInt(request.getParameter("SID"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        supplierService.updateSupplier(address, SID, name, phone, email);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/supplier-detail.do?SID=" + SID).forward(request, response);
    }

}
