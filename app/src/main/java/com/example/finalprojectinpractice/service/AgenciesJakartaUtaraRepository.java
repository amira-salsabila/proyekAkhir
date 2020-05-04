package com.example.finalprojectinpractice.service;

import com.example.finalprojectinpractice.model.modelJakartaUtara.ModelAgencyJakartaUtaraResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AgenciesJakartaUtaraRepository {

    @GET("Kota%20Jakarta%20Utara")
    Call<ModelAgencyJakartaUtaraResponse> getAgenciesJakartaUtaraModel();
}
