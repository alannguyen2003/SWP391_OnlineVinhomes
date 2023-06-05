/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Source;
import entity.UserEntity;
import entity.ResidentEntity;

/**
 *
 * @author vsngh
 */
public class UserRepository {

    Connection connect = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String queryResident = "select Account.AID, Resident.name,Account.email, Account.password, "
            + "Account.phone ,Resident.room, Resident.UID, Resident.BID, Account.roleId "
            + "from Account join Resident on Resident.AID = Account.AID where email =? and password =?";

    public UserEntity Login(String email, String password) throws SQLException {
        //select * from tbl User where email = ? and password=?
        String query = """
                       select Account.AID, Account.phone, Account.email, Account.password, Account.name, Account.BID, Account.roleId, Resident.room, Employee.manager_id 
                         from Account left join Resident on Account.AID = Resident.AID left join Employee on Account.AID = Employee.AID where email=? and password=?""";
        try {
            connect = new DBConfig().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new UserEntity(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)
                );
            }
        } catch (SQLException ex) {
            System.err.println("Error at Login");
        }
        return null;
    }

    public UserEntity Check(String email) throws SQLException {
        //select * from tbl User where email = ? and password=?
        String query = """
                       select Account.AID, Account.phone, Account.email, Account.password, Account.name, Account.BID, Account.roleId, Resident.room, Employee.manager_id 
                         from Account left join Resident on Account.AID = Resident.AID left join Employee on Account.AID = Employee.AID where email=? """;
        try {
            connect = new DBConfig().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new UserEntity(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            System.err.println("Error at Check User");
        }
        return null;
    }

    public void resetPass(String email, String password) throws SQLException {
        String query = "update Account set password = ? where email = ?";
        connect = DBConfig.getConnection();
        ps = connect.prepareStatement(query);
        ps.setString(2, email);
        ps.setString(1, password);
        ps.executeUpdate();
        connect.close();

    }
    
    public void changePass(String aid, String password) throws SQLException {
        String query = "update Account set password = ? where aid = ?";
        connect = DBConfig.getConnection();
        ps = connect.prepareStatement(query);
        ps.setString(2, aid);
        ps.setString(1, password);
        ps.executeUpdate();
        connect.close();

    }

    public void createAccount(String phone, String email, String password, String name, int blockId, int roleId) throws SQLException {
        String query = "insert into Account values(?,?,?,?,?,?)";
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, phone);
        stm.setString(2, email);
        stm.setString(3, password);
        stm.setString(4, name);
        stm.setInt(5, blockId);
        stm.setInt(6, roleId);
        stm.executeUpdate();
        con.close();
    }

    public ArrayList<UserEntity> getAllUser() throws Exception {
        ArrayList<UserEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select Account.AID, Account.phone, Account.email, Account.password, Account.name, Account.BID, Account.roleId, Resident.room \n"
                    + "                         from Account left join Resident on Account.AID = Resident.AID";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            UserEntity entity = new UserEntity();
            entity.setAID(rs.getInt(1));
            entity.setPhone(rs.getString(2));
            entity.setEmail(rs.getString(3));
            entity.setPassword(rs.getString(4));
            entity.setName(rs.getString(5));
            entity.setBID(rs.getInt(6));
            entity.setRoleID(rs.getInt(7));
            entity.setRoom(rs.getString(8));
            list.add(entity);
        }
        return list;
    }

    public ArrayList<UserEntity> getAllUserByName(String name) throws Exception {
        ArrayList<UserEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select Account.AID, Account.phone, Account.email, Account.password, Account.name as fullName, Account.BID, Account.roleId, Resident.room \n"
                    + "from Account left join Resident on Account.AID = Resident.AID where Account.name like ? ";
            pst = cn.prepareStatement(query);
            pst.setString(1, "%" + name + "%");
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            UserEntity entity = new UserEntity();
            entity.setAID(rs.getInt(1));
            entity.setPhone(rs.getString(2));
            entity.setEmail(rs.getString(3));
            entity.setPassword(rs.getString(4));
            entity.setName(rs.getString(5));
            entity.setBID(rs.getInt(6));
            entity.setRoleID(rs.getInt(7));
            entity.setRoom(rs.getString(8));
            list.add(entity);
        }
        return list;
    }

    public String getTopUserJsArray() throws SQLException {
        String result = "";
        List<String> list = new ArrayList<>();
        Connection con = DBConfig.getConnection();
        String SQL = """
                     select top 5 a.AID, a.name, SUM(od.min_price) as totalMoney from Orders as o 
                     join 
                     OrderDetail as od on o.OID = od.orderHeaderId 
                     join 
                     Account as a on o.UID = a.AID
                     group by a.AID, a.name
                     order by totalMoney desc""";

        PreparedStatement stm = con.prepareStatement(SQL);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("AID") + " | " + rs.getString("name"));
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                result += "['" + list.get(i) + "', ";
            } else if (i == list.size() - 1) {
                result += "'" + list.get(i) + "']";
            } else {
                result += "'" + list.get(i) + "', ";
            }

        }
        return result;
    }

    public String getTopUserTotalMoneyJsArray() throws SQLException {
        String result = "";
        List<Double> list = new ArrayList<>();
        Connection con = DBConfig.getConnection();
        String SQL = """
                     select top 5 a.AID, a.name, SUM(od.min_price) as totalMoney from Orders as o 
                     join 
                     OrderDetail as od on o.OID = od.orderHeaderId 
                     join 
                     Account as a on o.UID = a.AID
                     group by a.AID, a.name
                     order by totalMoney desc""";
        PreparedStatement stm = con.prepareStatement(SQL);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            list.add(rs.getDouble("totalMoney"));
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                result += "[" + list.get(i) + ",";
            } else if (i == list.size() - 1) {
                result += list.get(i) + "]";
            } else {
                result += list.get(i) + ", ";
            }

        }
        return result;
    }
    
    public int getCountResident() throws Exception {
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select count(*) as count from Resident";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void addNewResident(UserEntity userEntity) throws Exception {
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "insert into Account(phone, email, password, name, BID, roleId)\n"
                    + "values (?, ?, ?, ?, ?, 1)";
            pst = cn.prepareStatement(query);
            pst.setString(1, userEntity.getPhone());
            pst.setString(2, userEntity.getEmail());
            pst.setString(3, userEntity.getPassword());
            pst.setNString(4, userEntity.getName());
            pst.setInt(5, userEntity.getBID());
            pst.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException, Exception {
        UserRepository rep = new UserRepository();
        System.out.println(rep.getAllUser());
    }

}
