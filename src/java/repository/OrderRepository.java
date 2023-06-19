package repository;

import config.DBConfig;
import entity.CartEntity;
import entity.ItemEntity;
import entity.MyOrderEntity;
import entity.OrderDetailEntity;
import entity.OrderHeaderEntity;
import entity.RevenueEntity;
import entity.SaleEntity;
import entity.UserEntity;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import payload.request.EmployeeOrderRequest;
import payload.request.OrderHeaderRequest;
import payload.request.UpdateOrderServicePriceRequest;
import service.UserService;

public class OrderRepository {
    
    public List<RevenueEntity> getRevenue(String datePart, String[] timeSelect) throws SQLException {
        List<RevenueEntity> list = null;
        Connection con = DBConfig.getConnection();
        //Tạo đối tượng PreparedStatement

        String sql = "";
        switch (datePart) {
            case "day":
                sql = String.format("select datepart(day, h.[date]) as [label], sum(d.price) as total from Orders h join OrderDetail d on h.id = d.orderHeader_Id where datepart(month, h.[date]) = %s and datepart(year, h.[date]) = %s group by datepart(day, h.[date])",
                        timeSelect[0], timeSelect[1]);
                break;
            case "month":
                sql = String.format("select datepart(month, h.[date]) as [label], sum(d.price) as total from Orders h join OrderDetail d on h.id = d.orderHeader_Id where datepart(year, h.[date]) = %s group by datepart(month, h.[date])",
                        timeSelect[0]);
                break;
            case "year":
                sql = String.format("select datepart(year, h.[date]) as [label], sum(d.price) as total from Orders h join OrderDetail d on h.id = d.orderHeader_Id where datepart(year, h.[date]) between %s and %s group by datepart(year, h.[date])",
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
    
    public OrderHeaderEntity getOne(int id) throws SQLException {
        OrderHeaderEntity order = new OrderHeaderEntity();
        
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Orders WHERE OID = ?");
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            order.setId(rs.getInt(1));
            order.setDate(rs.getDate(2));
            order.setStatus(rs.getString(3));
            order.setResidentId(rs.getInt(4));
            order.setEmployeeId(rs.getInt(5));
            order.setNote(rs.getString(6));
        }
        con.close();
        return order;
    }
    
    public OrderHeaderEntity getOrderEmployee(int id) throws SQLException {
        OrderHeaderEntity order = new OrderHeaderEntity();
        
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Orders WHERE EID = ?");
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            order.setId(rs.getInt(1));
            order.setDate(rs.getDate(2));
            order.setStatus(rs.getString(3));
            order.setResidentId(rs.getInt(4));
            order.setEmployeeId(rs.getInt(5));
            order.setNote(rs.getString(6));
        }
        con.close();
        return order;
    }
    
    public List<MyOrderEntity> selectMyOrders(int id) throws SQLException {
        List<MyOrderEntity> list = null;
        Connection con = DBConfig.getConnection();
        
        PreparedStatement stm = con.prepareStatement("select * from Orders where UID = ? order by OID ASC");
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
    
    public List<MyOrderEntity> selectEmployeeOrders(int id) throws SQLException {
        List<MyOrderEntity> list = null;
        Connection con = DBConfig.getConnection();
        
        PreparedStatement stm = con.prepareStatement("select * from Orders where EID = ? order by OID ASC");
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
            
            HashMap<OrderDetailEntity, String> map = this.selectEmployeeOrderlWithName(rs.getInt("OID"));
            
            MyOrderEntity fo = new MyOrderEntity(oh, map);
            list.add(fo);
        }
        con.close();
        return list;
    }
    
    public List<OrderDetailEntity> selectEmployeeOrderDetail(int id) throws SQLException {
        List<OrderDetailEntity> list = null;
        Connection con = DBConfig.getConnection();
        
        PreparedStatement stm = con.prepareStatement("select * from OrderDetail where orderheader_id = ? ");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<>();
        while (rs.next()) {
            OrderDetailEntity od = new OrderDetailEntity();
            od.setId(rs.getInt("id"));
            od.setOrderHeaderId(rs.getInt("orderHeaderId"));
            od.setServiceId(rs.getInt("serviceId"));
            od.setCategoryId(rs.getInt("categoryId"));
            od.setPrice(rs.getInt("price"));
            list.add(od);
        }
        con.close();
        return list;
    }
    
    public HashMap<OrderDetailEntity, String> selectEmployeeOrderlWithName(int orderHeaderId) throws SQLException {
        HashMap<OrderDetailEntity, String> list = null;
        Connection con = DBConfig.getConnection();
        
        PreparedStatement stm = con.prepareStatement("select od.*, a.[name]  from Orders o, OrderDetail od, account a where  o.OID = od.orderHeader_id and a.AID = o.EID  and od.orderHeader_id = ?");
        stm.setInt(1, orderHeaderId);
        ResultSet rs = stm.executeQuery();
        list = new HashMap<>();
        while (rs.next()) {
            OrderDetailEntity od = new OrderDetailEntity();
            od.setId(rs.getInt("id"));
            od.setOrderHeaderId(rs.getInt("orderHeader_id"));
            od.setServiceId(rs.getInt("service_id"));
            od.setCategoryId(rs.getInt("category_id"));
            od.setPrice(rs.getInt("price"));
            String name = rs.getString("name");
            list.put(od, name);
        }
        con.close();
        return list;
    }
    
    public List<OrderDetailEntity> selectOrderDetail(int id) throws SQLException {
        List<OrderDetailEntity> list = null;
        Connection con = DBConfig.getConnection();
        
        PreparedStatement stm = con.prepareStatement("select * from OrderDetail where orderheader_id = ? ");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<>();
        while (rs.next()) {
            OrderDetailEntity od = new OrderDetailEntity();
            od.setId(rs.getInt("id"));
            od.setOrderHeaderId(rs.getInt("orderHeaderId"));
            od.setServiceId(rs.getInt("serviceId"));
            od.setCategoryId(rs.getInt("categoryId"));
            od.setPrice(rs.getInt("price"));
            list.add(od);
        }
        con.close();
        return list;
    }
    
    public List<UpdateOrderServicePriceRequest> selectOrderDetailWithNameService(int OID) throws SQLException {
        List<UpdateOrderServicePriceRequest> list = new ArrayList<>();
        
        Connection con = DBConfig.getConnection();
        
        PreparedStatement stm = con.prepareStatement(" select o.id, o.service_id, s.name, s.lower_price, s.upper_price \n"
                                                     + "FROM OrderDetail o INNER JOIN dbo.Service s\n"
                                                     + "ON s.service_id = o.service_id \n"
                                                     + "WHERE orderheader_id = ?");
        stm.setInt(1, OID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            UpdateOrderServicePriceRequest osr = new UpdateOrderServicePriceRequest();
            osr.setId(rs.getInt(1));
            osr.setServiceID(rs.getInt(2));
            osr.setName(rs.getString(3));
            osr.setMinPrice(rs.getInt(4));
            osr.setMaxPrice(rs.getInt(5));
            
            list.add(osr);
        }
        return list;
    }
    
    public HashMap<OrderDetailEntity, String> selectOrderDetailWithName(int orderHeaderId) throws SQLException {
        HashMap<OrderDetailEntity, String> list = null;
        Connection con = DBConfig.getConnection();
        
        PreparedStatement stm = con.prepareStatement("select o.*, s.[name]\n"
                + "from orderdetail o, service s\n"
                + "where o.orderHeader_Id = ? and o.service_Id = s.service_id");
        stm.setInt(1, orderHeaderId);
        ResultSet rs = stm.executeQuery();
        list = new HashMap<>();
        while (rs.next()) {
            OrderDetailEntity od = new OrderDetailEntity();
            od.setId(rs.getInt("id"));
            
            od.setOrderHeaderId(rs.getInt("orderHeader_id"));
            od.setServiceId(rs.getInt("service_id"));
            od.setCategoryId(rs.getInt("category_id"));
            od.setPrice(rs.getInt("price"));
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
    
    public List<SaleEntity> recentOrder() throws SQLException {
        List<SaleEntity> list = null;
        Connection con = DBConfig.getConnection();
        
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select top 5 Orders.OID, Orders.time, Orders.status, Service.name from Orders \n"
                + "left join OrderDetail on Orders.OID = OrderDetail.orderHeader_id \n"
                + "left join Service on OrderDetail.category_id = Service.service_id \n"
                + "where Orders.status = 'Pending' Order by Orders.time desc");
        list = new ArrayList<>();
        while (rs.next()) {
            SaleEntity oh = new SaleEntity();
            oh.setId(rs.getInt("OID"));
            oh.setDate(rs.getDate("time"));
            oh.setStatus(rs.getString("status"));
            oh.setServicename(rs.getString("name"));
            list.add(oh);
        }
        con.close();
        return list;
    }
    
    public int completeOrder() throws SQLException {
        int count = 0;
        Connection con = DBConfig.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select count (orders.OID) as CountOrders from Orders where orders.status = 'Completed'");
        
        while (rs.next()) {
            count = rs.getInt(1);
        }
        con.close();
        return count;
    }
    
    public int Revenue() throws SQLException {
        int rev = 0;
        Connection con = DBConfig.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select sum(OrderDetail.price) "
                + "as Revenue from OrderDetail left join Orders on OrderDetail.orderHeader_Id = Orders.OID where Orders.status='Completed'");
        
        while (rs.next()) {
            rev = rs.getInt(1);
        }
        con.close();
        return rev;
    }
    
    public int countAcc() throws SQLException {
        int count = 0;
        Connection con = DBConfig.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select count (Account.AID) as accCount from Account where roleId = '1'");
        
        while (rs.next()) {
            count = rs.getInt(1);
        }
        con.close();
        return count;
    }
    
    public List<SaleEntity> recentSale() throws SQLException {
        List list = null;
        Connection con = DBConfig.getConnection();
        
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select Orders.OID, Account.name, Service.name, OrderDetail.price, Orders.status \n"
                + "	from Orders left join Account on Orders.UID = Account.AID  \n"
                + "	left join OrderDetail on Orders.OID = OrderDetail.orderHeader_Id\n"
                + "	left join Service on OrderDetail.service_Id = Service.service_id");
        list = new ArrayList<>();
        while (rs.next()) {
            SaleEntity sa = new SaleEntity();
            sa.setId(rs.getInt(1));
            sa.setName(rs.getString(2));
            sa.setServicename(rs.getString(3));
            sa.setPrice(rs.getDouble(4));
            sa.setStatus(rs.getString(5));
            
            list.add(sa);
        }
        con.close();
        return list;
    }
    
