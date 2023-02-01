package maarsupilla.controller;

import com.google.gson.Gson;
import maarsupilla.model.dto.Shrine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIController {

    public static Shrine setupShrine(String urlString) throws IOException {
        URL url = new URL(urlString);
        String content = getStringifiedJson(url);
        Shrine shrine = new Gson().fromJson(content, Shrine.class);
        return shrine;
    }

    private static String getStringifiedJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = br.readLine();
        connection.disconnect();
        return line;
    }
}
