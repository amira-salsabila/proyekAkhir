package com.example.finalprojectinpractice.model.modelJakartaTimur;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelAgencyJakartaTimurResponse{

	@SerializedName("daftar_instansi")
	private ArrayList<ModelAgencyJakartaTimurResultItem> daftarInstansi;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setDaftarInstansi(ArrayList<ModelAgencyJakartaTimurResultItem> daftarInstansi){
		this.daftarInstansi = daftarInstansi;
	}

	public ArrayList<ModelAgencyJakartaTimurResultItem> getDaftarInstansi(){
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
			"ModelAgencyJakartaTimurResponse{" + 
			"daftar_instansi = '" + daftarInstansi + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}