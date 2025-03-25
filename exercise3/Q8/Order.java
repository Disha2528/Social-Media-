package exercise3.Q3;

import java.time.LocalDate;
import java.util.Date;

public class Order {

    private int orderId;
    private String customerName;
    private int totalAmount;
    private LocalDate orderDate;
    private String Status;

    public Order() {
    }

    public Order(int orderId, String customerName, int totalAmount, LocalDate orderDate, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        Status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString(){
        return "Order Id: "+ orderId+ " Name: "+ customerName+ " Total Amount: "+ totalAmount+" Order Date: "+orderDate
                +" Status: "+  Status;
    }



}
