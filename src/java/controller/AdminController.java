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
                    case "updateOrder":
                        updateOrder(request, response);
                        break;
                    case "employee-order":
                        loadEmployeeOrderList(request, response);
                        break;
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
                        request.setAttribute("activeTab", "pendingOrder");
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

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("OID"));
        String status = request.getParameter("status");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        os.updateStatus(orderId, employeeId, status);
        String message = "Update successfully";
        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin/order-detail.do?OID=" + orderId).forward(request, response);

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
        switch (op) {
            case "getAll":
                list = rs.getAllResident();
                // Calculate the total number of pages
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);

                break;
            case "generate":
                list = rs.getAllResident();
                String filterOption = request.getParameter("filterOption");
                int filterValue1 = Integer.parseInt(request.getParameter("filterValue1"));
                int filterValue2 = Integer.parseInt(request.getParameter("filterValue2"));
                String searchOption = request.getParameter("searchOption");
                String searchValue = request.getParameter("searchValue");
                String sortOption = request.getParameter("sortOption");
                String sortType = request.getParameter("sortType");

                list = filterSearchSortResident(list, filterOption, filterValue1, filterValue2, searchOption, searchValue, sortOption, sortType);

                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);

                request.setAttribute("filterOption", filterOption);
                request.setAttribute("filterValue1", filterValue1);
                request.setAttribute("filterValue2", filterValue2);
                request.setAttribute("searchOption", searchOption);
                request.setAttribute("searchValue", searchValue);
                request.setAttribute("sortOption", sortOption);
                request.setAttribute("sortType", sortType);
                break;
        }
        // Cắt danh sách dữ liệu theo phân trang
        List<UserEntity> subList = paginateListResident(list, currentPage, pageSize);

        request.setAttribute("activeTab", "resident");
        request.setAttribute("op", op);
        request.setAttribute("blockList", bs.getAllBlock());
        request.setAttribute("list", subList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    private List<UserEntity> filterSearchSortResident(List<UserEntity> list, String filterOption, int filterValue1, int filterValue2, String searchOption,
            String searchValue, String sortOption, String sortType) {
        // Lọc dữ liệu nếu có yêu cầu
        if (filterOption != null && !filterOption.isEmpty()) {
            list = filterListResident(list, filterOption, filterValue1, filterValue2);
        }

        // Tìm kiếm dữ liệu nếu có yêu cầu
        if (searchOption != null && !searchOption.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
            list = searchInListResident(list, searchOption, searchValue);
        }

        // Sắp xếp dữ liệu nếu có yêu cầu
        if (sortOption != null && !sortOption.isEmpty() && sortType != null && !sortType.isEmpty()) {
            list = sortListResident(list, sortOption, sortType);
        }

        return list;
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

// Phương thức sắp xếp danh sách dữ liệu
    private List<UserEntity> sortListResident(List<UserEntity> list, String sortOption, String sortType) {
        List<UserEntity> sortedList = new ArrayList<>(list);
        switch (sortOption) {
            case "name":
                sortedList.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
                break;
            case "status":
                sortedList.sort(Comparator.comparingInt(UserEntity::getStatus));
                break;
            case "email":
                sortedList.sort((e1, e2) -> e1.getEmail().compareTo(e2.getEmail()));
                break;
            case "phone":
                sortedList.sort((e1, e2) -> e1.getPhone().compareTo(e2.getPhone()));
                break;
        }
        if (sortType.equals("Descending")) {
            Collections.reverse(sortedList);
        }
        return sortedList;
    }

    private List<UserEntity> searchInListResident(List<UserEntity> list, String searchOption, String searchValue) {
        List<UserEntity> searchResults = new ArrayList<>();
        System.out.println(searchValue);
        for (UserEntity user : list) {
            if (searchOption.equals("name")) {
                if (user.getName().toLowerCase().contains(searchValue.toLowerCase())) {
                    searchResults.add(user);
                }
            } else if (searchOption.equals("email")) {
                if (user.getEmail().toLowerCase().contains(searchValue.toLowerCase())) {
                    searchResults.add(user);
                }
            } else if (searchOption.equals("phone")) {
                if (user.getPhone().toLowerCase().contains(searchValue.toLowerCase())) {
                    searchResults.add(user);
                }
            }
        }
        return searchResults;
    }

    private ArrayList<OrderHeaderRequest> paginateListOrders(ArrayList<OrderHeaderRequest> list, int currentPage, int pageSize) {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());
        return (ArrayList<OrderHeaderRequest>) list.subList(startIndex, endIndex);
    }

    private List<UserEntity> paginateListResident(List<UserEntity> list, int currentPage, int pageSize) {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());
        return list.subList(startIndex, endIndex);
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

    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
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
        int currentPage = 1;

        if (indexPage != null) {
            currentPage = Integer.parseInt(indexPage);
        }
        List<ServiceEntity> list = new ArrayList<>();
        int totalPages = 0;
        int totalItems = 0;
        int pageSize = 10;

        // Lấy tất cả dữ liệu Service ban đầu
        // Xử lý dữ liệu nếu có yêu cầu
        String op = (String) request.getParameter("op");
        switch (op) {
            case "getAll":
                list = ss.getAllService();
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);
                break;
            case "generate":
                list = ss.getAllService();
                String filterOption = request.getParameter("filterOption");
                int filterValue1 = Integer.parseInt(request.getParameter("filterValue1"));
                int filterValue2 = Integer.parseInt(request.getParameter("filterValue2"));
                String searchOption = request.getParameter("searchOption");
                String searchValue = request.getParameter("searchValue");
                String sortOption = request.getParameter("sortOption");
                String sortType = request.getParameter("sortType");

                list = filterSearchSort(list, filterOption, filterValue1, filterValue2, searchOption, searchValue, sortOption, sortType);

                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);

                request.setAttribute("filterOption", filterOption);
                request.setAttribute("filterValue1", filterValue1);
                request.setAttribute("filterValue2", filterValue2);
                request.setAttribute("searchOption", searchOption);
                request.setAttribute("searchValue", searchValue);
                request.setAttribute("sortOption", sortOption);
                request.setAttribute("sortType", sortType);
                break;
        }
        // Cắt danh sách dữ liệu theo phân trang
        List<ServiceEntity> subList = paginateList(list, currentPage, pageSize);

        request.setAttribute("activeTab", "service");
        request.setAttribute("op", op);
        request.setAttribute("categoryList", cs.getAllCategory());
        request.setAttribute("supplierList", supplierService.getAllSupplier());
        request.setAttribute("list", subList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    private List<ServiceEntity> filterSearchSort(List<ServiceEntity> list, String filterOption, int filterValue1, int filterValue2, String searchOption,
            String searchValue, String sortOption, String sortType) {
        // Lọc dữ liệu nếu có yêu cầu
        if (filterOption != null && !filterOption.isEmpty()) {
            list = filterList(list, filterOption, filterValue1, filterValue2);
        }

        // Tìm kiếm dữ liệu nếu có yêu cầu
        if (searchOption != null && !searchOption.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
            list = searchInList(list, searchOption, searchValue);
        }

        // Sắp xếp dữ liệu nếu có yêu cầu
        if (sortOption != null && !sortOption.isEmpty() && sortType != null && !sortType.isEmpty()) {
            list = sortList(list, sortOption, sortType);
        }

        return list;
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

// Phương thức sắp xếp danh sách dữ liệu
    private List<ServiceEntity> sortList(List<ServiceEntity> list, String sortOption, String sortType) {
        List<ServiceEntity> sortedList = new ArrayList<>(list);
        switch (sortOption) {
            case "name":
                sortedList.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
                break;
            case "category":
                sortedList.sort(Comparator.comparingInt(ServiceEntity::getCategoryID));
                break;
            case "minPrice":
            case "maxPrice":
                sortedList.sort((e1, e2) -> Double.compare(e1.getLowerPrice(), e2.getLowerPrice()));
                break;
        }
        if (sortType.equals("Descending")) {
            Collections.reverse(sortedList);
        }
        return sortedList;
    }

    private List<ServiceEntity> searchInList(List<ServiceEntity> list, String searchOption, String searchValue) {
        List<ServiceEntity> searchResults = new ArrayList<>();
        System.out.println(searchValue);
        for (ServiceEntity service : list) {
            if (searchOption.equals("name")) {
                if (service.getName().toLowerCase().contains(searchValue.toLowerCase())) {
                    searchResults.add(service);
                }
            } else if (searchOption.equals("description")) {
                if (service.getDescription().toLowerCase().contains(searchValue.toLowerCase())) {
                    searchResults.add(service);
                }
            }
        }
        return searchResults;
    }

    private List<ServiceEntity> paginateList(List<ServiceEntity> list, int currentPage, int pageSize) {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());
        return list.subList(startIndex, endIndex);
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
        switch (op) {
            case "getAll":
                list = us.getAllUser();
                // Calculate the total number of pages
                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);

                break;
            case "generate":
                list = us.getAllUser();
                String filterOption = request.getParameter("filterOption");
                String filterValue1 = request.getParameter("filterValue1");
                int filterValue2 = Integer.parseInt(request.getParameter("filterValue2"));
                int filterValue3 = Integer.parseInt(request.getParameter("filterValue3"));
                String searchOption = request.getParameter("searchOption");
                String searchValue = request.getParameter("searchValue");
                String sortOption = request.getParameter("sortOption");
                String sortType = request.getParameter("sortType");

                list = filterSearchSortUser(list, filterOption, filterValue1, filterValue2, filterValue3, searchOption, searchValue, sortOption, sortType);

                totalItems = list.size();
                totalPages = (int) Math.ceil((double) totalItems / pageSize);

                request.setAttribute("filterOption", filterOption);
                request.setAttribute("filterValue1", filterValue1);
                request.setAttribute("filterValue2", filterValue2);
                request.setAttribute("filterValue3", filterValue3);
                request.setAttribute("searchOption", searchOption);
                request.setAttribute("searchValue", searchValue);
                request.setAttribute("sortOption", sortOption);
                request.setAttribute("sortType", sortType);
                break;
        }
        // Cắt danh sách dữ liệu theo phân trang
        List<UserEntity> subList = paginateListUser(list, currentPage, pageSize);

        request.setAttribute("activeTab", "user");
        request.setAttribute("op", op);
        request.setAttribute("roleList", rss.getAllRole());
        request.setAttribute("list", subList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
    }

    private List<UserEntity> filterSearchSortUser(List<UserEntity> list, String filterOption, String filterValue1, int filterValue2, int filterValue3, String searchOption,
            String searchValue, String sortOption, String sortType) {
        // Lọc dữ liệu nếu có yêu cầu
        if (filterOption != null && !filterOption.isEmpty()) {
            list = filterListUser(list, filterOption, filterValue1, filterValue2, filterValue3);
        }

        // Tìm kiếm dữ liệu nếu có yêu cầu
        if (searchOption != null && !searchOption.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
            list = searchInListUser(list, searchOption, searchValue);
        }

        // Sắp xếp dữ liệu nếu có yêu cầu
        if (sortOption != null && !sortOption.isEmpty() && sortType != null && !sortType.isEmpty()) {
            list = sortListUser(list, sortOption, sortType);
        }

        return list;
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

// Phương thức sắp xếp danh sách dữ liệu
    private List<UserEntity> sortListUser(List<UserEntity> list, String sortOption, String sortType) {
        List<UserEntity> sortedList = new ArrayList<>(list);
        switch (sortOption) {
            case "id":
                sortedList.sort(Comparator.comparingInt(UserEntity::getAID));
                break;
            case "name":
                sortedList.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
                break;
            case "gender":
                sortedList.sort((e1, e2) -> e1.getGender().compareTo(e2.getGender()));
                break;
            case "email":
                sortedList.sort((e1, e2) -> e1.getEmail().compareTo(e2.getEmail()));
                break;
            case "phone":
                sortedList.sort((e1, e2) -> e1.getPhone().compareTo(e2.getPhone()));
                break;
            case "role":
                sortedList.sort(Comparator.comparingInt(UserEntity::getRoleID));
                break;
            case "status":
                sortedList.sort(Comparator.comparingInt(UserEntity::getStatus));
                break;
        }
        if (sortType.equals("Descending")) {
            Collections.reverse(sortedList);
        }
        return sortedList;
    }

    private List<UserEntity> searchInListUser(List<UserEntity> list, String searchOption, String searchValue) {
        List<UserEntity> searchResults = new ArrayList<>();
        System.out.println(searchValue);
        for (UserEntity user : list) {
            if (searchOption.equals("name")) {
                if (user.getName().toLowerCase().contains(searchValue.toLowerCase())) {
                    searchResults.add(user);
                }
            } else if (searchOption.equals("email")) {
                if (user.getEmail().toLowerCase().contains(searchValue.toLowerCase())) {
                    searchResults.add(user);
                }
            } else if (searchOption.equals("phone")) {
                if (user.getPhone().toLowerCase().contains(searchValue.toLowerCase())) {
                    searchResults.add(user);
                }
            }
        }
        return searchResults;
    }

    private List<UserEntity> paginateListUser(List<UserEntity> list, int currentPage, int pageSize) {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());
        return list.subList(startIndex, endIndex);
    }

    private void user_detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, Exception {
        String aid = request.getParameter("AID");

        UserEntity user = us.getUser(Integer.parseInt(aid));

        request.setAttribute("u", user);

    }

    //é đù ăng seng //m quay ha
}
