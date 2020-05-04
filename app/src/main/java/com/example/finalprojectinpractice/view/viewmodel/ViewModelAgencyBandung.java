package com.example.finalprojectinpractice.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectinpractice.model.modelKotaBandung.ModelAgencyBandungResponse;
import com.example.finalprojectinpractice.model.modelKotaBandung.ModelAgencyBandungResultItem;
import com.example.finalprojectinpractice.service.ApiMain;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelAgencyBandung extends ViewModel {

    private ApiMain apiMain;
    private MutableLiveData<ArrayList<ModelAgencyBandungResultItem>> listAgenciesBandung = new MutableLiveData<>();

    public void setAgenciesBandungModel() {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApiAgenciesBandung().getAgenciesBandungModel().enqueue(new Callback<ModelAgencyBandungResponse>() {
            @Override
            public void onResponse(Call<ModelAgencyBandungResponse> call, Response<ModelAgencyBandungResponse> response) {
                ModelAgencyBandungResponse responseAgencyBandungModel = response.body();
                if (responseAgencyBandungModel != null && responseAgencyBandungModel.getDaftarInstansi() != null) {
                    ArrayList<ModelAgencyBandungResultItem> resultAgencyBandungModel = responseAgencyBandungModel.getDaftarInstansi();
                    listAgenciesBandung.postValue(resultAgencyBandungModel);
                }
            }

            @Override
            public void onFailure(Call<ModelAgencyBandungResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<ModelAgencyBandungResultItem>> getAgenciesBandungModel() {
        return listAgenciesBandung;
    }
}
