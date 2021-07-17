package com.example.sunrinheck2021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    RecyclerView store_recyclerView;
    ArrayList<Item> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        store_recyclerView = findViewById(R.id.store_recyclerView);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),
                new LinearLayoutManager(getApplicationContext()).getOrientation());
        store_recyclerView.addItemDecoration(dividerItemDecoration);
        store_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecyclerAdapter adapter = new RecyclerAdapter(datas);

        adapter.setOnRecyclerItemClickListener(new RecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StoreActivity.this);
                if (datas.get(position).getName().equals("구매완료")) {
                    builder.setTitle("배경화면 적용");
                    builder.setMessage("적용하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            datas.set(position, new Item(datas.get(position).getBackgroundImg(), "구매완료", 0));
                            adapter.notifyDataSetChanged();
                            Toast.makeText(StoreActivity.this, "적용되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    builder.setTitle("배경화면 구매");
                    builder.setMessage("구매하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            datas.set(position, new Item(datas.get(position).getBackgroundImg(), "구매완료", 0));
                            adapter.notifyDataSetChanged();
                            Toast.makeText(StoreActivity.this, "구매되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        store_recyclerView.setAdapter(adapter);
        datas.add(new Item(getResources().getDrawable(R.drawable.ic_launcher_background),"응애",10000));
        datas.add(new Item(getResources().getDrawable(R.drawable.ic_launcher_background),"응애",10000));
        datas.add(new Item(getResources().getDrawable(R.drawable.ic_launcher_background),"응애",10000));
        datas.add(new Item(getResources().getDrawable(R.drawable.ic_launcher_background),"응애",10000));
        adapter.notifyDataSetChanged();
    }
}