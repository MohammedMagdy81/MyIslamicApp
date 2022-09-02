package com.example.islamy.model.radio;

import com.google.gson.annotations.SerializedName;

public class RadioItem {
    @SerializedName("name")
    private String name;

    @SerializedName("radio_url")
    private String radioUrl;

    public String getName() {
        return name;
    }

    public String getRadioUrl() {
        return radioUrl;
    }
}
