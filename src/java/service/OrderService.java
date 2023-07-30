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
import payload.request.EmployeeOrderRequest;
import payload.request.OrderHeaderRequest;
import payload.request.UpdateOrderServicePriceRequest;
import repository.OrderRepository;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public List<RevenueEntity> getRevenue(String datePart, String[] timeSelect) throws SQLException {
        return orderRepository.getRevenue(datePart, timeSelect);
    }

    public List<OrderHeaderEntity> selectAll() throws SQLException {
        return orderRepository.selectAll();
    }
    
    public OrderHeaderEntity getOne(int id) throws SQLException {
        return orderRepository.getOne(id);
    }
    
    public OrderHeaderEntity getOrderEmployee(int id) throws SQLException {
        return orderRepository.getOrderEmployee(id);
    }

    public List<MyOrderEntity> selectMyOrders(int id) throws SQLException {
        return orderRepository.selectMyOrders(id);
    }
  
    public List<OrderDetailEntity> selectOrderDetail(int id) throws SQLException {
        return orderRepository.selectOrderDetail(id);
    }

    public List<UpdateOrderServicePriceRequest> selectOrderDetailWithNameService(int OID) throws SQLException {
        return orderRepository.selectOrderDetailWithNameService(OID);
    }
    
    public HashMap<OrderDetailEntity, String> selectOrderDetailWithName(int orderHeaderId) throws SQLException {
        return orderRepository.selectOrderDetailWithName(orderHeaderId);
    }
    
    public List<MyOrderEntity> selectEmployeeOrders(int id) throws SQLException {
        return orderRepository.selectEmployeeOrders(id);
    }
    
    public HashMap<OrderDetailEntity, String> selectEmployeeOrderlWithName(int orderHeaderId) throws SQLException {
        return orderRepository.selectEmployeeOrderlWithName(orderHeaderId);
    }

    public void updateStatus(int oId, int eId, String status) throws SQLException {
        orderRepository.updateStatus(oId, eId, status);
    }
    
    public void cancelOrder(int oId) throws SQLException {
        orderRepository.cancelOrder(oId);
    }
    
    public void updatePrice(int id, double price) throws SQLException {
        orderRepository.updatePrice(id, price);
    }
    
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
    
    public String getTotalMoneyInMonth() {
        return orderRepository.getJsTotalMoneyArray(orderRepository.getTotalMoneyInMonth());
    }
    
    public String getMonth() {
        return orderRepository.getJsMonthArray(orderRepository.getValidatedMonth());

    }
    public void addOrder(UserEntity user, CartEntity cart, String note) throws SQLException {
        orderRepository.addOrder(user, cart, note);
    }
    
    public int getPendingOrders(int blockId) throws SQLException {
        return orderRepository.getPendingOrders(blockId);
    }
    
    public ArrayList<OrderHeaderRequest> getAllOrders() throws Exception {
        ArrayList<OrderHeaderRequest> list = orderRepository.getAllOrders();
        return list;
    }
    
    public static void main(String[] args) throws Exception {
        OrderService orderService = new OrderService();
        orderService.updatePrice(25, 70);
        System.out.println(orderService.getPendingOrders(1));
    }
}
