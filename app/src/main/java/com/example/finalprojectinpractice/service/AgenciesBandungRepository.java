package com.example.finalprojectinpractice.service;

import com.example.finalprojectinpractice.model.modelKotaBandung.ModelAgencyBandungResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AgenciesBandungRepository {

    @GET("Kota%20Bandung")
    Call<ModelAgencyBandungResponse> getAgenciesBandungModel();

}
