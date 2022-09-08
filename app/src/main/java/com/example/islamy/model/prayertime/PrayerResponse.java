package com.example.islamy.model.prayertime;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PrayerResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private String status;

	public int getCode(){
		return code;
	}

	public List<DataItem> getData(){
		return data;
	}

	public String getStatus(){
		return status;
	}
}