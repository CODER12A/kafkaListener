package com.training.kafkaListener;



import java.time.LocalDateTime;

public class OrderUpdateEvent {
    
    private String orderId;
    private String oldStatus;
    private String newStatus;
    private LocalDateTime timestamp;

    // Constructors
    public OrderUpdateEvent() {}

    public OrderUpdateEvent(String orderId, String oldStatus, String newStatus, LocalDateTime timestamp) {
        this.orderId = orderId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OrderUpdateEvent{" +
                "orderId='" + orderId + '\'' +
                ", oldStatus='" + oldStatus + '\'' +
                ", newStatus='" + newStatus + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
