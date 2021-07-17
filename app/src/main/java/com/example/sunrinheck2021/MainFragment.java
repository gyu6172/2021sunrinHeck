package com.example.sunrinheck2021;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static com.example.sunrinheck2021.R.drawable.copy_1;
import static com.example.sunrinheck2021.R.drawable.copy_2;
import static com.example.sunrinheck2021.R.drawable.copy_3;
import static com.example.sunrinheck2021.R.drawable.invalid_name;

public class MainFragment extends Fragment {


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private String id, pw;
    private String marimoName;
    private int size, period;
    private FirebaseFirestore db;
    private int equipState;
    private ConstraintLayout mainImage;
    private ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mainImage = view.findViewById(R.id.main_image);
        imageView = view.findViewById(R.id.imageView);

        equipState = 1;
        // Inflate the layout for this fragment

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mainPref",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        equipState = sharedPreferences.getInt("equip",1);

        Drawable drawable;

        switch (equipState){
            case 1:
                drawable = getResources().getDrawable(invalid_name);
                mainImage.setBackground(drawable);
                break;

            case 2:
                drawable = getResources().getDrawable(copy_1);
                mainImage.setBackground(drawable);
                break;

            case 3:
                drawable = getResources().getDrawable(copy_2);
                mainImage.setBackground(drawable);
                break;

            case 4:
                drawable = getResources().getDrawable(copy_3);
                mainImage.setBackground(drawable);
                break;

            default:
                break;
        }

        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.bounce);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(animation);

                long seed = System.currentTimeMillis();
                Random random = new Random(seed);

                int n = random.nextInt(4);
                switch (n){
                    case 0:
                        imageView.setImageResource(R.drawable.img_0040);
                        break;

                    case 1:
                        imageView.setImageResource(R.drawable.img_0045);
                        break;

                    case 2:
                        imageView.setImageResource(R.drawable.img_0046);
                        break;

                    case 3:
                        imageView.setImageResource(R.drawable.img_0047);
                        break;

                    default:
                        break;
                }
            }
        });

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
                            editor.putString("marimoName",marimoName);
                            editor.putInt("size", size);
                            editor.putInt("period",period);
                            editor.apply();

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