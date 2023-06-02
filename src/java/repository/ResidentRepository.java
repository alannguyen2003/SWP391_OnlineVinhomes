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
    
}
