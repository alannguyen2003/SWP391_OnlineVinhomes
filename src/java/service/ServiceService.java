/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ServiceEntity;
import config.DBConfig;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import payload.request.AdminServiceListRequest;
import repository.ServiceRepository;
/**
 *
 * @author acer
 */
public class ServiceService {
    private ServiceRepository serviceRepository = new ServiceRepository();
    
    
    public ArrayList<AdminServiceListRequest> getServicesListForAdminPage() throws Exception {
        return serviceRepository.getAllServices();
    }
    
    public ArrayList<ServiceEntity> getServiceByCategory(int categoryId) throws Exception {
        return serviceRepository.getServiceByCategory(categoryId);
    }
    
    public ServiceEntity getServiceById(int DID) throws SQLException{
        return serviceRepository.getServiceById(DID);
    }
    
    public ArrayList<ServiceEntity> getServiceByName(String serviceName) throws Exception {
        return serviceRepository.getServiceByName(serviceName);
    }
    
    public ArrayList<ServiceEntity> getServiceByDescription(String serviceDescription) throws Exception {
        return serviceRepository.getServiceByDescription(serviceDescription);
    }
    
    public void updateService(int service_id, String name, String description, double lowerPrice, double upperPrice, double rated, int supplierId, int categoryId) throws SQLException{
        serviceRepository.updateService(service_id, name, description, lowerPrice, upperPrice, rated, supplierId, categoryId);
    }
    
    public void addService(String name, String description, double lowerPrice, double upperPrice, double rated, int supplierID, int categoryID) throws SQLException{
        serviceRepository.addService(name, description, lowerPrice, upperPrice, rated, supplierID, categoryID);
    }
    
    public String checkResource(ServiceEntity service, int blockId) throws SQLException {
        return serviceRepository.checkResource(service, blockId);
    }
    
    public ArrayList<ServiceEntity> searchByName(String search) {
        return (ArrayList<ServiceEntity>) serviceRepository.searchByName(search);
    }
    
    public static void main(String[] args) throws Exception {
        ServiceService serviceService = new ServiceService();
//        for (ServiceEntity entity : serviceService.getServiceByCategory(1)) {
//            System.out.println(entity);
//        }
        
        ServiceEntity service = new ServiceEntity();
        service.setServiceID(1);
        
        System.out.println(serviceService.checkResource(service, 1));
        
    }
}
