package com.example.islamy.model.prayertime;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("longitude")
	private double longitude;

	public double getLatitude(){
		return latitude;
	}

	public double getLongitude(){
		return longitude;
	}
}