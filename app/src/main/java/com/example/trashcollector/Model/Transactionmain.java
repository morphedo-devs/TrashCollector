package com.example.trashcollector.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transactionmain {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Transactiondata data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Transactiondata getData() {
        return data;
    }

    public void setData(Transactiondata data) {
        this.data = data;
    }
}
