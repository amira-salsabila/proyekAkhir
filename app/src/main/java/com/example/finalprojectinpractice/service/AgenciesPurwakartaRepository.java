package com.example.finalprojectinpractice.service;

import com.example.finalprojectinpractice.model.modelKabupatenPurwakarta.ModelAgencyPurwakartaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AgenciesPurwakartaRepository {

    @GET("Kabupaten%20Purwakarta")
    Call<ModelAgencyPurwakartaResponse> getAgenciesPurwakarta();
}
