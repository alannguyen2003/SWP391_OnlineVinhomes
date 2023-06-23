/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import config.DBConfig;
import entity.BlockResourceEntity;
import entity.ResourceEntity;
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
    
    public List<ResourceEntity> getResourceList() throws SQLException {
        List<ResourceEntity> list = new ArrayList<>();
        Connection con = DBConfig.getConnection();
        String query = "select * from Resource";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rs = stm.executeQuery();
        while(rs.next()) {
            ResourceEntity resource = new ResourceEntity();
            resource.setId(rs.getInt("rid"));
            resource.setName(rs.getString("name"));
            list.add(resource);
        }
        return list;
    }
    
    
    public List<BlockResourceEntity> getBlockResourceList(int blockId) throws SQLException {
        List<BlockResourceEntity> list = new ArrayList<>();
        Connection con = DBConfig.getConnection();
        String query = """
                       select br.BID, b.name as blockName, r.RID , r.name as resourceName, br.quantity 
                       from BlockResource as br join BlockVin as b on br.BID = b.BID 
                       join Resource as r on br.RID = r.RID
                       where br.BID = ?""";

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
                       where br.BID = ? and r.name like ?""";
  
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

    public ResourceEntity getResourceEntity(int id) throws SQLException {
        ResourceEntity entity = new ResourceEntity();
        Connection con = DBConfig.getConnection();
        String query = """
                       select * from Resource where rid = ?""";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if(rs.next()) {
            entity.setId(rs.getInt("rid"));
            entity.setName(rs.getString("name"));
        }
        return entity;
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
        con.close();
        return e;
    }
    
    public boolean addResource(String name) throws SQLException {
         boolean check = false;
        Connection con = DBConfig.getConnection();
        String query = "insert into Resource values(?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, name);
        int count = stm.executeUpdate();
        if(count != 0) {
            check = true;
        }
        con.close();
        return check;
    }
    
    public boolean addBlockResource(int blockId, int resourceId, int quantity) throws SQLException {
        boolean check = false;
        Connection con = DBConfig.getConnection();
        String query = "insert into BlockResource values(?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, blockId);
        stm.setInt(2, resourceId);
        stm.setInt(3, quantity);
        int count = stm.executeUpdate();
        if(count != 0) {
            check = true;
        }
        con.close();
        return check;
    }
    
    public boolean updateResource(int rid, String name) throws SQLException {
        boolean check = false;
        Connection con = DBConfig.getConnection();
        String query = """
                       update Resource 
                       set name = ?
                       where Resource.rid = ?""";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, name);
        stm.setInt(2, rid);
        int count = stm.executeUpdate();
        if (count != 0) {
            check = true;
        }
        con.close();
        return check;
    }
    
    public boolean updateBlockResource(BlockResourceEntity br) throws SQLException {
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
    
    public List<ResourceEntity> getUnassginedResourceOfBlock(int blockId) throws SQLException {
        List<ResourceEntity> list = new ArrayList<>();
        Connection con = DBConfig.getConnection();
        String query = """
                       select r.RID, r.name from Resource as r join (select RID from Resource
                                                                     except
                                                                     select RID from BlockResource
                                                                     where BID = ?) as unassgined on r.RID = unassgined.RID""";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, blockId);
        ResultSet rs = stm.executeQuery();
        while(rs.next()) {
            int id = rs.getInt("rid");
            String name = rs.getString("name");
            ResourceEntity entity = new ResourceEntity(id, name);
            list.add(entity);
        }
        con.close();
        return list;
    }

    public static void main(String[] args) throws SQLException {
        BlockResourceEntity br = new BlockResourceEntity(1, "Toa cc", 17, "cc", 20);
        System.out.println(new ResourceRepository().getBlockResourceEntity(7,1).getResourceName());
        ResourceRepository r = new ResourceRepository();
        r.updateResource(1, "Air Conditioninggggg");
    }
}
