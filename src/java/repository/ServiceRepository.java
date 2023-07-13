/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConfig;
import entity.ServiceEntity;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

/**
 *
 * @author acer
 */
public class ServiceRepository {

    public ArrayList<ServiceEntity> getAllService() throws Exception {
        ArrayList<ServiceEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Service";
            pst = cn.prepareStatement(query);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            ServiceEntity entity = new ServiceEntity();
            entity.setServiceID(rs.getInt(1));
            entity.setName(rs.getString(2));
            entity.setDescription(rs.getString(3));
            entity.setLowerPrice(rs.getDouble(4));
            entity.setUpperPrice(rs.getDouble(5));
            entity.setRated(rs.getDouble(6));
            entity.setSupplierID(rs.getInt(7));
            entity.setCategoryID(rs.getInt(8));
            list.add(entity);
        }
        return list;
    }

    public ArrayList<ServiceEntity> getServiceByName(String serviceName) throws Exception {
        ArrayList<ServiceEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Service  \n"
                    + "where  Service.name like ? ";
            pst = cn.prepareStatement(query);
            pst.setString(1, "%" + serviceName + "%");
            rs = pst.executeQuery();
        }

        while (rs.next()) {
            ServiceEntity serviceEntity = new ServiceEntity();
            serviceEntity.setServiceID(rs.getInt(1));
            serviceEntity.setName(rs.getString(2));
            serviceEntity.setDescription(rs.getString(3));
            serviceEntity.setLowerPrice(rs.getDouble(4));
            serviceEntity.setUpperPrice(rs.getDouble(5));
            serviceEntity.setRated(rs.getDouble(6));
            serviceEntity.setSupplierID(rs.getInt(7));
            serviceEntity.setCategoryID(rs.getInt(8));

            list.add(serviceEntity);

        }
        return list;
    }

    public ArrayList<ServiceEntity> getServiceByCategory(int categoryId) throws Exception {
        ArrayList<ServiceEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Service \n"
                    + "where category_id = ?";
            pst = cn.prepareStatement(query);
            pst.setInt(1, categoryId);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            ServiceEntity entity = new ServiceEntity();
            entity.setServiceID(rs.getInt(1));
            entity.setName(rs.getString(2));
            entity.setDescription(rs.getString(3));
            entity.setLowerPrice(rs.getDouble(4));
            entity.setUpperPrice(rs.getDouble(5));
            entity.setRated(rs.getDouble(6));
            entity.setSupplierID(rs.getInt(7));
            entity.setCategoryID(rs.getInt(8));
            list.add(entity);
        }
        return list;
    }

    public ArrayList<ServiceEntity> getServiceByDescription(String serviceDescription) throws Exception {
        ArrayList<ServiceEntity> list = new ArrayList<>();
        Connection cn = (Connection) DBConfig.getConnection();
        PreparedStatement pst;
        ResultSet rs = null;
        if (cn != null) {
            String query = "select * from Service \n"
                    + "where Service.description LIKE ?";
            pst = cn.prepareStatement(query);
            pst.setString(1, "%" + serviceDescription + "%");
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            ServiceEntity serviceEntity = new ServiceEntity();
            serviceEntity.setServiceID(rs.getInt(1));
            serviceEntity.setName(rs.getString(2));
            serviceEntity.setDescription(rs.getString(3));
            serviceEntity.setLowerPrice(rs.getDouble(4));
            serviceEntity.setUpperPrice(rs.getDouble(5));
            serviceEntity.setRated(rs.getDouble(6));
            serviceEntity.setSupplierID(rs.getInt(7));
            serviceEntity.setCategoryID(rs.getInt(8));
            list.add(serviceEntity);
        }
        return list;
    }

    public ServiceEntity getServiceById(int id) throws SQLException {
        String query = "select * from service where service_id = ?";
        ServiceEntity service = new ServiceEntity();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBConfig.getConnection();
            pre = con.prepareStatement(query);
            // code go la phai nam duoi pre = con.pre
            pre.setInt(1, id);
            rs = pre.executeQuery();
            while (rs.next()) {
                // output tu database
                service.setServiceID(rs.getInt("service_id"));
                service.setName(rs.getString("name"));
                service.setDescription(rs.getString("description"));
                service.setLowerPrice(rs.getDouble("lower_price"));
                service.setUpperPrice(rs.getDouble("upper_price"));
                service.setRated(rs.getDouble("rated"));
                service.setSupplierID(rs.getInt("supplier_id"));
                service.setCategoryID(rs.getInt("category_id"));
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (rs != null) {
                rs.close();
            }

        }
        return service;
    }

    public void updateService(int service_id, String name, String description, double lowerPrice, double upperPrice, double rated, int supplierId, int categoryId) throws SQLException {
        Connection con = DBConfig.getConnection();
        PreparedStatement pstm = con.prepareStatement("update Service set name = ?, description = ?, lower_price = ?, upper_price = ?"
                + ", rated = ?, supplier_id = ?, category_id = ? where service_id = ?");
        pstm.setString(1, name);
        pstm.setString(2, description);
        pstm.setDouble(3, lowerPrice);
        pstm.setDouble(4, upperPrice);
        pstm.setDouble(5, rated);
        pstm.setInt(6, supplierId);
        pstm.setInt(7, categoryId);
        pstm.setInt(8, service_id);
        int count = pstm.executeUpdate();

        con.close();
    }

    public void addService(String name, String description, double lowerPrice, double upperPrice, double rated, int supplierID, int categoryID) throws SQLException {
        Connection con = DBConfig.getConnection();
        String query = "insert into Service values(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, name);
        stm.setString(2, description);
        stm.setDouble(3, lowerPrice);
        stm.setDouble(4, upperPrice);
        stm.setDouble(5, rated);
        stm.setInt(6, supplierID);
        stm.setInt(7, categoryID);
        stm.executeUpdate();
        con.close();
    }

    public String checkResource(ServiceEntity service, int blockId) throws SQLException {
        String needed = "";
        String query = """
                       select srn.RID, r.name,srn.quantity - br.quantity as needed
                       from ServiceResourceNeeded as srn 
                       left join 
                       BlockResource as br 
                       on srn.RID = br.RID and br.BID = ?
                       left join
                       Resource as r
                       on br.RID = r.RID
                       where srn.SID = ?""";
        Connection con = DBConfig.getConnection();
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, blockId);
        stm.setInt(2, service.getServiceID());
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            if (rs.getInt("needed") > 0) {
                needed += rs.getString("name") + ":" + " " + rs.getInt("needed") + "\n";
            }
        }
        return needed;
    }

    public List<ServiceEntity> searchByName(String txtSearch) {
        List<ServiceEntity> list = new ArrayList<>();
        String query = "select * from Service\n"
                + "where [name] like ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBConfig().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ServiceEntity service = new ServiceEntity();
                service.setServiceID(rs.getInt("service_id"));
                service.setName(rs.getString("name"));
                service.setDescription(rs.getString("description"));
                service.setLowerPrice(rs.getDouble("lower_price"));
                service.setUpperPrice(rs.getDouble("upper_price"));
                service.setRated(rs.getDouble("rated"));
                service.setSupplierID(rs.getInt("supplier_id"));
                service.setCategoryID(rs.getInt("category_id"));
                list.add(service);
            }
            conn.close();
            
        } catch (Exception e) {
        }
        
        return list;
    }

    public static void main(String[] args) throws Exception {
        ServiceRepository repository = new ServiceRepository();
        repository.updateService(1, "Carpet Cleaning", "We clean carpets using steam and eco-friendly products.", 50, 100, 4.5, 1, 1);
    }

}
