package com.example.sunrinheck2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DailyActivity extends AppCompatActivity {

    private TextView marimoNameTv, sizeTv, periodTv;
    private String marimoName;
    private int size, period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        marimoNameTv = findViewById(R.id.daily_name);
        sizeTv = findViewById(R.id.daily_size);
        periodTv = findViewById(R.id.daily_day);

        SharedPreferences sharedPreferences = getSharedPreferences("mainPref",0);
        marimoName = sharedPreferences.getString("marimoName","");
        size = sharedPreferences.getInt("size",-1);
        period = sharedPreferences.getInt("period",-1);

        marimoNameTv.setText(marimoName);
        sizeTv.setText(""+size+"cm");
        periodTv.setText("DAY-"+period);
    }
}