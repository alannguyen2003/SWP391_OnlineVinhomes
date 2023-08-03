package service;

import entity.CartEntity;
import entity.MyOrderEntity;
import entity.OrderDetailEntity;
import entity.OrderHeaderEntity;
import entity.RevenueEntity;
import entity.SaleEntity;
import entity.UserEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import payload.request.AdminOrderListRequest;
import payload.request.MyOrderRequest;
import payload.request.OrderDetailRequest;
import payload.request.UpdateOrderServicePriceRequest;
import repository.OrderRepository;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    //  ----------------------------------------
    //
    //  Get Order Function
    //
    //  ----------------------------------------
    public List<OrderHeaderEntity> selectAll() throws SQLException {
        return orderRepository.selectAll();
    }

    public OrderHeaderEntity getOne(int id) throws SQLException {
        return orderRepository.getOne(id);
    }

    public List<OrderDetailEntity> selectOrderDetail(int id) throws SQLException {
        return orderRepository.selectOrderDetail(id);
    }

    public ArrayList<String> getStatus() throws SQLException {
        return orderRepository.getStatus();
    }

    //  ----------------------------------------
    //
    //  Get Order From 1 Resident Function
    //
    //  ----------------------------------------
    public List<MyOrderEntity> selectMyOrders(int id) throws SQLException {
        return orderRepository.selectMyOrders(id);
    }
    
    public List<MyOrderRequest> selectMyOrdersRequest(int id) throws SQLException {
        return orderRepository.selectMyOrdersRequest(id);
    }

    //  ----------------------------------------
    //
    //  Get A list of Order and its Order_Detail by Hash Map Function
    //
    //  ----------------------------------------
    public HashMap<OrderDetailEntity, String> selectOrderDetailWithID(int orderHeaderId) throws SQLException {
        return orderRepository.selectOrderDetailWithID(orderHeaderId);
    }

    //  ----------------------------------------
    //
    //  Get All Order of Coordinator need to manage Function
    //
    //  ----------------------------------------
    public List<AdminOrderListRequest> selectOrdersCoordinator(int id) throws SQLException {
        return orderRepository.selectOrdersCoordinator(id);
    }

    public List<UpdateOrderServicePriceRequest> selectOrderDetailWithNameService(int OID) throws SQLException {
        return orderRepository.selectOrderDetailWithNameService(OID);
    }

    //  ----------------------------------------
    //
    //  Admin Notifications Icon Function
    //
    //  ----------------------------------------
    public int getPendingOrders(int blockId) throws SQLException {
        return orderRepository.getPendingOrders(blockId);
    }

    //  ----------------------------------------
    //
    //  Admin Dashboard Function
    //
    //  ----------------------------------------
    public List<SaleEntity> recentOrder() throws SQLException {
        return orderRepository.recentOrder();
    }

    public int completeOrder() throws SQLException {
        return orderRepository.completeOrder();
    }

    public int Revenue() throws SQLException {
        return orderRepository.Revenue();
    }

    public int countAcc() throws SQLException {
        return orderRepository.countAcc();
    }

    public List<SaleEntity> recentSale() throws SQLException {
        return orderRepository.recentSale();
    }

    public List<SaleEntity> cateTraffic() throws SQLException {
        return orderRepository.cateTraffic();
    }

    public List<SaleEntity> topsell() throws SQLException {
        return orderRepository.topsell();
    }

    //  ----------------------------------------
    //
    //  Admin Order-List Function
    //
    //  ----------------------------------------
    public ArrayList<AdminOrderListRequest> getAllOrders() throws Exception {
        return orderRepository.getAllOrders();
    }

    //  ----------------------------------------
    //
    //  Admin Coordinator Update Information for Order Fuction
    //
    //  ----------------------------------------
    public void updateStatus(int OID, int CID, String status, String note) throws SQLException {
        orderRepository.updateStatus(OID, CID, status, note);
    }

    public void updatePrice(int id, double price) throws SQLException {
        orderRepository.updatePrice(id, price);
    }
    
    public void updateSupplier(int id, int supplierId) throws SQLException {
        orderRepository.updateSupplier(id, supplierId);
    }

    //  ----------------------------------------
    //
    //  Admin Revenue Function
    //
    //  ----------------------------------------
    public String getTotalMoneyInMonth() {
        return orderRepository.getJsTotalMoneyArray(orderRepository.getTotalMoneyInMonth());
    }

    public String getMonth() {
        return orderRepository.getJsMonthArray(orderRepository.getValidatedMonth());
    }

    public List<RevenueEntity> getRevenue(String datePart, String[] timeSelect) throws SQLException {
        return orderRepository.getRevenue(datePart, timeSelect);
    }

    //  ----------------------------------------
    //
    //  Resident Interact Order Function
    //
    //  ----------------------------------------
    public void addOrder(UserEntity user, CartEntity cart, String note, int coordinatorId) throws SQLException {
        orderRepository.addOrder(user, cart, note, coordinatorId);
    }

    public void cancelOrder(int oId) throws SQLException {
        orderRepository.cancelOrder(oId);
    }
    
    public ArrayList<OrderDetailRequest> getAllOrderDetailById(int id) throws Exception {
        return orderRepository.getAllOrderDetailById(id);
    }
    
    public UserEntity getNameFromOrder(int OID) throws SQLException{
        return orderRepository.getNameFromOrder(OID);
    }

    public static void main(String[] args) throws Exception {
        OrderService orderService = new OrderService();
        orderService.updatePrice(25, 70);
        System.out.println(orderService.getPendingOrders(1));
    }
}
