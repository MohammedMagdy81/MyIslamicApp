package com.example.islamy.model.prayertime;

import com.google.gson.annotations.SerializedName;

public class Params{

	@SerializedName("Isha")
	private double isha;

	@SerializedName("Fajr")
	private double fajr;

	public double getIsha(){
		return isha;
	}

	public double getFajr(){
		return fajr;
	}
}