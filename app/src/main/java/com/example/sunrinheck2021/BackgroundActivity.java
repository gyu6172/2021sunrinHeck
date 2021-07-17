package com.example.sunrinheck2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class BackgroundActivity extends AppCompatActivity {

    private TextView equipTv1, equipTv2, equipTv3, equipTv4;
    private FrameLayout bg1, bg2, bg3, bg4;
    private SharedPreferences sharedPreferences;
    private int equipState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        sharedPreferences = getSharedPreferences("mainPref",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        equipState = sharedPreferences.getInt("equip",1);

        bg1 = findViewById(R.id.bg1);
        bg2 = findViewById(R.id.bg2);
        bg3 = findViewById(R.id.bg3);
        bg4 = findViewById(R.id.bg4);
        equipTv1 = findViewById(R.id.equip_base);
        equipTv2 = findViewById(R.id.equip_background_1);
        equipTv3 = findViewById(R.id.equip_background_2);
        equipTv4 = findViewById(R.id.equip_background_3);

        Intent intent = new Intent(BackgroundActivity.this, MainActivity.class);

        switch (equipState){
            case 1:
                equipTv1.setVisibility(View.VISIBLE);
                equipTv2.setVisibility(View.INVISIBLE);
                equipTv3.setVisibility(View.INVISIBLE);
                equipTv4.setVisibility(View.INVISIBLE);
                break;

            case 2:
                equipTv1.setVisibility(View.INVISIBLE);
                equipTv2.setVisibility(View.VISIBLE);
                equipTv3.setVisibility(View.INVISIBLE);
                equipTv4.setVisibility(View.INVISIBLE);
                break;

            case 3:
                equipTv1.setVisibility(View.INVISIBLE);
                equipTv2.setVisibility(View.INVISIBLE);
                equipTv3.setVisibility(View.VISIBLE);
                equipTv4.setVisibility(View.INVISIBLE);
                break;

            case 4:
                equipTv1.setVisibility(View.INVISIBLE);
                equipTv2.setVisibility(View.INVISIBLE);
                equipTv3.setVisibility(View.INVISIBLE);
                equipTv4.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }

        bg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equipTv1.setVisibility(View.VISIBLE);
                equipTv2.setVisibility(View.INVISIBLE);
                equipTv3.setVisibility(View.INVISIBLE);
                equipTv4.setVisibility(View.INVISIBLE);
                editor.putInt("equip",1);
                editor.apply();
                startActivity(intent);
            }
        });
        bg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equipTv1.setVisibility(View.INVISIBLE);
                equipTv2.setVisibility(View.VISIBLE);
                equipTv3.setVisibility(View.INVISIBLE);
                equipTv4.setVisibility(View.INVISIBLE);
                editor.putInt("equip",2);
                editor.apply();
                startActivity(intent);
            }
        });
        bg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equipTv1.setVisibility(View.INVISIBLE);
                equipTv2.setVisibility(View.INVISIBLE);
                equipTv3.setVisibility(View.VISIBLE);
                equipTv4.setVisibility(View.INVISIBLE);
                editor.putInt("equip",3);
                editor.apply();
                startActivity(intent);
            }
        });
        bg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equipTv1.setVisibility(View.INVISIBLE);
                equipTv2.setVisibility(View.INVISIBLE);
                equipTv3.setVisibility(View.INVISIBLE);
                equipTv4.setVisibility(View.VISIBLE);
                editor.putInt("equip",4);
                editor.apply();
                startActivity(intent);
            }
        });
    }
}