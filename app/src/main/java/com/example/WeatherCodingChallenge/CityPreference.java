package com.example.WeatherCodingChallenge;

import android.app.Activity;
import android.content.SharedPreferences;

class CityPreference {
    private SharedPreferences prefs;

    CityPreference(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    String getCity() {
        //default city setting
        return prefs.getString("city", "Decatur, US");
    }

    void setCity(String city) {
        prefs.edit().putString("city", city).apply();
    }
}