package com.vann.sample_pos_sale;

public class OrderModel {
    String Id;
    String OrderStatus;
    String Late;

    int LateM;
    boolean isStart;

    public OrderModel(String id, String orderStatus, String late, int lateM, boolean isStart) {
        Id = id;
        OrderStatus = orderStatus;
        Late = late;
        LateM = lateM;
        this.isStart = isStart;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getLate() {
        return Late;
    }

    public void setLate(String late) {
        Late = late;
    }

    public int getLateM() {
        return LateM;
    }

    public void setLateM(int lateM) {
        LateM = lateM;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }
}
