/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload.request;

import entity.OrderDetailEntity;
import entity.OrderHeaderEntity;
import java.util.HashMap;

/**
 *
 * @author acer
 */
public class MyOrderRequest {
    private OrderHeaderEntity oh;
    private HashMap<OrderDetailEntity, String> od;
    private String coordinator;

    public MyOrderRequest() {
    }

    public MyOrderRequest(OrderHeaderEntity oh, HashMap<OrderDetailEntity, String> od, String coordinator) {
        this.oh = oh;
        this.od = od;
        this.coordinator = coordinator;
    }

    public OrderHeaderEntity getOh() {
        return oh;
    }

    public void setOh(OrderHeaderEntity oh) {
        this.oh = oh;
    }

    public HashMap<OrderDetailEntity, String> getod() {
        return od;
    }

    public void setod(HashMap<OrderDetailEntity, String> od) {
        this.od = od;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public double getTotal() {
        int total = 0;

        for (OrderDetailEntity od : this.od.keySet()) {
            total += od.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "MyOrderRequest{" + "oh=" + oh + ", od=" + od + ", coordinator=" + coordinator + '}';
    }
    
    
}
