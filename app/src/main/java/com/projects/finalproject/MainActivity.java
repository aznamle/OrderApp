package com.projects.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //arraylist
        modelList = new ArrayList<>();
        modelList.add(new Model("NVIDIA GeForce RTX 3060 Ti", R.drawable.card1));
        modelList.add(new Model("NVIDIA GeForce RTX 3090", R.drawable.card2));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        mAdapter = new OrderAdapter(this, modelList);

        recyclerView.setAdapter(mAdapter);
    }
}