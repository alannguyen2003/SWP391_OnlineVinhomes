package service;

import entity.MyOrderEntity;
import entity.OrderDetailEntity;
import entity.OrderHeaderEntity;
import entity.RevenueEntity;
import entity.SaleEntity;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import repository.OrderRepository;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public List<RevenueEntity> getRevenue(String datePart, String[] timeSelect) throws SQLException {
        return orderRepository.getRevenue(datePart, timeSelect);
    }

    public List<OrderHeaderEntity> selectAll() throws SQLException {
        return orderRepository.selectAll();
    }

    public List<MyOrderEntity> selectMyOrders(int id) throws SQLException {
        return orderRepository.selectMyOrders(id);
    }

    public List<OrderDetailEntity> selectOrderDetail(int id) throws SQLException {
        return orderRepository.selectOrderDetail(id);
    }

    public HashMap<OrderDetailEntity, String> selectOrderDetailWithName(int orderHeaderId) throws SQLException {
        return orderRepository.selectOrderDetailWithName(orderHeaderId);
    }

    public void updateStatus(int oId, int eId, String status) throws SQLException {
        orderRepository.updateStatus(oId, eId, status);
    }

    
    public List<OrderHeaderEntity> recentOrder() throws SQLException {
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
    public String getTotalMoneyInMonth() {
        return orderRepository.getJsTotalMoneyArray(orderRepository.getTotalMoneyInMonth());
    }
    
    public String getMonth() {
        return orderRepository.getJsMonthArray(orderRepository.getValidatedMonth());

    }
}
