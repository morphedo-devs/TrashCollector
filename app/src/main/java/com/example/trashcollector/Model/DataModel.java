package com.example.trashcollector.Model;

import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("TransactionId")
    String TransactionId;
    @SerializedName("PaymentStatus")
    String PaymentStatus;
    @SerializedName("Amount")
    String Amount;
    @SerializedName("Quantity")
    String Quantity;
    @SerializedName("Price")
    String Price;
    @SerializedName("DateTime")
    String DateTime;

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }
}
