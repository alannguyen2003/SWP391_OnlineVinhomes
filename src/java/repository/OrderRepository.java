package repository;

import config.DBConfig;
import entity.MyOrderEntity;
import entity.OrderDetailEntity;
import entity.OrderHeaderEntity;
import entity.RevenueEntity;
import entity.SaleEntity;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

    public List<MyOrderEntity> selectMyOrders(int id) throws SQLException {
        List<MyOrderEntity> list = null;
        Connection con = DBConfig.getConnection();

        PreparedStatement stm = con.prepareStatement("select * from Orders where AID = ? order by OID desc");
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
                + "where o.orderHeader_Id = ? and o.service_Id = s.service_id");
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

    public List<OrderHeaderEntity> recentOrder() throws SQLException {
        List<OrderHeaderEntity> list = null;
        Connection con = DBConfig.getConnection();

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select top 5 * from Orders where status = 'Pending' Order by time desc");
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
        for(Double d : l) {
            if(d != 0.0) {
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
        String query = "select SUM(od.min_price) as totalMoney from Orders as o join OrderDetail as od on o.OID = od.orderHeader_Id where o.OID = ? and o.status = 'Completed'";
        int result = 0;
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, oId);
        ResultSet rs = stm.executeQuery();
        if(rs.next()) {
            result += rs.getInt("totalMoney");
        }
        
        return result;
    }


    public static void main(String[] args) throws SQLException {
        OrderRepository op = new OrderRepository();
        int rev = op.Revenue();
        System.out.println(rev);
    }
}
