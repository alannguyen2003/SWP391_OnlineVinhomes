/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.*;
import java.sql.Connection;
import java.sql.SQLException;
import config.DBConfig;
import java.sql.*;
import java.util.ArrayList;

public class ResidentRepository {

    public ResidentEntity read(String aid) throws SQLException {
        ResidentEntity resident = new ResidentEntity();

        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("select * from resident where  AID = ?");
        pstm.setString(1, aid);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            resident.setAID(rs.getInt("AID"));
            resident.setRoom(rs.getString("room"));
        }
        con.close();
        return resident;
    }

    public ArrayList<UserEntity> getAllResident() throws Exception {
        ArrayList<UserEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * \n" +
"                         from Account where Account.roleId=1";
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
        public ArrayList<UserEntity> getAllResidentByName(String residentName) throws Exception {
        ArrayList<UserEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * \n" +
                            "from Account where Account.roleId = 1 AND Account.name like ? ";
            pst = cn.prepareStatement(query);
            pst.setString(1, "%" + residentName + "%");
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
        
    public UserEntity getOne(int aid) throws SQLException {
        UserEntity user = new UserEntity();

        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("select * from account a where a.AID = ?");
        pstm.setInt(1, aid);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            user.setAID(rs.getInt("AID"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setGender(rs.getString("gender"));
            user.setBID(rs.getInt("BID"));
            user.setRoleID(rs.getInt("roleID"));
            user.setRoom(rs.getString("room"));
            user.setStatus(rs.getInt("status"));
        }
        con.close();
        return user;
    } 
        
    public void updateRoom(String room, int AID) throws SQLException{
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Account set room = ? where AID = ?");
        pstm.setString(1, room);
        pstm.setInt(2, AID);
        int count = pstm.executeUpdate();

        con.close();
    }
    
    public void updateResident(String room, int BID, int status, int AID) throws SQLException{
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Account set room = ?, BID = ?, status = ? where AID = ?");
        pstm.setString(1, room);
        pstm.setInt(2, BID);
        pstm.setInt(3, status);
        pstm.setInt(4, AID);
        int count = pstm.executeUpdate();

        con.close();
    }

    public static void main(String[] args) throws Exception {
        ResidentRepository repo = new ResidentRepository();
        repo.updateResident("GP09B3198", 4, 1, 13);
    }

}
