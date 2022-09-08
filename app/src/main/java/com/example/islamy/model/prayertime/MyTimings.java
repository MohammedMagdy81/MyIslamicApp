package com.example.islamy.model.prayertime;

public class MyTimings {

    private String prayerTime, prayerName;

    public MyTimings(String prayerTime, String prayerName) {
        this.prayerTime = prayerTime;
        this.prayerName = prayerName;
    }

    public String getPrayerTime() {
        return prayerTime;
    }

    public String getPrayerName() {
        return prayerName;
    }
}
