package com.example.sunrinheck2021;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class MainFragment extends Fragment {


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private String id, pw;
    private String marimoName;
    private int size, period;
    private FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Inflate the layout for this fragment

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mainPref",0);
        id = sharedPreferences.getString("id","");
        pw = sharedPreferences.getString("pw","");

        Log.e("id/pw",id+"/"+pw);

        db = FirebaseFirestore.getInstance();
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.getData().get("id").toString().equals(id)){
                            marimoName = document.getData().get("marimoName").toString();
                            size = Integer.parseInt(document.getData().get("growth").toString());
                            period = Integer.parseInt(document.getData().get("period").toString());
                            Log.e("stats",marimoName+size+period);
                        }
                    }
                }
                else{
                    Log.e("mainFragment","failed");
                }
            }
        });

        return view;
    }
}