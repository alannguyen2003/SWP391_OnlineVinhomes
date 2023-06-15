/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.BlockResourceEntity;
import entity.ResourceEntity;
import java.sql.SQLException;
import java.util.List;
import repository.ResourceRepository;

/**
 *
 * @author ASUS
 */
public class ResourceService {

    private final ResourceRepository resourceRepo = new ResourceRepository();

    public List<BlockResourceEntity> getResourceBySearched(String searched, int blockId) throws SQLException {
        return resourceRepo.getBlockResourceListByResourceName(searched, blockId);
    }

    public boolean addResource(int blockId, int resourceId, int quantity) throws SQLException {
        return resourceRepo.addResource(blockId, resourceId, quantity);
    }
    
    public boolean updateResource(BlockResourceEntity entity) throws SQLException {
        return resourceRepo.updateResource(entity);
    }

    public List<BlockResourceEntity> getAllResource(int blockId) throws SQLException {
        return resourceRepo.getBlockResourceList(blockId);
    }

    public BlockResourceEntity getBlockResource(int bId, int rId) throws SQLException {
        return resourceRepo.getBlockResourceEntity(bId, rId);
    }

    public List<ResourceEntity> getUnassginedResourcesOfBlock(int blockId) throws SQLException {
        return resourceRepo.getUnassginedResourceOfBlock(blockId);
    }
    
    
    public static void main(String[] args) throws SQLException {
        System.out.println(new ResourceService().getUnassginedResourcesOfBlock(1).size());
    }

    
}
