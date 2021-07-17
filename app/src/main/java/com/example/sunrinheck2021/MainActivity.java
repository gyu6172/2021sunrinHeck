package com.example.sunrinheck2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView main_bottom;
    private FloatingActionButton fab;
    private String id, pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_bottom = findViewById(R.id.bottomnav_main);
        fab = findViewById(R.id.fab_main);

        switchFragment(MainFragment.newInstance());

        Intent intent1 = getIntent();
        id = intent1.getStringExtra("id");
        pw = intent1.getStringExtra("pw");

        SharedPreferences sharedPreferences = getSharedPreferences("mainPref",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("pw",pw);
        editor.apply();

        main_bottom.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_shop:
                    Intent intent = new Intent(this,BackgroundActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bottom_list:
                    Intent intent2 = new Intent(this,DailyActivity.class);
                    startActivity(intent2);
                    break;
            }
            return true;
        });

        fab.setOnClickListener(v->{
            switchFragment(MainFragment.newInstance());
        });
    }


    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }
}