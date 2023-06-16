package entity;

import java.util.HashMap;

public class MyOrderEntity {

    private OrderHeaderEntity oh;
    private HashMap<OrderDetailEntity, String> od;

    public MyOrderEntity() {
    }

    public MyOrderEntity(OrderHeaderEntity oh, HashMap<OrderDetailEntity, String> od) {
        this.oh = oh;
        this.od = od;
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

    public double getTotal() {
        int total = 0;

        for (OrderDetailEntity od : this.od.keySet()) {
            total += od.getPrice();
        }
        return total;
    }

}
