package com.example.finalprojectinpractice.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMain {

    Retrofit retrofit;

    public AgenciesAllRepository getApiAllAgencies() {
        String BASE_URL = "http://dev.farizdotid.com/api/instansi/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(AgenciesAllRepository.class);
    }

    public AgenciesBandungRepository getApiAgenciesBandung() {
        String BASE_URL = "http://dev.farizdotid.com/api/instansi/daftar_instansi/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(AgenciesBandungRepository.class);
    }

    public AgenciesJakartaBaratRepository getApiAgenciesJakartaBarat() {
        String BASE_URL = "http://dev.farizdotid.com/api/instansi/daftar_instansi/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(AgenciesJakartaBaratRepository.class);
    }

    public AgenciesJakartaSelatanRepository getApiAgenciesJakartaSelatan() {
        String BASE_URL = "http://dev.farizdotid.com/api/instansi/daftar_instansi/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(AgenciesJakartaSelatanRepository.class);
    }

    public  AgenciesJakartaTimurRepository getApiAgenciesJakartaTimur() {
        String BASE_URL = "http://dev.farizdotid.com/api/instansi/daftar_instansi/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(AgenciesJakartaTimurRepository.class);
    }

    public AgenciesJakartaUtaraRepository getApiAgenciesJakartaUtara() {
        String BASE_URL = "http://dev.farizdotid.com/api/instansi/daftar_instansi/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(AgenciesJakartaUtaraRepository.class);
    }

    public AgenciesPurwakartaRepository getApiAgenciesPurwakarta() {
        String BASE_URL = "http://dev.farizdotid.com/api/instansi/daftar_instansi/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(AgenciesPurwakartaRepository.class);
    }
}
