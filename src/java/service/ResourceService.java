/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.BlockResourceEntity;
import java.sql.SQLException;
import java.util.List;
import repository.ResourceRepository;

/**
 *
 * @author ASUS
 */
public class ResourceService {
    private final ResourceRepository resourceRepo = new ResourceRepository();
//    public List<BlockResourceEntity> getAll(int page, int entries, int blockId) throws SQLException {
//        return resourceRepo.getPaginatedBlockResourceList(page, entries, blockId);
//    }
    public List<BlockResourceEntity> getResourceBySearched( String searched, int blockId) throws SQLException {
        return resourceRepo.getBlockResourceListByResourceName( searched, blockId);
    }
//    public List<BlockResourceEntity> getResourceByBlockName(int page, int entries, String searched, boolean isRunOutOfResource) throws SQLException {
//        return resourceRepo.getPaginatedBlockResourceListBySearchedBlockName(page, entries, searched, isRunOutOfResource);
//    }
    public boolean updateResource(BlockResourceEntity entity) throws SQLException {
        return resourceRepo.updateResource(entity);
    }
    public List<BlockResourceEntity> getAllResource(int blockId) throws SQLException {
        return resourceRepo.getBlockResourceList(blockId);
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(new ResourceService().getAllResource(6).size());
    }
}
