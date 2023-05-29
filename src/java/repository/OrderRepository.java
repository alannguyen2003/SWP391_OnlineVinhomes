
package repository;

import config.DBConfig;
import entity.MyOrderEntity;
import entity.OrderDetailEntity;
import entity.OrderHeaderEntity;
import entity.RevenueEntity;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OrderRepository {
    
    public List<RevenueEntity> getRevenue(String datePart, String[] timeSelect) throws SQLException {
        List<RevenueEntity> list = null;
        Connection con = DBConfig.getConnection();
        //Tạo đối tượng PreparedStatement

        String sql = "";
        switch (datePart) {
            case "day":
                sql = String.format("select datepart(day, h.[date]) as [label], sum(d.price) as total from Orders h join OrderDetail d on h.id = d.orderHeaderId where datepart(month, h.[date]) = %s and datepart(year, h.[date]) = %s group by datepart(day, h.[date])",
                        timeSelect[0], timeSelect[1]);
                break;
            case "month":
                sql = String.format("select datepart(month, h.[date]) as [label], sum(d.price) as total from Orders h join OrderDetail d on h.id = d.orderHeaderId where datepart(year, h.[date]) = %s group by datepart(month, h.[date])",
                        timeSelect[0]);
                break;
            case "year":
                sql = String.format("select datepart(year, h.[date]) as [label], sum(d.price) as total from Orders h join OrderDetail d on h.id = d.orderHeaderId where datepart(year, h.[date]) between %s and %s group by datepart(year, h.[date])",
                        timeSelect[0], timeSelect[1]);
                break;
        }

        Statement stm = con.createStatement(); //Thực thi lệnh sql

        ResultSet rs = stm.executeQuery(sql);
        list = new ArrayList<>();
        while (rs.next()) {
            list.add(new RevenueEntity(rs.getString("label"), rs.getDouble("total")));
        }
        return list;
    }
    
    public List<OrderHeaderEntity> selectAll() throws SQLException {
        List<OrderHeaderEntity> list = null;
        Connection con = DBConfig.getConnection();

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from Orders order by OID desc");
        list = new ArrayList<>();
        while (rs.next()) {
            OrderHeaderEntity oh = new OrderHeaderEntity();
            oh.setId(rs.getInt("OID"));
            oh.setDate(rs.getDate("time"));
            oh.setResidentId(rs.getInt("UID"));
            oh.setEmployeeId(rs.getInt("EID"));
            oh.setStatus(rs.getString("status"));
            oh.setNote(rs.getString("note"));
            list.add(oh);
        }
        con.close();
        return list;
    }
    
    public List<MyOrderEntity> selectMyOrders(int id) throws SQLException {
        List<MyOrderEntity> list = null;
        Connection con = DBConfig.getConnection();

        PreparedStatement stm = con.prepareStatement("select * from Orders where UID = ? order by OID desc");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<>();
        while (rs.next()) {
            OrderHeaderEntity oh = new OrderHeaderEntity();
            oh.setId(rs.getInt("OID"));
            oh.setDate(rs.getDate("time"));
            oh.setResidentId(rs.getInt("UID"));
            oh.setEmployeeId(rs.getInt("EID"));
            oh.setStatus(rs.getString("status"));
            oh.setNote(rs.getString("note"));
            
            HashMap<OrderDetailEntity, String> map = this.selectOrderDetailWithName(rs.getInt("OID"));
            
            MyOrderEntity fo = new MyOrderEntity(oh, map);
            list.add(fo);
        }
        con.close();
        return list;
    }
    
    public List<OrderDetailEntity> selectOrderDetail(int id) throws SQLException {
        List<OrderDetailEntity> list = null;
        Connection con = DBConfig.getConnection();

        PreparedStatement stm = con.prepareStatement("select * from OrderDetail where orderheaderid = ? ");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<>();
        while (rs.next()) {
            OrderDetailEntity od = new OrderDetailEntity();
            od.setId(rs.getInt("id"));
            od.setOrderHeaderId(rs.getInt("orderHeaderId"));
            od.setServiceId(rs.getInt("serviceId"));
            od.setCategoryId(rs.getInt("categoryId"));
            od.setMinPrice(rs.getInt("min_price"));
            od.setMaxPrice(rs.getInt("max_price"));
            list.add(od);
        }
        con.close();
        return list;
    }
    
      public HashMap<OrderDetailEntity, String> selectOrderDetailWithName(int orderHeaderId) throws SQLException {
        HashMap<OrderDetailEntity, String> list = null;
        Connection con = DBConfig.getConnection();

        PreparedStatement stm = con.prepareStatement("select o.*, s.[name]\n"
                + "from orderdetail o, service s\n"
                + "where o.orderHeaderId = ? and o.serviceId = s.service_id");
        stm.setInt(1, orderHeaderId);
        ResultSet rs = stm.executeQuery();
        list = new HashMap<>();
        while (rs.next()) {
            OrderDetailEntity od = new OrderDetailEntity();
            od.setId(rs.getInt("id"));
            od.setOrderHeaderId(rs.getInt("orderHeaderId"));
            od.setServiceId(rs.getInt("serviceId"));
            od.setCategoryId(rs.getInt("categoryId"));
            od.setMinPrice(rs.getInt("min_price"));
            od.setMaxPrice(rs.getInt("max_price"));
            String name = rs.getString("name");
            list.put(od, name);
        }
        con.close();
        return list;
    }
      
      public void updateStatus(int oId, int eId, String status) throws SQLException {
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Orders set EID = ?, status = ? where OID = ?");
        pstm.setInt(1, eId);
        pstm.setString(2, status);
        pstm.setInt(3, oId);
        int count = pstm.executeUpdate();

        con.close();
    }
      
       public static void main(String[] args) {
        OrderRepository od = new OrderRepository();
        String[] array = {"2013", "2023"};
        try {
            od.updateStatus(3, 4, "Failed");
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
