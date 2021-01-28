package com.reliancehmotest.prozoneapp;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RelProviderInterf {

    @POST("providers")
    Call<ReliancePostProvidersModel>  createProvider(@Body ReliancePostProvidersModel post);

    @GET("providers")
    Call<ArrayList<RelianceProZoneAppModelClass>> getProviders();

    @GET("providers")
    Call<ArrayList<RelianceProZoneAppModelClass>> getProvidersByTypeStatus(@Query("provider_type") String provider_type,
                                                               @Query("active_status") String active_status);


    @GET("providers")
    Call<ArrayList<RelianceProZoneAppModelClass>> getProvidersGenericSearch(@QueryMap Map<String , String> parameters);
}