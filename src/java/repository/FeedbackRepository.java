/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.FeedbackEntity;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class FeedbackRepository {
    
    public List<FeedbackEntity> getFeedbackOfService(int id) throws SQLException{
        List<FeedbackEntity> result = new ArrayList<>();
        String query = "select * from Feedback where DID = ?";
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            FeedbackEntity fb = new FeedbackEntity();
            fb.setFID(rs.getInt("fid"));
            fb.setDID(rs.getInt("did"));
            fb.setUID(rs.getInt("uid"));
            fb.setMessage(rs.getString("message"));
            fb.setName(rs.getString("name"));
            fb.setContactNumber(rs.getString("contactNumber"));
            fb.setEmail(rs.getString("email"));
            result.add(fb);
        }
        con.close();
        return result;
    }
    
    public void addFeedbackEntity(FeedbackEntity c) throws SQLException {
        String query = "insert into Feedback values(?, ?, ?, ?, ?, ?)";
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, c.getUID());
        stm.setInt(2, c.getDID());
        stm.setString(3, c.getMessage());
        stm.setString(4, c.getEmail());
        stm.setString(5, c.getName());
        stm.setString(6, c.getContactNumber());
        stm.executeUpdate();
        con.close();
    }
    
    public void deleteFeedbackEntity(int id) throws SQLException {
        String query = "delete from Feedback where fid = ?";
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.executeUpdate();
        con.close();
    }
    
    public int getTotalFeedbackEntitys() throws SQLException {
        int result = 0;
        Connection con = DBConfig.getConnection();
        String query = "select count(fid) as totalFB from Feedback";
        PreparedStatement stm1 = con.prepareStatement(query);
        ResultSet rs = stm1.executeQuery();
        if(rs.next()) {
            result += rs.getInt("totalFB");
        }
        return result;
    }
}
