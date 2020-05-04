package com.example.finalprojectinpractice.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectinpractice.model.modelJakartaUtara.ModelAgencyJakartaUtaraResponse;
import com.example.finalprojectinpractice.model.modelJakartaUtara.ModelAgencyJakartaUtaraResultItem;
import com.example.finalprojectinpractice.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelJakartaUtara extends ViewModel {

    private ApiMain apiMain;
    private MutableLiveData<ArrayList<ModelAgencyJakartaUtaraResultItem>> listAgenciesJakartaUtara = new MutableLiveData<>();

    public void setAgenciesJakartaUtaraModel() {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApiAgenciesJakartaUtara().getAgenciesJakartaUtaraModel().enqueue(new Callback<ModelAgencyJakartaUtaraResponse>() {
            @Override
            public void onResponse(Call<ModelAgencyJakartaUtaraResponse> call, Response<ModelAgencyJakartaUtaraResponse> response) {
                ModelAgencyJakartaUtaraResponse responseAgencyJakartaUtaraModel = response.body();
                if (responseAgencyJakartaUtaraModel != null && responseAgencyJakartaUtaraModel.getDaftarInstansi() != null) {
                    ArrayList<ModelAgencyJakartaUtaraResultItem> resultAgencyJakartaUtaraModel = responseAgencyJakartaUtaraModel.getDaftarInstansi();
                    listAgenciesJakartaUtara.postValue(resultAgencyJakartaUtaraModel);
                }
            }

            @Override
            public void onFailure(Call<ModelAgencyJakartaUtaraResponse> call, Throwable t) {

            }
        });
    }


    public LiveData<ArrayList<ModelAgencyJakartaUtaraResultItem>> getAgenciesJakartaUtaraModel() {
        return listAgenciesJakartaUtara;

    }
}
