package com.example.finalprojectinpractice.service;

import com.example.finalprojectinpractice.model.modelJakartaSelatan.ModelAgencyJakartaSelatanResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AgenciesJakartaSelatanRepository {

    @GET("Kota%20Jakarta%20Selatan")
    Call<ModelAgencyJakartaSelatanResponse> getAgenciesJakartaSelatanModel();
}
