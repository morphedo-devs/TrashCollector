package com.example.trashcollector.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transactiondata {
    @SerializedName("transaction_id")
    @Expose
    private Integer transactionId;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
}
