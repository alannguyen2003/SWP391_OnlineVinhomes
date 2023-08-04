
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
import java.lang.reflect.Array;
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
import payload.request.AdminOrderListRequest;
import payload.request.MyOrderRequest;
import payload.request.OrderDetailRequest;
import payload.request.UpdateOrderServicePriceRequest;
import service.UserService;

public class OrderRepository {

    public ArrayList<OrderDetailRequest> getAllOrderDetailById(int id) throws Exception {
        ArrayList<OrderDetailRequest> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select od.id, s.name as service, sp.name as supplier, od.price, o.note from OrderDetail od\n"
                    + "left join Orders o\n"
                    + "on o.OID = od.orderHeader_id\n"
                    + "inner join Service s\n"
                    + "on od.service_id = s.service_id\n"
                    + "left join Supplier sp\n"
                    + "on od.supplier_id = sp.SID\n"
                    + "where o.OID = ?";
            pst = cn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
        }
        if (rs != null) {
            while (rs.next()) {
                OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
                orderDetailRequest.setId(rs.getInt(1));
                orderDetailRequest.setService(rs.getString(2));
                orderDetailRequest.setSupplier(rs.getString(3));
                orderDetailRequest.setPrice(rs.getInt(4));
                orderDetailRequest.setNote(rs.getString(5));
                list.add(orderDetailRequest);
            }
        }
        return list;
    }

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

    //  ----------------------------------------
    //
    //  General Order Function (Multi-purpose)
    //
    //  ----------------------------------------
    // This method create a new ORder in Database
    public void addOrder(UserEntity user, CartEntity cart, String note, int coordinatorId) throws SQLException {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        UserService uService = new UserService();
        Connection con = DBConfig.getConnection();
        String sql = "insert into Orders values(?,? ,'Pending' ,? , ?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, date);
        stm.setString(2, cart.getDeliveryTime());
        stm.setInt(3, user.getAID());
        stm.setInt(4, coordinatorId);
        stm.setInt(5, cart.getBlockId());
        stm.setString(6, note);
        stm.executeUpdate();
        String sql1 = "select top 1 oid from Orders order by oid desc";
        PreparedStatement stm1 = con.prepareStatement(sql1);
        ResultSet rs = stm1.executeQuery();
        while (rs.next()) {
            int oid = rs.getInt("oid");
            for (ItemEntity item : cart.getItems()) {
                String sql2 = "insert into OrderDetail values(?, ?, ?, 0, null)";
                PreparedStatement stm2 = con.prepareStatement(sql2);
                stm2.setInt(1, oid);
                stm2.setInt(2, item.getService().getServiceID());
                stm2.setInt(3, item.getService().getCategoryID());
                stm2.executeUpdate();
            }
        }
        con.close();

    }

    // This method Cancel an Order in Database
    public void cancelOrder(int oId) throws SQLException {
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Orders set status = 'Cancel' where OID = ?");
        pstm.setInt(1, oId);
        int count = pstm.executeUpdate();

        con.close();
    }

    // Get full List of Orders
    public List<OrderHeaderEntity> selectAll() throws SQLException {
        List<OrderHeaderEntity> list = null;
        Connection con = DBConfig.getConnection();

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from Orders order by OID desc");
        list = new ArrayList<>();
        while (rs.next()) {
            OrderHeaderEntity oh = new OrderHeaderEntity();
            oh.setId(rs.getInt("OID"));
            oh.setDate(rs.getTimestamp("time"));
            oh.setDelivery_time(rs.getTimestamp("delivery_time"));
            oh.setStatus(rs.getString("status"));
            oh.setResidentId(rs.getInt("RID"));
            oh.setCoordinatorID(rs.getInt("CID"));
            oh.setBlockid(rs.getInt("BID"));
            oh.setNote(rs.getString("note"));
            list.add(oh);
        }
        con.close();
        return list;
    }

    // Get Order Header by OrderHeader_ID
    public OrderHeaderEntity getOne(int id) throws SQLException {
        OrderHeaderEntity order = new OrderHeaderEntity();

        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Orders WHERE OID = ?");
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            order.setId(rs.getInt(1));
            order.setDate(rs.getTimestamp(2));
            order.setDelivery_time(rs.getDate(3));
            order.setStatus(rs.getString(4));
            order.setResidentId(rs.getInt(5));
            order.setCoordinatorID(rs.getInt(6));
            order.setBlockid(rs.getInt(7));
            order.setNote(rs.getString(8));
        }
        con.close();
        return order;
    }

    // Get Order Detail of one OrderHeader by OrderHeader_ID
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
            od.setOrderHeaderId(rs.getInt("orderHeader_id"));
            od.setServiceId(rs.getInt("service_id"));
            od.setCategoryId(rs.getInt("category_id"));
            od.setPrice(rs.getInt("price"));
            list.add(od);
        }
        con.close();
        return list;
    }

    //This Methods get all Status of Orders provide for Update Status Function in Admin
    public ArrayList<String> getStatus() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        ResultSet rs = null;
        if (cn != null) {
            String query = "SELECT DISTINCT status FROM dbo.Orders";
            Statement stm = cn.createStatement();
            rs = stm.executeQuery(query);
        }
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        return list;
    }

    //  ----------------------------------------
    //
    //  Get Order From 1 Resident Function
    //
    //  ----------------------------------------
    //Select Orders of 1 Customer with their ID
    public List<MyOrderEntity> selectMyOrders(int id) throws SQLException {
        List<MyOrderEntity> list = null;
        Connection con = DBConfig.getConnection();

        PreparedStatement stm = con.prepareStatement("select * from Orders where RID = ? order by OID desc");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<>();
        while (rs.next()) {
            OrderHeaderEntity oh = new OrderHeaderEntity();
            oh.setId(rs.getInt("OID"));
            oh.setDate(rs.getTimestamp("time"));
            oh.setDelivery_time(rs.getTimestamp("delivery_time"));
            oh.setResidentId(rs.getInt("RID"));
            oh.setCoordinatorID(rs.getInt("CID"));
            oh.setStatus(rs.getString("status"));
            oh.setNote(rs.getString("note"));

            //Get all OrderDetail of this Order Header
            HashMap<OrderDetailEntity, String> map = this.selectOrderDetailWithID(rs.getInt("OID"));

            // Map OrderHeader with its OrderDetail
            MyOrderEntity fo = new MyOrderEntity(oh, map);
            list.add(fo);
        }
        con.close();
        return list;
    }

    public List<MyOrderRequest> selectMyOrdersRequest(int id) throws SQLException {
        List<MyOrderRequest> list = null;
        Connection con = DBConfig.getConnection();

        PreparedStatement stm = con.prepareStatement("  select o.OID, o.time, o.delivery_time, o.RID, o.CID, o.note, o.status, ac.name from Orders o\n"
                + "  left join Coordinator co \n"
                + "  on o.CID = co.ID\n"
                + "  left join Account ac\n"
                + "  on ac.AID = co.ID\n"
                + "  where RID = ? order by OID desc");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<>();
        while (rs.next()) {
            OrderHeaderEntity oh = new OrderHeaderEntity();
            oh.setId(rs.getInt("OID"));
            oh.setDate(rs.getTimestamp("time"));
            oh.setDelivery_time(rs.getTimestamp("delivery_time"));
            oh.setResidentId(rs.getInt("RID"));
            oh.setCoordinatorID(rs.getInt("CID"));
            oh.setStatus(rs.getString("status"));
            oh.setNote(rs.getString("note"));

            //Get all OrderDetail of this Order Header
            HashMap<OrderDetailEntity, String> map = this.selectOrderDetailWithID(rs.getInt("OID"));
            String coordinator = rs.getString("name");
            // Map OrderHeader with its OrderDetail
            MyOrderRequest fo = new MyOrderRequest(oh, map, coordinator);
            list.add(fo);
        }
        con.close();
        return list;
    }

    // This method get A list of Order and its Order_Detail by Hash Map Function
    public HashMap<OrderDetailEntity, String> selectOrderDetailWithID(int orderHeaderId) throws SQLException {
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
            od.setSupplierId(rs.getInt("supplier_id"));
            String name = rs.getString("name");
            list.put(od, name);
        }
        con.close();
        return list;
    }

    //  ----------------------------------------
    //
    //  Admin Coordinator Update Information for his/her Order about Status or Price for every Service
    //
    //  ----------------------------------------
    // This method for Admin to update Coordinator or Status of Orders
    public void updateStatus(int OID, int CID, String status, String note) throws SQLException {
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Orders set CID = ?, status = ?, note = ? where OID = ?");
        pstm.setInt(1, CID);
        pstm.setString(2, status);
        pstm.setString(3, note);
        pstm.setInt(4, OID);
        int count = pstm.executeUpdate();

        con.close();
    }

    // This method is use to update price for each Service in Order Detail in Admin Pages for Coordinator
    public void updatePrice(int id, double price) throws SQLException {
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update OrderDetail set price = ? where id = ?");
        pstm.setDouble(1, price);
        pstm.setInt(2, id);
        int count = pstm.executeUpdate();
        con.close();
    }

    public List<UpdateOrderServicePriceRequest> selectOrderDetailWithNameService(int OID) throws SQLException {
        List<UpdateOrderServicePriceRequest> list = new ArrayList<>();

        Connection con = DBConfig.getConnection();

        PreparedStatement stm = con.prepareStatement("SELECT o.id, o.service_id, s.name, s.lower_price, s.upper_price, sp.name, o.price FROM dbo.OrderDetail o INNER  JOIN dbo.Service s\n"
                + "ON s.service_id = o.service_id\n"
                + "LEFT JOIN dbo.Supplier sp\n"
                + "ON sp.SID = o.supplier_id\n"
                + "WHERE orderheader_id = ?");
        stm.setInt(1, OID);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            UpdateOrderServicePriceRequest osr = new UpdateOrderServicePriceRequest();
            osr.setId(rs.getInt(1));
            osr.setServiceID(rs.getInt(2));
            osr.setName(rs.getString(3));
            osr.setMinPrice(rs.getInt(4));
            osr.setMaxPrice(rs.getInt(5));
            osr.setSupplier(rs.getString(6));
            osr.setPrice(rs.getInt(7));
            list.add(osr);
        }
        return list;
    }

    //  ----------------------------------------
    //
    //  Admin Dashboard Function
    //
    //  ----------------------------------------
    // This method is load Recent Order part for Admin DashBoard
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

    // This method is load Complete Order part for Admin DashBoard
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

    // This method is load Revenue part for Admin DashBoard
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

    // This method is load Count Acc part for Admin DashBoard
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

    // This method is load Recent Sales part for Admin DashBoard
    public List<SaleEntity> recentSale() throws SQLException {
        List list = null;
        Connection con = DBConfig.getConnection();

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select Orders.OID, Account.name, Service.name, OrderDetail.price, Orders.status \n"
                + "	from Orders left join Account on Orders.RID = Account.AID  \n"
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

    // This method is load Cate Traffic part for Admin DashBoard
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

    // This method is load Topsell part for Admin DashBoard
    public List<SaleEntity> topsell() throws SQLException {
        List<SaleEntity> list = null;
        Connection con = DBConfig.getConnection();

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select top 4 Orders.OID, Service.name, OrderDetail.price ,COUNT(service.service_id) as Sold, Sum(OrderDetail.price) as Revenue\n"
                + "from Orders left join OrderDetail ON Orders.OID = OrderDetail.orderHeader_id \n"
                + "left join Service on Service.service_id = OrderDetail.service_id where Orders.status = 'Completed' \n"
                + "GROUP BY Orders.OID, Service.name, OrderDetail.price Order By Revenue DESC");
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

    //  ----------------------------------------
    //
    //  Admin Revenue Function
    //
    //  ----------------------------------------
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

    // Select Order Header of 1 Coordinator
    public List<AdminOrderListRequest> selectOrdersCoordinator(int id) throws SQLException {
        List<AdminOrderListRequest> list = null;
        Connection con = DBConfig.getConnection();

        PreparedStatement stm = con.prepareStatement("select o.OID, o.RID, a.name, o.CID, c.name, o.time, o.delivery_time, o.status, o.note, r.BID, b.name, r.room \n"
                + "FROM Orders o\n"
                + "LEFT join Account a\n"
                + "ON o.RID = a.AID\n"
                + "LEFT JOIN Account c\n"
                + "ON c.AID = o.CID\n"
                + "LEFT JOIN dbo.Resident r\n"
                + "ON r.ID = o.RID\n"
                + "LEFT JOIN dbo.BlockVin b\n"
                + "ON b.BID = r.BID\n"
                + "WHERE O.CID = ?\n"
                + "ORDER by o.time DESC");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<>();
        while (rs.next()) {
            AdminOrderListRequest entity = new AdminOrderListRequest();
            entity.setId(rs.getInt(1));
            entity.setRID(rs.getInt(2));
            entity.setResidentName(rs.getNString(3));
            entity.setCID(rs.getInt(4));
            entity.setCoordinatorName(rs.getString(5));
            entity.setDate(rs.getTimestamp(6));
            entity.setDelivery_time(rs.getTimestamp(7));
            entity.setStatus(rs.getString(8));
            entity.setNote(rs.getString(9));
            entity.setBID(rs.getInt(10));
            entity.setBlock(rs.getString(11));
            entity.setRoom(rs.getString(12));
            HashMap<OrderDetailEntity, String> map = this.selectOrderDetailWithID(rs.getInt("OID"));

            entity.setOd(map);

            list.add(entity);
        }
        con.close();
        return list;
    }

    //This method Get Order List for Admin Pages (Order List Tab in right nav-bar)
    public ArrayList<AdminOrderListRequest> getAllOrders() throws Exception {
        ArrayList<AdminOrderListRequest> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select o.OID, o.RID, a.name, o.CID, c.name, o.time, o.delivery_time, o.status, o.note, r.BID, b.name, r.room \n"
                    + "FROM Orders o\n"
                    + "LEFT join Account a\n"
                    + "ON o.RID = a.AID\n"
                    + "LEFT JOIN Account c\n"
                    + "ON c.AID = o.CID\n"
                    + "LEFT JOIN dbo.Resident r\n"
                    + "ON r.ID = o.RID\n"
                    + "LEFT JOIN dbo.BlockVin b\n"
                    + "ON b.BID = r.BID\n"
                    + "ORDER by o.OID DESC";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        if (rs != null) {
            while (rs.next()) {
                AdminOrderListRequest entity = new AdminOrderListRequest();
                entity.setId(rs.getInt(1));
                entity.setRID(rs.getInt(2));
                entity.setResidentName(rs.getNString(3));
                entity.setCID(rs.getInt(4));
                entity.setCoordinatorName(rs.getString(5));
                entity.setDate(rs.getTimestamp(6));
                entity.setDelivery_time(rs.getTimestamp(7));
                entity.setStatus(rs.getString(8));
                entity.setNote(rs.getString(9));
                entity.setBID(rs.getInt(10));
                entity.setBlock(rs.getString(11));
                entity.setRoom(rs.getString(12));
                HashMap<OrderDetailEntity, String> map = this.selectOrderDetailWithID(rs.getInt("OID"));

                entity.setOd(map);

                list.add(entity);

            }
        }
        return list;
    }

    //  ----------------------------------------
    //
    //  Admin Dashboard Pending-order Functions
    //
    //  ----------------------------------------
    // This method get all Pending Orders for the Notification Icons in Admin Pages
    public int getPendingOrders() throws SQLException {
        int result = 0;
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Orders where status = 'Pending'";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            result++;
        }
        return result;
    }

    // This method is use to update price for each Service in Order Detail in Admin Pages for Coordinator
    public void updateSupplier(int id, int supplierId) throws SQLException {
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update OrderDetail set supplier_id = ? where id = ?");
        pstm.setDouble(1, supplierId);
        pstm.setInt(2, id);
        int count = pstm.executeUpdate();
        con.close();
    }
    
    public UserEntity getNameFromOrder(int OID) throws SQLException {
        UserEntity user = new UserEntity();

        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("select name, AID, phone, email, password, gender, roleID from Account a join Coordinator c on a.AID = c.ID join Orders o on c.ID = o.CID where o.OID = ?");
        pstm.setInt(1, OID);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            user.setName(rs.getString(1));
            user.setAID(rs.getInt(2));
            user.setPhone(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setPassword(rs.getString(5));
            user.setGender(rs.getString(6));
            user.setRoleID(rs.getInt(7));
        }
        con.close();
        return user;
    }
    
    public UserEntity getResidentNameFromOrder(int OID) throws SQLException {
        UserEntity user = new UserEntity();

        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("select name, AID, phone, email, password, gender, roleID from Account a join Resident r on a.AID = r.ID join Orders o on r.ID = o.RID where o.OID = ?");
        pstm.setInt(1, OID);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            user.setName(rs.getString(1));
            user.setAID(rs.getInt(2));
            user.setPhone(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setPassword(rs.getString(5));
            user.setGender(rs.getString(6));
            user.setRoleID(rs.getInt(7));
        }
        con.close();
        return user;
    }
    
    public void deleteOrder(int oId) throws SQLException {
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Orders set status = 'Failed' where OID = ?");
        pstm.setInt(1, oId);
        int count = pstm.executeUpdate();
        con.close();
    }
    
    public void checkOrder() throws SQLException {
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("select OID from Orders where delivery_time < GETDATE() and status = 'Pending'");
        ResultSet rs = pstm.executeQuery();
        while(rs.next()) {
            deleteOrder(rs.getInt("oid"));
        }
        con.close();
    }

    public static void main(String[] args) throws SQLException, Exception {
        OrderRepository op = new OrderRepository();
        UserEntity us = op.getResidentNameFromOrder(14);
        System.out.println(us);
    }
}