    public List<SaleEntity> cateTraffic() throws SQLException {
        List list = null;
        Connection con = DBConfig.getConnection();
        
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select Category.name, COUNT(OrderDetail.category_Id) "
                + "as CountService from OrderDetail "
                + "left join Category on OrderDetail.category_Id = Category.CID "
                + "group by Category.name");
        list = new ArrayList<>();
        while (rs.next()) {
            SaleEntity sa = new SaleEntity();
            sa.setCatename(rs.getString(1));
            sa.setCountcate(rs.getInt(2));
            
            list.add(sa);
        }
        con.close();
        return list;
    }
    
    public List<SaleEntity> topsell() throws SQLException {
        List<SaleEntity> list = null;
        Connection con = DBConfig.getConnection();
        
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select top 5 Orders.OID, Service.name, OrderDetail.price ,COUNT(service.service_id) as Sold, Sum(OrderDetail.price) as Revenue\n"
                + "from Orders left join OrderDetail ON Orders.OID = OrderDetail.orderHeader_id \n"
                + "left join Service on Service.service_id = OrderDetail.service_id where Orders.status = 'Completed' \n"
                + "GROUP BY Orders.OID, Service.name, OrderDetail.price");
        list = new ArrayList<>();
        while (rs.next()) {
            SaleEntity oh = new SaleEntity();
            oh.setId(rs.getInt("OID"));
            oh.setServicename(rs.getString("name"));
            oh.setPrice(rs.getDouble("price"));
            oh.setSold(rs.getInt("Sold"));
            oh.setRevenue(rs.getDouble("Revenue"));
            list.add(oh);
        }
        con.close();
        return list;
    }

    /**
     *
     * @param date
     * @param status
     * @param UID
     * @param EID
     * @param note
     * @throws SQLException
     */
