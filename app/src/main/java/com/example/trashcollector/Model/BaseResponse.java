package com.example.trashcollector.Model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

@SerializedName("status")
    int status;
@SerializedName("message")
    String message;
@SerializedName("data")
    DataModel data;

public int getStatus() {
        return status;
        }

public void setStatus(int status) {
        this.status = status;
        }

public String getMessage() {
        return message;
        }

public void setMessage(String message) {
        this.message = message;
        }

public DataModel getData() {
        return data;
        }

public void setData(DataModel data) {
        this.data = data;
        }
        }
