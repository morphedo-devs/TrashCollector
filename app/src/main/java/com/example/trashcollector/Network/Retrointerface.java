package com.example.trashcollector.Network;



import com.example.trashcollector.Model.BaseResponse;
import com.example.trashcollector.Model.Transactionmain;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Retrointerface {

    @FormUrlEncoded
    @POST("trashpayment")
    Call<Transactionmain>initPayment(@Field("skey") String key,
                                     @Field("MobileNumber") String MobileNumber,
                                     @Field("TrashType") String TrashType);

    @FormUrlEncoded
    @POST("getprice")
    Call<BaseResponse>getPrice(@Field("Key") String key);

    @FormUrlEncoded
    @POST("verifymob")
    Call<BaseResponse>verfiymobile(@Field("skey") String key, @Field("MobileNumber") String MobileNumber);

    @FormUrlEncoded
    @POST("updateprice")
    Call<BaseResponse>updatePrice(@Field("Key") String key, @Field("price_qty") String price_qty, @Field("date-timestamp") String timestamp);
}
