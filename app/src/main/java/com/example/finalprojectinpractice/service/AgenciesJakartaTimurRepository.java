package com.example.finalprojectinpractice.service;

import com.example.finalprojectinpractice.model.modelJakartaTimur.ModelAgencyJakartaTimurResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AgenciesJakartaTimurRepository {

    @GET("Kota%20Jakarta%20Timur")
    Call<ModelAgencyJakartaTimurResponse> getAgenciesJakartaTimurModel();
}
