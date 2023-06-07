/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class BlockVinEntity {
    private int BID;
    private String name;

    public BlockVinEntity() {
    }

    public BlockVinEntity(int BID, String name) {
        this.BID = BID;
        this.name = name;
    }

    public int getBID() {
        return BID;
    }

    public void setBID(int BID) {
        this.BID = BID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlockVinEntity{" + "BID=" + BID + ", name=" + name + '}';
    }
}
