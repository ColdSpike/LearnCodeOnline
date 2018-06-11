package com.pawar.makrand.learncodeonline.retrofit.factory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceFactory {
    private static Retrofit retrofit;
    private static String BASE_URL_DS = "https://learncodeonline.in/";

    public static Retrofit getInstanceforDS(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_DS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
