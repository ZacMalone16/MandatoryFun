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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Intent intent = getIntent();
        String type = intent.getStringExtra(MainActivity.EXTRA_TYPE);
        recyclerView = findViewById(R.id.recyclerView);
        LevelAdapter levelAdapter;
        DBHelper db = new DBHelper(this);
        Integer value = db.spellCount();
        Integer value2 = db.vocabCount();
        //s1 and s2 are just for testing and will be replaced
        s1 = makeArray(value);
        s2 = makeArray(value2);

        if (type.equals("choice")) {
            recyclerView.setBackgroundColor(Color.CYAN);
            levelAdapter = new LevelAdapter(this, s1, s2, type);
        }
        else if (type.equals("typed")) {
            recyclerView.setBackgroundColor(Color.GREEN);
            levelAdapter = new LevelAdapter(this, s1, s2, type);
        }
        else{
            recyclerView.setBackgroundColor(Color.YELLOW);
            levelAdapter = new LevelAdapter(this, s2, s1, type);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(levelAdapter);



    }

    public String[] makeArray(Integer length){
        String[] array = new String[length];

        Integer i = 0;
        while (i <= length-1) {
            Integer level = i+1;
            array[i] = level.toString();

            i++;
        }

        return array;
    }
}