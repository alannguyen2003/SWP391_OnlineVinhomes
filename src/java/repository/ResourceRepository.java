/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import config.DBConfig;
import entity.BlockResourceEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ResourceRepository {
    
    public List<BlockResourceEntity> getBlockResourceList(int blockId) throws SQLException {
        List<BlockResourceEntity> list = new ArrayList<>();
        Connection con = DBConfig.getConnection();
        String query = """
                       select br.BID, b.name as blockName, r.RID , r.name as resourceName, br.quantity 
                       from BlockResource as br join BlockVin as b on br.BID = b.BID 
                       join Resource as r on br.RID = r.RID
                       where br.BID = (select a.BID from Account as a join Employee as e on a.AID = e.AID where a.roleId = 3 and a.BID = ?)""";

        PreparedStatement stm = con.prepareStatement(query);
//        stm.setInt(2, ((page - 1) * entries));
//        stm.setInt(3, entries);
        stm.setInt(1, blockId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            BlockResourceEntity br = new BlockResourceEntity();
            br.setbId(rs.getInt("bid"));
            br.setrId(rs.getInt("rid"));
            br.setBlockName(rs.getString("blockName"));
            br.setResourceName(rs.getString("resourceName"));
            br.setQuantity(rs.getInt("quantity"));
            list.add(br);
        }
        con.close();
        return list;
    }
    

    

    public List<BlockResourceEntity> getBlockResourceListByResourceName(String resourceName, int blockId) throws SQLException {
        List<BlockResourceEntity> list = new ArrayList<>();
        Connection con = DBConfig.getConnection();
        String query = """
                       select br.BID, b.name as blockName, r.RID , r.name as resourceName, br.quantity 
                       from BlockResource as br join BlockVin as b on br.BID = b.BID 
                       join Resource as r on br.RID = r.RID
                       where br.BID = (select a.BID from Account as a join Employee as e on a.AID = e.AID where a.roleId = 3 and a.BID = ?) and r.name like ?""";
  
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, blockId);
        stm.setString(2, "%" + resourceName + "%");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            BlockResourceEntity br = new BlockResourceEntity();
            br.setbId(rs.getInt("bid"));
            br.setrId(rs.getInt("rid"));
            br.setBlockName(rs.getString("blockName"));
            br.setResourceName(rs.getString("resourceName"));
            br.setQuantity(rs.getInt("quantity"));
            list.add(br);
        }
        con.close();
        return list;
    }


    public BlockResourceEntity getBlockResourceEntity(int bId, int rId) throws SQLException {
        BlockResourceEntity e = new BlockResourceEntity();
        Connection con = DBConfig.getConnection();
        String query = """
                       select br.BID, b.name as blockName, r.RID , r.name as resourceName, br.quantity 
                       from BlockResource as br join BlockVin as b on br.BID = b.BID 
                       join Resource as r on br.RID = r.RID
                       where r.RID = ? and b.BID = ?""";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, rId);
        stm.setInt(2, bId);
        ResultSet rs = stm.executeQuery();
        if(rs.next()) {
            e.setbId(rs.getInt("bid"));
            e.setrId(rs.getInt("rid"));
            e.setBlockName(rs.getString("blockName"));
            e.setResourceName(rs.getString("resourceName"));
            e.setQuantity(rs.getInt("quantity"));
        }
        
        return e;
    }
    
    public boolean updateResource(BlockResourceEntity br) throws SQLException {
        boolean check = false;
        Connection con = DBConfig.getConnection();
        String query = """
                       update BlockResource 
                       set quantity = ?
                       where BlockResource.BID = ? and BlockResource.RID = ?""";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, br.getQuantity());
        stm.setInt(2, br.getbId());
        stm.setInt(3, br.getrId());
        int count = stm.executeUpdate();
        if (count != 0) {
            check = true;
        }
        con.close();
        return check;
    }

    public static void main(String[] args) throws SQLException {
        BlockResourceEntity br = new BlockResourceEntity(1, "Toa cc", 17, "cc", 20);
        System.out.println(new ResourceRepository().getBlockResourceEntity(7,1).getResourceName());
    }
}
