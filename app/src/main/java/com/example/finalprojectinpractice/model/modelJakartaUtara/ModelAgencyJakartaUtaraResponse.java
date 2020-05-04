package com.example.finalprojectinpractice.model.modelJakartaUtara;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelAgencyJakartaUtaraResponse{

	@SerializedName("daftar_instansi")
	private ArrayList<ModelAgencyJakartaUtaraResultItem> daftarInstansi;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setDaftarInstansi(ArrayList<ModelAgencyJakartaUtaraResultItem> daftarInstansi){
		this.daftarInstansi = daftarInstansi;
	}

	public ArrayList<ModelAgencyJakartaUtaraResultItem> getDaftarInstansi(){
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
			"ModelAgencyJakartaUtaraResponse{" + 
			"daftar_instansi = '" + daftarInstansi + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}