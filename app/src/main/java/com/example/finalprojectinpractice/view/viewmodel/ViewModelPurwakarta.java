package com.example.finalprojectinpractice.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectinpractice.model.modelKabupatenPurwakarta.ModelAgencyPurwakartaResponse;
import com.example.finalprojectinpractice.model.modelKabupatenPurwakarta.ModelAgencyPurwakartaResultItem;
import com.example.finalprojectinpractice.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelPurwakarta extends ViewModel {

    private ApiMain apiMain;
    private MutableLiveData<ArrayList<ModelAgencyPurwakartaResultItem>> listAgenciesPurwakarta = new MutableLiveData<>();

    public void setAgenciesPurwakartaModel() {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApiAgenciesPurwakarta().getAgenciesPurwakarta().enqueue(new Callback<ModelAgencyPurwakartaResponse>() {
            @Override
            public void onResponse(Call<ModelAgencyPurwakartaResponse> call, Response<ModelAgencyPurwakartaResponse> response) {
                ModelAgencyPurwakartaResponse responseAgencyPurwakartaModel = response.body();
                if (responseAgencyPurwakartaModel != null && responseAgencyPurwakartaModel.getDaftarInstansi() != null) {
                    ArrayList<ModelAgencyPurwakartaResultItem> resultAgencyPurwakartaModel = responseAgencyPurwakartaModel.getDaftarInstansi();
                    listAgenciesPurwakarta.postValue(resultAgencyPurwakartaModel);
                }
            }

            @Override
            public void onFailure(Call<ModelAgencyPurwakartaResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<ModelAgencyPurwakartaResultItem>> getAgenciesPurwakartaModel() {
        return listAgenciesPurwakarta;
    }
}
