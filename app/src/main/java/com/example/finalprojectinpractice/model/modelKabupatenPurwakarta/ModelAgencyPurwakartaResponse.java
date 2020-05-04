package com.example.finalprojectinpractice.model.modelKabupatenPurwakarta;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelAgencyPurwakartaResponse{

	@SerializedName("daftar_instansi")
	private ArrayList<ModelAgencyPurwakartaResultItem> daftarInstansi;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setDaftarInstansi(ArrayList<ModelAgencyPurwakartaResultItem> daftarInstansi){
		this.daftarInstansi = daftarInstansi;
	}

	public ArrayList<ModelAgencyPurwakartaResultItem> getDaftarInstansi(){
		return daftarInstansi;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ModelAgencyPurwakartaResponse{" + 
			"daftar_instansi = '" + daftarInstansi + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}