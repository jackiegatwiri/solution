package com.jacky.innovex;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Service {

    public static void postData(Callback callback, String output)
    {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://stormy-meadow-99540.herokuapp.com/closingbalance/new").newBuilder();
        String url = urlBuilder.build().toString();
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(MEDIA_TYPE, output);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void getData(Callback callback)
    {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://stormy-meadow-99540.herokuapp.com/closingbalance").newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }


    public ArrayList<Compute> processDataResults(Response response) {
        ArrayList<Compute> data = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONArray jsonArray = new JSONArray(jsonData);

            if (response.isSuccessful())
            {
                for (int i=0; i <jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int months = jsonObject.getInt("months");
                    String principal = jsonObject.getString("principal");
                    String random = jsonObject.getString("random");
                    String pricipale = jsonObject.getString("pricipale");
                    String total = jsonObject.getString("total");
                    String interest = jsonObject.getString("interest");

                    Compute compute = new Compute(months,principal,random,pricipale,total,interest);
                    data.add(compute);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  data;
    }




}


