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

    
    public List<ResourceEntity> getResourceList() throws SQLException {
        return resourceRepo.getResourceList();
    }
    
    public List<BlockResourceEntity> getResourceBySearched(String searched, int blockId) throws SQLException {
        return resourceRepo.getBlockResourceListByResourceName(searched, blockId);
    }

    public boolean addResource(String name) throws SQLException {
        return resourceRepo.addResource(name);
    }
    
    public boolean addBlockResource(int blockId, int resourceId, int quantity) throws SQLException {
        return resourceRepo.addBlockResource(blockId, resourceId, quantity);
    }
    
    public boolean updateResource(int id, String name) throws SQLException {
        return resourceRepo.updateResource(id, name);
    }
    
    public boolean updateBlockResource(BlockResourceEntity entity) throws SQLException {
        return resourceRepo.updateBlockResource(entity);
    }

    public List<BlockResourceEntity> getAllBlockResource(int blockId) throws SQLException {
        return resourceRepo.getBlockResourceList(blockId);
    }

    public ResourceEntity getResource(int id) throws SQLException {
        return resourceRepo.getResourceEntity(id);
    }
    
    public BlockResourceEntity getBlockResource(int bId, int rId) throws SQLException {
        return resourceRepo.getBlockResourceEntity(bId, rId);
    }

    public List<ResourceEntity> getUnassginedResourcesOfBlock(int blockId) throws SQLException {
        return resourceRepo.getUnassginedResourceOfBlock(blockId);
    }
    
    
    public static void main(String[] args) throws SQLException {
        System.out.println(new ResourceService().getUnassginedResourcesOfBlock(1).size());
        System.out.println(new ResourceService().getResourceList().size());
        new ResourceService().updateResource(1, "Air Conditioning");
    }

    
}
