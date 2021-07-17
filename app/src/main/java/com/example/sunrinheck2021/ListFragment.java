package com.example.sunrinheck2021;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView marimoRecycler;
    ArrayList<marimoItem> datas = new ArrayList<>();
    marimoAdapter adapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_list, container, false);
        marimoRecycler = (RecyclerView)v.findViewById(R.id.marimoRecycler);
        marimoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        datas.add(new marimoItem("name","time",0.44));
        adapter = new marimoAdapter(datas);
        marimoRecycler.setAdapter(adapter);

        return v;
    }
}