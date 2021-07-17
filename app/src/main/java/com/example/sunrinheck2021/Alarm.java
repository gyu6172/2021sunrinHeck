package com.example.sunrinheck2021;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import androidx.core.app.NotificationCompat;

public class Alarm extends BroadcastReceiver {

    static RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;
    private int num;
    private int number;
    @Override
    public void onReceive(Context context, Intent intent){
        sharedPreferences = context.getSharedPreferences("check_day",0);
        num = sharedPreferences.getInt("check",1);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (num == 7){
            editor.putInt("check",0);
            NotificationCompat.Builder builders = new NotificationCompat.Builder(context, "default");
            builders.setSmallIcon(R.mipmap.ic_launcher);
            builders.setContentTitle("마이모");
            builders.setContentText("혹시 물이 적지 않은지 확인해 주세요");
            builders.setAutoCancel(true);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
            }
            notificationManager.notify(1, builders.build());

        }else{
            editor.putInt("check",num++);

        }

        CurrentWeatherCall(context);
    }

    private void CurrentWeatherCall(Context context){

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }

        String url = "http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=b4b515952c9a195c9e4c159bc806de56";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String city = jsonObject.getString("name");
                    JSONArray weatherJson = jsonObject.getJSONArray("weather");
                    JSONObject weatherObj = weatherJson.getJSONObject(0);
                    String weather = weatherObj.getString("description");
                    JSONObject tempK = new JSONObject(jsonObject.getString("main"));
                    double tempDo = (Math.round((tempK.getDouble("temp")-273.15)*100)/100.0);

                    // teamK - 기온, weather - 날씨

                    if (tempDo>32){
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
                        builder.setSmallIcon(R.mipmap.ic_launcher);
                        builder.setContentTitle("마이모");
                        builder.setContentText("날씨가 너무 더워요 ㅜㅜ 직접적인 햇빛을 피해주세요");
                        builder.setAutoCancel(true);
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
                        }
                        notificationManager.notify(1, builder.build());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //응애~
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }
}

