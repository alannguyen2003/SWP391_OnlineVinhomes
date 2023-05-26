/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CartEntity {
    private List<ItemEntity> items;
    
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
    
    public double getTotalMoney() {
        double t= 0;
        for(ItemEntity item : items) {
            t += item.getPrice();
        }
        return t;
    }
    /*
    
    
    
    */
}
