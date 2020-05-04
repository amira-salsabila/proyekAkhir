package com.example.finalprojectinpractice.model.modelJakartaBarat;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelAgencyJakartaBaratResponse{

	@SerializedName("daftar_instansi")
	private ArrayList<ModelAgencyJakartaBaratResultItem> daftarInstansi;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setDaftarInstansi(ArrayList<ModelAgencyJakartaBaratResultItem> daftarInstansi){
		this.daftarInstansi = daftarInstansi;
	}

	public ArrayList<ModelAgencyJakartaBaratResultItem> getDaftarInstansi(){
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
			"ModelAgencyJakartaBaratResponse{" + 
			"daftar_instansi = '" + daftarInstansi + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}