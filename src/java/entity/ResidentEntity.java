/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class ResidentEntity {
    
    private int blockId;
    private String room;    
    private int AID;

    public ResidentEntity() {
    }

    public ResidentEntity(int blockId, String room, int AID) {
        this.blockId = blockId;
        this.room = room;
        this.AID = AID;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    
    
    
    
    
}
