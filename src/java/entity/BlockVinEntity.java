/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author acer
 */
public class BlockVinEntity {
    private int bid;
    private String name;

    public BlockVinEntity() {
    }

    public BlockVinEntity(int bid, String name) {
        this.bid = bid;
        this.name = name;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlockVinEntity{" + "bid=" + bid + ", name=" + name + '}';
    }
    
    
}
