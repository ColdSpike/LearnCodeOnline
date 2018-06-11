package com.pawar.makrand.learncodeonline.retrofit.services;

import com.pawar.makrand.learncodeonline.retrofit.utils.DataStructureResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataStructuresService {
    @GET("/api/android/datastructure.json")
    Call<DataStructureResponse> getDSData();
}
