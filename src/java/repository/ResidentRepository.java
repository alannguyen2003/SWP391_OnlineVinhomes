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
            String query = "select Account.AID, Account.phone, Account.email, Account.password, Account.name, Account.BID, Account.roleId, Resident.room \n" +
"                         from Account left join Resident on Account.AID = Resident.AID where Account.roleId=1";
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
        public ArrayList<UserEntity> getAllResidentByName(String residentName) throws Exception {
        ArrayList<UserEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select Account.AID, Account.phone, Account.email, Account.password, Account.name as fullName, Account.BID, Account.roleId, Resident.room \n" +
                            "from Account left join Resident on Account.AID = Resident.AID where Account.roleId = 1 AND Account.name like ? ";
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
            entity.setBID(rs.getInt(6));
            entity.setRoleID(rs.getInt(7));
            entity.setRoom(rs.getString(8));
            list.add(entity);
        }
        return list;
    }
        
    public void updateRoom(String room, int AID) throws SQLException{
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Resident set room = ? where AID = ?");
        pstm.setString(1, room);
        pstm.setInt(2, AID);
        int count = pstm.executeUpdate();

        con.close();
    }

    public static void main(String[] args) throws Exception {
        ResidentRepository repo = new ResidentRepository();
        repo.updateRoom("", 0);
    }

}
