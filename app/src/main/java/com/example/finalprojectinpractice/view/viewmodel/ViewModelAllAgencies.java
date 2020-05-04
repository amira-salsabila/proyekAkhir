package com.example.finalprojectinpractice.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResponse;
import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResultItem;
import com.example.finalprojectinpractice.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelAllAgencies extends ViewModel {

    private ApiMain apiMain;
    private MutableLiveData<ArrayList<ModelAllAgenciesResultItem>> listAllAgencies = new MutableLiveData<>();

    public void setListAllAgencies() {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApiAllAgencies().getAllAgenciesModel().enqueue(new Callback<ModelAllAgenciesResponse>() {
            @Override
            public void onResponse(Call<ModelAllAgenciesResponse> call, Response<ModelAllAgenciesResponse> response) {
                ModelAllAgenciesResponse responseApiAllAgencies = response.body();
                if (responseApiAllAgencies != null && responseApiAllAgencies.getInstansi() != null) {
                    ArrayList<ModelAllAgenciesResultItem> resultAllAgencies = responseApiAllAgencies.getInstansi();
                    listAllAgencies.postValue(resultAllAgencies);
                }
            }

            @Override
            public void onFailure(Call<ModelAllAgenciesResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<ModelAllAgenciesResultItem>> getListAllAgencies() {
        return listAllAgencies;
    }
}
