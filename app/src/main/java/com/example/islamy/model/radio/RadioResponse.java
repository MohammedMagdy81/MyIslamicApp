package com.example.islamy.model.radio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RadioResponse {

    @SerializedName("radios")
    private List<RadioItem> radios;

    public List<RadioItem> getRadios() {
        return radios;
    }
}
