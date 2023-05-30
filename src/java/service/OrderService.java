package service;

import entity.MyOrderEntity;
import entity.OrderDetailEntity;
import entity.OrderHeaderEntity;
import entity.RevenueEntity;
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
}
