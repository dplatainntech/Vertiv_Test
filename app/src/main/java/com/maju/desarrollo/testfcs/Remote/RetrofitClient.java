package com.maju.desarrollo.testfcs.Remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RetrofitClient {
    private static Retrofit instance;
    //private static String url = "http://vertiv-fcslatam.northcentralus.cloudapp.azure.com/Servicios/";//Latam PROD
    private static String url = "http://fcstest.northcentralus.cloudapp.azure.com/servicios/" ; // LATAM TEST
    //private static String url = "http://inntech-vertiv.northcentralus.cloudapp.azure.com/LatamServicios/" ; // LATAM TEST
    //private static String url = "http://inntech-vertiv.northcentralus.cloudapp.azure.com/Servicios/"; //Inn-tech
    //private static String url = "http://10.203.247.119/Servicios/"; //Vertiv VPN
    //private static String url = "http://vertiv-fcs.northcentralus.cloudapp.azure.com/servicios/"; //Vertiv Prod

    public static Retrofit getInstance(){
        if(instance == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            instance = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return instance;

    }

}
