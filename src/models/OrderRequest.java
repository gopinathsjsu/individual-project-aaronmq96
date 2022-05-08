package models;

public class OrderRequest {

    private String orderItem;
    private int orderQuantity;
    private String cardNumber;

    public OrderRequest(String orderItem, int orderQuantity, String cardNumber){
        this.orderItem=orderItem;
        this.orderQuantity=orderQuantity;
        this.cardNumber=cardNumber;

    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
