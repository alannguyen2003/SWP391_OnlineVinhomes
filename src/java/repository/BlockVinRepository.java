/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.BlockVinEntity;
import java.util.ArrayList;
import java.sql.*;

public class BlockVinRepository {

    // Get the entire BlockVin
    public ArrayList<BlockVinEntity> getAllBlock() throws SQLException {
        ArrayList<BlockVinEntity> list = new ArrayList<>();
        Connection cn = DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from BlockVin";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            BlockVinEntity blockVinEntity = new BlockVinEntity();
            blockVinEntity.setBID(rs.getInt(1));
            blockVinEntity.setName(rs.getString(2));
            list.add(blockVinEntity);
        }
        return list;
    }

    //Get Block Id
    public int getBlockId(String blockName) throws SQLException {
        int result = 0;
        BlockVinEntity entity = new BlockVinEntity();
        Connection con = DBConfig.getConnection();
        String query = "select * from BlockVin where name = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, blockName);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            result = rs.getInt("BID");
        }
        return result;
    }

    // Get BlockVin by its Block ID
    public BlockVinEntity getBlockVin(int blockId) throws SQLException {
        BlockVinEntity entity = new BlockVinEntity();
        Connection con = DBConfig.getConnection();
        String query = "select * from BlockVin where bid = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, blockId);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            entity.setBID(rs.getInt("BID"));
            entity.setName(rs.getString("name"));
        }
        return entity;
    }

    public static void main(String[] args) throws SQLException {
        BlockVinRepository repo = new BlockVinRepository();
        System.out.println(repo.getAllBlock());
    }
}
