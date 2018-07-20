package com.example.android.newssandwich;

import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static final String TAG = "QueryUtils";
    private QueryUtils(){

    }

    public static List<ItemNews> fetchNewsList(String url){

        URL urlObject = createUrl(url);

        String jsonResponse = null;
        try {
            jsonResponse = makeHTTPrequest(urlObject);
        } catch (IOException e) {
            Log.e(TAG, "fetchNewsList: error in closing input stream" );
        }
        List<ItemNews> newsList = extractFeedNewsFromJson(jsonResponse);
        return newsList;

    }

    private static URL createUrl(String url){
        URL urlObject = null;

        try {
            urlObject= new URL(url);
        } catch (MalformedURLException e) {
            Log.d(TAG, "createUrl: ");
        }

        return urlObject;
    }

    private static String makeHTTPrequest(URL url) throws IOException {
        String jsonResponse = "";

        if(url == null){
            return jsonResponse;
        }
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(1000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);

            }else {
                Log.e(TAG, "makeHTTPrequest: error in response code" + httpURLConnection.getResponseCode() );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpURLConnection != null)
                httpURLConnection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }
        return jsonResponse;
    }

    private static String readFromInputStream(InputStream inputStream) {

        StringBuilder stringBuilder = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);
        try {
            String line = bufferedInputStream.readLine();
            while (line != null){
                stringBuilder.append(line);
                line = bufferedInputStream.readLine();
            }
        } catch (IOException e) {
            Log.e(TAG, "readFromInputStream: " );
        }
        return stringBuilder.toString();
    }

    private static List<ItemNews> extractFeedNewsFromJson(String jsonResponse){
        Log.i(TAG, "extractFeedNewsFromJson: json response" + jsonResponse);
        if(TextUtils.isEmpty(jsonResponse))
            return null;
        List<ItemNews> newsList = new ArrayList<>();
        try {
            JSONObject rootJsonObject = new JSONObject(jsonResponse);
            JSONObject response = rootJsonObject.getJSONObject("response");
            JSONArray results= response.getJSONArray("results");
            for (int i = 0,n=results.length(); i < n;i++) {
                JSONObject newsObject = results.getJSONObject(i);
                String title = newsObject.getString("webTitle");
                Log.i(TAG, "extractFeedNewsFromJson: title"+title);
                String url = newsObject.getString("webUrl");
                Log.i(TAG, "extractFeedNewsFromJson: url "+url);
                String section = newsObject.getString("sectionName");
                Log.i(TAG, "extractFeedNewsFromJson: section "+section);
                ItemNews itemNews = new ItemNews(title,section,url);
                newsList.add(itemNews);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

}
