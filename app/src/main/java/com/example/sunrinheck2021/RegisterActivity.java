package com.example.sunrinheck2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText id_input, pw_input, userName_input, marimoName_input;
    private TextView register_btn;
    private String id, pw, userName, marimoName;
    private FirebaseAuth mAuth;
    private TextView question_tv;
    private int year, month, day, growth, period;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Intent intent = new Intent(getApplicationContext(), Alarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,intent, 0);

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 5);
        calendar1.set(Calendar.MINUTE, 49);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        // 지정한 시간에 매일 알림
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(),  AlarmManager.INTERVAL_DAY, pIntent);
















        Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.e("date",""+year+"/"+month+"/"+day);

        month++;

        id_input = findViewById(R.id.register_id_input);
        pw_input = findViewById(R.id.register_pw_input);
        register_btn = findViewById(R.id.register_registerBtn);
        userName_input = findViewById(R.id.register_name_input);
        marimoName_input = findViewById(R.id.register_marimoName_input);
        question_tv = findViewById(R.id.question_bt);

        mAuth = FirebaseAuth.getInstance();

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        question_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        register_btn.setOnClickListener(v-> {
            id = id_input.getText().toString();
            pw = pw_input.getText().toString();
            userName = userName_input.getText().toString();
            marimoName = marimoName_input.getText().toString();
            mAuth.createUserWithEmailAndPassword(id, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("TAG", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                String uid = user.getUid();

                                growth = 5;
                                period = 1;

                                Map<String, Object> userStats = new HashMap<>();
                                userStats.put("id",id);
                                userStats.put("pw",pw);
                                userStats.put("userName",userName);
                                userStats.put("marimoName",marimoName);
                                userStats.put("uid",uid);
                                userStats.put("year",year);
                                userStats.put("month",month);
                                userStats.put("day",day);
                                userStats.put("growth",growth);
                                userStats.put("period",period);

                                database.collection("users").add(userStats);

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                setResult(101);
                                finish();
                            } else {
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}