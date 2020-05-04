package com.example.finalprojectinpractice.service;

import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AgenciesAllRepository {

    @GET("semuainstansi")
    Call<ModelAllAgenciesResponse> getAllAgenciesModel();
}
