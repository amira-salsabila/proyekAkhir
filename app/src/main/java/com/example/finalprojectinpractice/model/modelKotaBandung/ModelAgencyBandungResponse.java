package com.example.finalprojectinpractice.model.modelKotaBandung;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class ModelAgencyBandungResponse{

	@SerializedName("daftar_instansi")
	private ArrayList<ModelAgencyBandungResultItem> daftarInstansi;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setDaftarInstansi(ArrayList<ModelAgencyBandungResultItem> daftarInstansi){
		this.daftarInstansi = daftarInstansi;
	}

	public ArrayList<ModelAgencyBandungResultItem> getDaftarInstansi(){
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
			"ModelAgencyBandungResponse{" + 
			"daftar_instansi = '" + daftarInstansi + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}