package com.example.islamy.model.quranresponse;

import java.util.List;

import com.example.islamy.model.quran.SurahDetails;
import com.google.gson.annotations.SerializedName;

public class SurahDetailsResponse{

	@SerializedName("result")
	private List<SurahDetails> result;

	public List<SurahDetails> getResult(){
		return result;
	}
}