package com.example.finalprojectinpractice.model.modelAllAgencies;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelAllAgenciesResponse{

	@SerializedName("instansi")
	private ArrayList<ModelAllAgenciesResultItem> instansi;

	public void setInstansi(ArrayList<ModelAllAgenciesResultItem> instansi){
		this.instansi = instansi;
	}

	public ArrayList<ModelAllAgenciesResultItem> getInstansi(){
		return instansi;
	}

	@Override
 	public String toString(){
		return 
			"ModelAllAgenciesResponse{" + 
			"instansi = '" + instansi + '\'' + 
			"}";
		}
}