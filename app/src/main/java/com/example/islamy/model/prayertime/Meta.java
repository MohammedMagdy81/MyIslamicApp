package com.example.islamy.model.prayertime;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("method")
	private Method method;

	@SerializedName("offset")
	private Offset offset;

	@SerializedName("school")
	private String school;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("midnightMode")
	private String midnightMode;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("longitude")
	private double longitude;

	@SerializedName("latitudeAdjustmentMethod")
	private String latitudeAdjustmentMethod;

	public Method getMethod(){
		return method;
	}

	public Offset getOffset(){
		return offset;
	}

	public String getSchool(){
		return school;
	}

	public String getTimezone(){
		return timezone;
	}

	public String getMidnightMode(){
		return midnightMode;
	}

	public double getLatitude(){
		return latitude;
	}

	public double getLongitude(){
		return longitude;
	}

	public String getLatitudeAdjustmentMethod(){
		return latitudeAdjustmentMethod;
	}
}