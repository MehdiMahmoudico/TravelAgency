package com.example.demo.model;



public class PaymentRequest {

    private double amount;
    private String currency;
    private String receiverWalletId;
    private String description;
    private int lifespan;  // The lifespan of the payment request, in minutes or seconds depending on API

    // Default constructor
    public PaymentRequest() {}

    // Constructor with all fields
    public PaymentRequest(double amount, String currency, String receiverWalletId, String description, int lifespan) {
        this.amount = amount;
        this.currency = currency;
        this.receiverWalletId = receiverWalletId;
        this.description = description;
        this.lifespan = lifespan;
    }

    // Getters and Setters

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReceiverWalletId() {
        return receiverWalletId;
    }

    public void setReceiverWalletId(String receiverWalletId) {
        this.receiverWalletId = receiverWalletId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    // toString method to represent the PaymentRequest as a string (optional)
    @Override
    public String toString() {
        return "PaymentRequest{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", receiverWalletId='" + receiverWalletId + '\'' +
                ", description='" + description + '\'' +
                ", lifespan=" + lifespan +
                '}';
    }
}
