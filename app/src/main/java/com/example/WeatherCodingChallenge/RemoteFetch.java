package com.example.WeatherCodingChallenge;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.weather.weatherapp.BuildConfig;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class RemoteFetch {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    static JSONObject getJSON(String city) {
        try {
            URL url = new URL(String.format(BuildConfig.OPEN_WEATHER_MAP_API, city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.addRequestProperty("x-api-key",
                    BuildConfig.OPEN_WEATHER_KEY);

            StringBuilder json;
            JSONObject data;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

                json = new StringBuilder(1024);
                String tmp;

                while ((tmp = reader.readLine()) != null)
                    json.append(tmp).append("\n");
                reader.close();
            }

            data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200) {
                return null;
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }
}