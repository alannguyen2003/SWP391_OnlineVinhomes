/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ServiceEntity;
import config.DBConfig;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.ServiceRepository;
/**
 *
 * @author acer
 */
public class ServiceService {
    private ServiceRepository serviceRepository = new ServiceRepository();
    
    public ArrayList<ServiceEntity> getAllService() throws Exception {
        return serviceRepository.getAllService();
    }
    
    public ArrayList<ServiceEntity> getServiceByCategory(int categoryId) throws Exception {
        return serviceRepository.getServiceByCategory(categoryId);
    }
    
    public ServiceEntity getServiceById(int DID) throws SQLException{
        return serviceRepository.getServiceById(DID);
    }
    
    public static void main(String[] args) throws Exception {
        ServiceService serviceService = new ServiceService();
        for (ServiceEntity entity : serviceService.getServiceByCategory(1)) {
            System.out.println(entity);
        }
    }
}