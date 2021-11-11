package com.example.mandatoryfun;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class Spelling extends AppCompatActivity {
    TextView testText;
    TextView testText2;
    //EditText userAnswer/*, date, numCorrect, numMissed*/;
    //Button insert, view;
    //DB_spelling DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling);

        testText = findViewById(R.id.textViewSpellingWord);
        testText2 = findViewById(R.id.textViewTestingText2);

        Intent intent = getIntent();
        testText = findViewById(R.id.textViewSpellingWord);
        String test = intent.getStringExtra("word");
        testText.setText(test);
        testText2.setText("Test Typed Spelling");


    }
}