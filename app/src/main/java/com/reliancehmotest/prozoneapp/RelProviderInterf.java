package com.reliancehmotest.prozoneapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RelProviderInterf {

    @POST("providers")
    Call<ReliancePostProvidersModel>  createPost(@Body ReliancePostProvidersModel post);

    @GET("providers")
    Call<ArrayList<RelianceProZoneAppModelClass>> getProviders();

}
