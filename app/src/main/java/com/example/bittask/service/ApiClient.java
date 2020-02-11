package com.example.bittask.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.bittask.utils.AppConstants.BASE_URL;

/**
 * Created by Hyper Design Hussien on 2/24/2018.
 */

public class ApiClient {

    public static final String BASE_URL_API = BASE_URL;
    private static Retrofit retrofit = null;


    private static ApiClient instance;
    private ServiceApiInterface gitHubService;

    private ApiClient() {
        final Gson gson =
                new GsonBuilder ().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL_API)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        gitHubService = retrofit.create(ServiceApiInterface.class);
    }

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


}
