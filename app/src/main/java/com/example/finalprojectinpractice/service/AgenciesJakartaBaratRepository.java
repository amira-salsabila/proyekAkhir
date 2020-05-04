package com.example.finalprojectinpractice.service;

import com.example.finalprojectinpractice.model.modelJakartaBarat.ModelAgencyJakartaBaratResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AgenciesJakartaBaratRepository {

    @GET("Kota%20Jakarta%20Barat")
    Call<ModelAgencyJakartaBaratResponse> getAgenciesJakartaBaratModel();
}
