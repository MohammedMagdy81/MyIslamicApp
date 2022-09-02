package com.example.islamy.model.quran;

import com.google.gson.annotations.SerializedName;

public class SurahDetails {

	@SerializedName("sura")
	private int sura;

	@SerializedName("aya")
	private int aya;

	@SerializedName("translation")
	private String translation;

	@SerializedName("id")
	private int id;

	@SerializedName("arabic_text")
	private String arabicText;

	@SerializedName("footnotes")
	private String footnotes;

	public int getSura() {
		return sura;
	}

	public int getAya() {
		return aya;
	}

	public String getTranslation() {
		return translation;
	}

	public int getId() {
		return id;
	}

	public String getArabicText() {
		return arabicText;
	}

	public String getFootnotes() {
		return footnotes;
	}

	public SurahDetails(int sura, int aya, String translation, int id, String arabicText, String footnotes) {
		this.sura = sura;
		this.aya = aya;
		this.translation = translation;
		this.id = id;
		this.arabicText = arabicText;
		this.footnotes = footnotes;
	}
}