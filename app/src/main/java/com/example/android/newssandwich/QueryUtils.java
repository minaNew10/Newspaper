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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
         String jsonResponse = null;
//
//        if(url == null){
//            return jsonResponse;
//        }
//        HttpURLConnection httpURLConnection = null;
//        InputStream inputStream = null;
//        try {
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setReadTimeout(1000);
//            httpURLConnection.setConnectTimeout(15000);
//            httpURLConnection.connect();
//            if(httpURLConnection.getResponseCode() == 200){
//                inputStream = httpURLConnection.getInputStream();
//                jsonResponse = readFromInputStream(inputStream);
//
//            }else {
//                Log.e(TAG, "makeHTTPrequest: error in response code" + httpURLConnection.getResponseCode() );
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(httpURLConnection != null)
//                httpURLConnection.disconnect();
//            if(inputStream != null)
//                inputStream.close();
//        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            Log.i(TAG, "makeHTTPrequest: code " + response.code());
            if(response.code() == 200){
                jsonResponse = response.body().string();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(response != null)
               response.close();
        }

        Log.i(TAG, "makeHTTPrequest: 0 " + jsonResponse);
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
                String title = null,
                        url= null,
                        section= null,
                        date= null,
                        author= null;
                JSONObject fields;
                if(newsObject.has("webTitle"))
                     title = newsObject.getString("webTitle");
                if(newsObject.has("webUrl"))
                     url = newsObject.getString("webUrl");
                if(newsObject.has("sectionName"))
                     section = newsObject.getString("sectionName");
                if(newsObject.has("webPublicationDate"))
                     date = newsObject.getString("webPublicationDate");
                if(newsObject.has("fields")) {
                    fields = newsObject.getJSONObject("fields");
                    if (fields.has("byline"))
                        author = fields.getString("byline");
                }

                ItemNews itemNews = new ItemNews(title,section,url,author,date);
                newsList.add(itemNews);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

}
