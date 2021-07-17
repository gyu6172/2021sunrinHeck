package com.example.sunrinheck2021;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class marimoFragment extends Fragment {

    RecyclerView marimoRecycler;
    ArrayList<marimoItem> datas = new ArrayList<>();
    marimoAdapter adapter;

    public marimoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_blank, container, false);
        marimoRecycler = (RecyclerView)v.findViewById(R.id.marimoRecycler);
        marimoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        datas.add(new marimoItem("name","time",0.44));
        adapter = new marimoAdapter(datas);
        marimoRecycler.setAdapter(adapter);

        return v;
    }
}