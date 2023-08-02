/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class CartEntity {
    private List<ItemEntity> items;
    private String deliveryTime;
    private int blockId;

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
    
    
    
    public CartEntity(){
        this.items = new ArrayList<>();
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
    
    public ItemEntity getItemById(int id){
        for(ItemEntity item : items){
            if(item.getService().getServiceID() == id){
                return item;
            }
        }
        return null;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    
    public void addItem(ItemEntity item){
        if(getItemById(item.getService().getServiceID()) != null){
            ItemEntity m = getItemById(item.getService().getServiceID());
        } else {
            items.add(item);
        }
    }
    
    public void removeItem(int id) {
        if(getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }
    
    public double getMinTotal() {
        int minTotal = 0;

        for (ItemEntity od : items) {
            minTotal += od.getLowerPrice();
        }
        return minTotal;
    }

    public double getMaxTotal() {
        int maxTotal = 0;

        for (ItemEntity od : items) {
            maxTotal += od.getUpperPrice();
        }

        return maxTotal;
    }
    
    public double getTotalMoney() {
        double maxTotal = getMaxTotal();
        double minTotal = getMinTotal();
        return (( maxTotal + minTotal ) / 2);
    }

    @Override
    public String toString() {
        return "CartEntity{" + "items=" + items + '}';
    }
    
    
}
