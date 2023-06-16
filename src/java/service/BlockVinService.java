/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.BlockVinEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.BlockVinRepository;

/**
 *
 * @author admin
 */
public class BlockVinService {
    
    BlockVinRepository bp = new BlockVinRepository();
    
    public ArrayList<BlockVinEntity> getAllBlock() throws SQLException {
        return bp.getAllBlock();
    }
    public BlockVinEntity getBlock(int blockId ) throws SQLException {
        return bp.getBlockVin(blockId);
    }
}
