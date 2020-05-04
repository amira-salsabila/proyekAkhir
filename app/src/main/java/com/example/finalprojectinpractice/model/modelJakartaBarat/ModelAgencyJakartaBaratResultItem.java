package com.example.finalprojectinpractice.model.modelJakartaBarat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ModelAgencyJakartaBaratResultItem implements Parcelable {

	@SerializedName("nomor_instansi")
	private String nomorInstansi;

	@SerializedName("lat_")
	private String lat;

	@SerializedName("long_")
	private String long_;

	@SerializedName("nama_kabupaten")
	private String namaKabupaten;

	@SerializedName("jenis_instansi")
	private String jenisInstansi;

	@SerializedName("alamat_instansi")
	private String alamatInstansi;

	@SerializedName("id")
	private String id;

	@SerializedName("nama_instansi")
	private String namaInstansi;

	@SerializedName("id_kabupaten")
	private String idKabupaten;

	public void setNomorInstansi(String nomorInstansi){
		this.nomorInstansi = nomorInstansi;
	}

	public String getNomorInstansi(){
		return nomorInstansi;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	public void setLong(String long_){
		this.long_ = long_;
	}

	public String getLong(){
		return long_;
	}

	public void setNamaKabupaten(String namaKabupaten){
		this.namaKabupaten = namaKabupaten;
	}

	public String getNamaKabupaten(){
		return namaKabupaten;
	}

	public void setJenisInstansi(String jenisInstansi){
		this.jenisInstansi = jenisInstansi;
	}

	public String getJenisInstansi(){
		return jenisInstansi;
	}

	public void setAlamatInstansi(String alamatInstansi){
		this.alamatInstansi = alamatInstansi;
	}

	public String getAlamatInstansi(){
		return alamatInstansi;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNamaInstansi(String namaInstansi){
		this.namaInstansi = namaInstansi;
	}

	public String getNamaInstansi(){
		return namaInstansi;
	}

	public void setIdKabupaten(String idKabupaten){
		this.idKabupaten = idKabupaten;
	}

	public String getIdKabupaten(){
		return idKabupaten;
	}

	@Override
 	public String toString(){
		return 
			"ModelAgencyJakartaBaratResultItem{" +
			"nomor_instansi = '" + nomorInstansi + '\'' + 
			",lat_ = '" + lat + '\'' + 
			",long_ = '" + long_ + '\'' +
			",nama_kabupaten = '" + namaKabupaten + '\'' + 
			",jenis_instansi = '" + jenisInstansi + '\'' + 
			",alamat_instansi = '" + alamatInstansi + '\'' + 
			",id = '" + id + '\'' + 
			",nama_instansi = '" + namaInstansi + '\'' + 
			",id_kabupaten = '" + idKabupaten + '\'' + 
			"}";
		}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.nomorInstansi);
		dest.writeString(this.lat);
		dest.writeString(this.long_);
		dest.writeString(this.namaKabupaten);
		dest.writeString(this.jenisInstansi);
		dest.writeString(this.alamatInstansi);
		dest.writeString(this.id);
		dest.writeString(this.namaInstansi);
		dest.writeString(this.idKabupaten);
	}

	public ModelAgencyJakartaBaratResultItem() {
	}

	protected ModelAgencyJakartaBaratResultItem(Parcel in) {
		this.nomorInstansi = in.readString();
		this.lat = in.readString();
		this.long_ = in.readString();
		this.namaKabupaten = in.readString();
		this.jenisInstansi = in.readString();
		this.alamatInstansi = in.readString();
		this.id = in.readString();
		this.namaInstansi = in.readString();
		this.idKabupaten = in.readString();
	}

	public static final Parcelable.Creator<ModelAgencyJakartaBaratResultItem> CREATOR = new Parcelable.Creator<ModelAgencyJakartaBaratResultItem>() {
		@Override
		public ModelAgencyJakartaBaratResultItem createFromParcel(Parcel source) {
			return new ModelAgencyJakartaBaratResultItem(source);
		}

		@Override
		public ModelAgencyJakartaBaratResultItem[] newArray(int size) {
			return new ModelAgencyJakartaBaratResultItem[size];
		}
	};
}