package com.example.islamy.model.prayertime;

import com.google.gson.annotations.SerializedName;

public class Method{

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private Location location;

	@SerializedName("id")
	private int id;

	@SerializedName("params")
	private Params params;

	public String getName(){
		return name;
	}

	public Location getLocation(){
		return location;
	}

	public int getId(){
		return id;
	}

	public Params getParams(){
		return params;
	}
}