//    public void addOrder(Date date, String status, int UID, int EID, String note) throws SQLException {
//
//        Connection con = DBConfig.getConnection();
//        String sql = "insert into Orders values(?,?,?,?,?)";
//        PreparedStatement stm = con.prepareStatement(sql);
//        stm.setDate(1, date);
//        stm.setString(2, status);
//        stm.setInt(3, UID);
//        stm.setInt(4, EID);
//        stm.setString(5, note);
//        stm.executeUpdate();
//        con.close();
//    }
    public List<Integer> getValidatedMonth() {
        List<Boolean> m = new ArrayList<Boolean>();
        for (int i = 0; i < 12; i++) {
            m.add(false);
        }
        try {
            Connection con = DBConfig.getConnection();
            String sql = "select * from Orders";
            PreparedStatement stm1 = con.prepareStatement(sql);
            ResultSet rs = stm1.executeQuery();
            while (rs.next()) {
                java.util.Date date = rs.getDate("time");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);
                m.set(month, true);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            if (m.get(i) == true) {
                result.add(i + 1);
            }
        }
        return result;
    }
    
    public String getJsMonthArray(List<Integer> l) {
        String result = "";
        
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        
        for (int i = 0; i < l.size(); i++) {
            if (i == 0) {
                result += "['" + months[l.get(i) - 1] + "',";
            } else if (i == l.size() - 1) {
                result += "'" + months[l.get(i) - 1] + "']";
            } else {
                result += "'" + months[l.get(i) - 1] + "',";
            }
        }
        return result;
    }
    
    public List<Double> getTotalMoneyInMonth() {
        List<Double> l = new ArrayList<Double>();
        for (int i = 0; i < 12; i++) {
            l.add(0.0);
        }
        try {
            Connection con = DBConfig.getConnection();
            String sql = "select * from Orders";
            PreparedStatement stm1 = con.prepareStatement(sql);
            ResultSet rs = stm1.executeQuery();
            while (rs.next()) {
                java.util.Date date = rs.getDate("time");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);
                l.set(month, l.get(month) + getTotalMoneyOfOrder(rs.getInt("oid")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        List<Double> result = new ArrayList<Double>();
        for (Double d : l) {
            if (d != 0.0) {
                result.add(d);
            }
        }
        return result;
    }
    
    public String getJsTotalMoneyArray(List<Double> l) {
        String result = "";
        
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) != 0.0) {
                if (i == 0) {
                    result += "[" + l.get(i) + ",";
                } else if (i == l.size() - 1) {
                    result += l.get(i) + "]";
                } else {
                    result += l.get(i) + ",";
                }
            }
        }
        return result;
    }
    
    public int getTotalMoneyOfOrder(int oId) throws SQLException {
        String query = "select SUM(od.price) as totalMoney from Orders as o join OrderDetail as od on o.OID = od.orderHeader_Id where o.OID = ? and o.status = 'Completed'";
        int result = 0;
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, oId);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            result += rs.getInt("totalMoney");
        }
        
        return result;
    }
    
    public void addOrder(UserEntity user, CartEntity cart, String note) throws SQLException {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        UserService uService = new UserService();
        Connection con = DBConfig.getConnection();
        String sql = "insert into Orders values(?, 'Pending',?, null, ?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, date);
        stm.setInt(2, user.getAID());
        stm.setString(3, note);
        stm.executeUpdate();
        String sql1 = "select top 1 oid from Orders order by oid desc";
        PreparedStatement stm1 = con.prepareStatement(sql1);
        ResultSet rs = stm1.executeQuery();
        while (rs.next()) {
            int oid = rs.getInt("oid");
            for (ItemEntity item : cart.getItems()) {
                String sql2 = "insert into OrderDetail values(?, ?, ?, null)";
                PreparedStatement stm2 = con.prepareStatement(sql2);
                stm2.setInt(1, oid);
                stm2.setInt(2, item.getService().getServiceID());
                stm2.setInt(3, item.getService().getCategoryID());
                stm2.executeUpdate();
            }
        }
        con.close();
        
    }
    
    public ArrayList<OrderHeaderRequest> getAllOrders() throws Exception {
        ArrayList<OrderHeaderRequest> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select o.OID, a.AID, a.name, o.time, o.status, o.note from Orders o\n"
                    + "left join Account a\n"
                    + "on o.UID = a.AID";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        if (rs != null) {
            while (rs.next()) {
                OrderHeaderRequest entity = new OrderHeaderRequest();
                entity.setId(rs.getInt(1));
                entity.setUid(rs.getInt(2));
                entity.setResidentName(rs.getNString(3));
                entity.setDate(rs.getDate(4));
                entity.setStatus(rs.getString(5));
                entity.setNote(rs.getString(6));
                
                HashMap<OrderDetailEntity, String> map = this.selectEmployeeOrderlWithName(rs.getInt("OID"));
                
                entity.setOd(map);
                
                list.add(entity);
            }
        }
        return list;
    }
    
    public void updatePrice(int id, double price) throws SQLException{
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update OrderDetail set price = ? where id = ?");
        pstm.setDouble(1, price);
        pstm.setInt(2, id);
        int count = pstm.executeUpdate();
        
        con.close();
    }
    
    public ArrayList<EmployeeOrderRequest> getEmployeeListForOrderList() throws Exception {
        ArrayList<EmployeeOrderRequest> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select a.aid, a.name from Orders o \n"
                    + "left join Account a\n"
                    + "on o.EID = a.AID";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        if (rs != null) {
            while (rs.next()) {
                EmployeeOrderRequest entity = new EmployeeOrderRequest();
                entity.setAid(rs.getInt(1));
                entity.setName(rs.getString(2));
                list.add(entity);
            }
        }
        return list;
    }
    
    public static void main(String[] args) throws SQLException, Exception {
        OrderRepository op = new OrderRepository();
        System.out.println(op.selectOrderDetailWithNameService(38));
    }
}
