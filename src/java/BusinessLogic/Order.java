package BusinessLogic;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Order implements Serializable {
    private int orderId;
    private int customerId;
    private int price;
    private Date date;

    public Order(int orderId, int customerId, int price, Date date) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
        this.date = date;
    }

    //region get&set
    public Date getDate() {
        return date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    //endregion
}
