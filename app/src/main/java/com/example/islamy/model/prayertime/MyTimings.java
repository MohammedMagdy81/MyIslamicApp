package com.example.islamy.model.prayertime;

public class MyTimings {

    private String prayerTime, prayerName,prayerArabicN;

    public MyTimings(String prayerTime, String prayerName,String prayerArabicN) {
        this.prayerTime = prayerTime;
        this.prayerName = prayerName;
        this.prayerArabicN=prayerArabicN;
    }

    public String getPrayerArabicN() {
        return prayerArabicN;
    }

    public String getPrayerTime() {
        return prayerTime;
    }

    public String getPrayerName() {
        return prayerName;
    }
}
