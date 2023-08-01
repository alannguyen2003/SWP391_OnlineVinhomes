/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ServiceEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import payload.request.AdminServiceListRequest;
import repository.ServiceRepository;

/**
 *
 * @author admin
 */
public class CartService {
    private ServiceRepository serviceRepository = new ServiceRepository();
    
    public ServiceEntity getServiceById(int id) throws SQLException{
        return serviceRepository.getServiceById(id);
    }
    
    public ArrayList<AdminServiceListRequest> getAllService() throws Exception {
        return serviceRepository.getAllServices();
    }
    
    public ArrayList<ServiceEntity> getServiceByCategory(int categoryId) throws Exception {
        return serviceRepository.getServiceByCategory(categoryId);
    }
}
