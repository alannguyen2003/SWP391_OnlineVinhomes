/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utils.Hasher;
import config.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
                       select *
                         from Account where email=? and password=?""";
        try {
            connect = new DBConfig().getConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, Hasher.doHashing(password, getUserSalt(email)));
            rs = ps.executeQuery();
            while (rs.next()) {
                return new UserEntity(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10)
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
                       select *
                         from Account where email=? """;
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
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10));
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
        createSaltForUser(Integer.parseInt(aid));
        String query = "update Account set password = ? where aid = ?";
        connect = DBConfig.getConnection();
        ps = connect.prepareStatement(query);
        ps.setString(2, aid);
        ps.setString(1, Hasher.doHashing(password, getUserSalt(getUser(Integer.parseInt(aid)).getEmail())));
        ps.executeUpdate();
        connect.close();

    }
//      CREATE ACCOUNT MANAGER FOR ADMIN - AI LAM PHAN NAY THI SUA 
//    public void createAccount(String phone, String email, String password, String name, int blockId, int roleId) throws SQLException {
//        String query = "insert into Account values(?,?,?,?,?,?)";
//        Connection con = DBConfig.getConnection();
//        PreparedStatement stm = con.prepareStatement(query);
//        stm.setString(1, phone);
//        stm.setString(2, email);
//        stm.setString(3, password);
//        stm.setString(4, name);
//        stm.setInt(5, blockId);
//        stm.setInt(6, roleId);
//        stm.executeUpdate();
//        con.close();
//    }
    
    public ArrayList<UserEntity> getEmployee() throws Exception {
        ArrayList<UserEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Account where roleId = 2";
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
            entity.setGender(rs.getString(6));
            entity.setBID(rs.getInt(7));
            entity.setRoleID(rs.getInt(8));
            entity.setRoom(rs.getString(9));
            entity.setStatus(rs.getInt(10));
            list.add(entity);
        }
        return list;
    }

    public ArrayList<UserEntity> getAllUser() throws Exception {
        ArrayList<UserEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Account";
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
            entity.setGender(rs.getString(6));
            entity.setBID(rs.getInt(7));
            entity.setRoleID(rs.getInt(8));
            entity.setRoom(rs.getString(9));
            entity.setStatus(rs.getInt(10));
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
            String query = "select * \n"
                    + "from Account where Account.name like ? ";
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
            entity.setGender(rs.getString(6));
            entity.setBID(rs.getInt(7));
            entity.setRoleID(rs.getInt(8));
            entity.setRoom(rs.getString(9));
            entity.setStatus(rs.getInt(10));
            list.add(entity);
        }
        return list;
    }
    
    public ArrayList<String> getStatus() throws SQLException{
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

    public String getTopUserJsArray() throws SQLException {
        String result = "";
        List<String> list = new ArrayList<>();
        Connection con = DBConfig.getConnection();
        String SQL = """
                     select top 5 a.AID, a.name, SUM(od.price) as totalMoney from Orders as o 
                     join 
                     OrderDetail as od on o.OID = od.orderHeader_Id 
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
                     select top 5 a.AID, a.name, SUM(od.price) as totalMoney from Orders as o 
                     join 
                     OrderDetail as od on o.OID = od.orderHeader_Id 
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
            String salt = Hasher.createSalt();
            String saltedHashPassword = Hasher.doHashing(userEntity.getPassword(), salt);
            String query = "insert into Account(phone, email, password, name, BID, roleId, salt)\n"
                    + "values (?, ?, ?, ?, ?, 1, ?)";
            pst = cn.prepareStatement(query);
            pst.setString(1, userEntity.getPhone());
            pst.setString(2, userEntity.getEmail());
            pst.setString(3, saltedHashPassword);
            pst.setNString(4, userEntity.getName());
            pst.setInt(5, userEntity.getBID());
            pst.setString(6, salt);
            pst.executeUpdate();
        }
    }

    public UserEntity getUser(int aid) throws SQLException {
        UserEntity user = new UserEntity();

        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("select * from account where  AID = ?");
        pstm.setInt(1, aid);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            user.setAID(rs.getInt(1));
            user.setPhone(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setName(rs.getString(5));
            user.setGender(rs.getString(6));
            user.setBID(rs.getInt(7));
            user.setRoleID(rs.getInt(8));
            user.setRoom(rs.getString(9));
            user.setStatus(rs.getInt(10));
        }
        con.close();
        return user;
    }
    
    public UserEntity getManagerOfBlock(int blockId) throws SQLException {
        UserEntity user = new UserEntity();
        String query = "select * from Account where roleId = 4 and BID = ?";
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, blockId);
        ResultSet rs = stm.executeQuery();
        if(rs.next()) {
            user.setAID(rs.getInt("aid"));
        }
        return user;
    }
    
    public void updateProfile(String username, String gender, int bid, String phone, int aid) throws SQLException{
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Account set name = ?, gender = ?, BID = ?, phone = ? where AID = ?");
        pstm.setString(1, username);
        pstm.setString(2, gender);
        pstm.setInt(3, bid);
        pstm.setString(4, phone);
        pstm.setInt(5, aid);
        int count = pstm.executeUpdate();

        con.close();
    }
    
    public String getUserSalt(String email) throws SQLException {
        String salt = "";
        Connection con = DBConfig.getConnection();
        String query = "select salt from Account where email = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        if(rs.next()) {
            salt += rs.getString("salt");
        }
        con.close();
        return salt.trim();
    }
    
    public void createSaltForUser(int aId) throws SQLException {
        Connection con = DBConfig.getConnection();
        String query = "update Account set salt = ? where aid = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, Hasher.createSalt());
        stm.setInt(2, aId);
        int count = stm.executeUpdate();
        con.close();
    }
    //Code from here is to update database
    public void createSaltForDatabase(int aId) throws SQLException {
        Connection con = DBConfig.getConnection();
        String query = "update Account set salt = ? where aid = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, Hasher.createSalt());
        stm.setInt(2, aId);
        int count = stm.executeUpdate();
        con.close();
    }
    
    
    public String getUserSaltForDatabase(int aid) throws SQLException {
        String salt = "";
        Connection con = DBConfig.getConnection();
        String query = "select salt from Account where aid = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, aid);
        ResultSet rs = stm.executeQuery();
        if(rs.next()) {
            salt += rs.getString("salt");
        }
        con.close();
        return salt.trim();
    }
    
    
    public void generateSaltedHashPasswordForDatabase(int id) throws SQLException {
        Connection con = DBConfig.getConnection();
        String query = "update Account set password = ? where aid = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, Hasher.doHashing("123456", getUserSaltForDatabase(id)));
        stm.setInt(2, id);
        stm.executeUpdate();
        con.close();
    }
    
    //Chạy hàm main này để update database
    public static void main(String[] args) throws SQLException, Exception {
        UserRepository rep = new UserRepository();

        for(int i = 1; i <= 58; i++) {
            rep.createSaltForDatabase(i);
            rep.generateSaltedHashPasswordForDatabase(i);
        }
    }

}
