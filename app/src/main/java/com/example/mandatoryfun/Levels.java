package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Levels extends AppCompatActivity {
    String s1[], s2[];
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Intent intent = getIntent();
        String type = intent.getStringExtra(MainActivity.EXTRA_TYPE);
        recyclerView = findViewById(R.id.recyclerView);
        LevelAdapter levelAdapter;


        //s1 and s2 are just for testing and will be replaced
        s1 = getResources().getStringArray(R.array.TestOfTen);
        s2 = getResources().getStringArray(R.array.Test2OfTen);

        if (type.equals("typed")) {
            recyclerView.setBackgroundColor(Color.GREEN);
            levelAdapter = new LevelAdapter(this, s1, s2, type);
        }
        else if (type.equals("choice")) {
            recyclerView.setBackgroundColor(Color.CYAN);
            levelAdapter = new LevelAdapter(this, s1, s2, type);
        }
        else{
            recyclerView.setBackgroundColor(Color.YELLOW);
            levelAdapter = new LevelAdapter(this, s1, s2, type);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(levelAdapter);

    }
}