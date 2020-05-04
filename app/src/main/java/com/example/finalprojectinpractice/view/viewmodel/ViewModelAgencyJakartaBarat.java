package com.example.finalprojectinpractice.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectinpractice.model.modelJakartaBarat.ModelAgencyJakartaBaratResponse;
import com.example.finalprojectinpractice.model.modelJakartaBarat.ModelAgencyJakartaBaratResultItem;
import com.example.finalprojectinpractice.service.ApiMain;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelAgencyJakartaBarat extends ViewModel {

    private ApiMain apiMain;
    private MutableLiveData<ArrayList<ModelAgencyJakartaBaratResultItem>> listAgenciesJakartaBarat = new MutableLiveData<>();

    public void setAgenciesJakartaBaratModel() {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApiAgenciesJakartaBarat().getAgenciesJakartaBaratModel().enqueue(new Callback<ModelAgencyJakartaBaratResponse>() {
            @Override
            public void onResponse(Call<ModelAgencyJakartaBaratResponse> call, Response<ModelAgencyJakartaBaratResponse> response) {
                ModelAgencyJakartaBaratResponse responseAgencyJakartaBaratModel = response.body();
                if (responseAgencyJakartaBaratModel != null && responseAgencyJakartaBaratModel.getDaftarInstansi() != null) {
                    ArrayList<ModelAgencyJakartaBaratResultItem> resultAgencyJakartaBaratModel = responseAgencyJakartaBaratModel.getDaftarInstansi();
                    listAgenciesJakartaBarat.postValue(resultAgencyJakartaBaratModel);
                }
            }

            @Override
            public void onFailure(Call<ModelAgencyJakartaBaratResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<ModelAgencyJakartaBaratResultItem>> getAgenciesModelJakartaBarat() {
        return listAgenciesJakartaBarat;
    }
}
