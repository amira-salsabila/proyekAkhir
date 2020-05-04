package com.example.finalprojectinpractice.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectinpractice.model.modelJakartaSelatan.ModelAgencyJakartaSelatanResponse;
import com.example.finalprojectinpractice.model.modelJakartaSelatan.ModelAgencyJakartaSelatanResultItem;
import com.example.finalprojectinpractice.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelAgencyJakartaSelatan extends ViewModel {

    private ApiMain apiMain;
    private MutableLiveData<ArrayList<ModelAgencyJakartaSelatanResultItem>> listAgenciesJakartaSelatan = new MutableLiveData<>();

    public void setAgenciesJakartaSelatanModel() {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApiAgenciesJakartaSelatan().getAgenciesJakartaSelatanModel().enqueue(new Callback<ModelAgencyJakartaSelatanResponse>() {
            @Override
            public void onResponse(Call<ModelAgencyJakartaSelatanResponse> call, Response<ModelAgencyJakartaSelatanResponse> response) {
                ModelAgencyJakartaSelatanResponse responseAgencyJakartaSelatanModel = response.body();
                if (responseAgencyJakartaSelatanModel != null && responseAgencyJakartaSelatanModel.getDaftarInstansi() != null) {
                    ArrayList<ModelAgencyJakartaSelatanResultItem> resultAgencyJakartaSelatanModel = responseAgencyJakartaSelatanModel.getDaftarInstansi();
                    listAgenciesJakartaSelatan.postValue(resultAgencyJakartaSelatanModel);
                }
            }

            @Override
            public void onFailure(Call<ModelAgencyJakartaSelatanResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<ModelAgencyJakartaSelatanResultItem>> getAgenciesJakartaSelatanModel() {
        return listAgenciesJakartaSelatan;
    }
}
