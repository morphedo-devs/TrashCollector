package com.example.trashcollector.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apimanager {

    private static Retrofit instance ;

    private static Retrofit getInstance()
    {
        if(instance ==null)
        {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            httpClient.readTimeout(500, TimeUnit.SECONDS);
            httpClient.connectTimeout(500, TimeUnit.SECONDS);
            httpClient.retryOnConnectionFailure(true);

            instance = new Retrofit.Builder()
                    .baseUrl("https://maxpe.in/api/index.php/TrashVendingMachine/Trash/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            return  instance;

        }
        else return  instance;
    }

    public  static  Retrointerface getApiservice()
    {
       return getInstance().create(Retrointerface.class);
    }



}
