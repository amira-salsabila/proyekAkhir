package com.example.finalprojectinpractice.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectinpractice.model.modelJakartaSelatan.ModelAgencyJakartaSelatanResponse;
import com.example.finalprojectinpractice.model.modelJakartaTimur.ModelAgencyJakartaTimurResponse;
import com.example.finalprojectinpractice.model.modelJakartaTimur.ModelAgencyJakartaTimurResultItem;
import com.example.finalprojectinpractice.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelAgencyJakartaTimur extends ViewModel {

    private ApiMain apiMain;
    private MutableLiveData<ArrayList<ModelAgencyJakartaTimurResultItem>> listAgenciesJakartaTimur = new MutableLiveData<>();

    public void setAgenciesJakartaTimurModel() {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApiAgenciesJakartaTimur().getAgenciesJakartaTimurModel().enqueue(new Callback<ModelAgencyJakartaTimurResponse>() {
            @Override
            public void onResponse(Call<ModelAgencyJakartaTimurResponse> call, Response<ModelAgencyJakartaTimurResponse> response) {
                ModelAgencyJakartaTimurResponse responseAgencyModelJakartaTimur = response.body();
                if (responseAgencyModelJakartaTimur != null && responseAgencyModelJakartaTimur.getDaftarInstansi() != null) {
                    ArrayList<ModelAgencyJakartaTimurResultItem> resultAgencyJakartaTimurModel = responseAgencyModelJakartaTimur.getDaftarInstansi();
                    listAgenciesJakartaTimur.postValue(resultAgencyJakartaTimurModel);
                }

            }

            @Override
            public void onFailure(Call<ModelAgencyJakartaTimurResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<ModelAgencyJakartaTimurResultItem>> getAgenciesJakartaTimurModel() {
        return listAgenciesJakartaTimur;
    };
}
