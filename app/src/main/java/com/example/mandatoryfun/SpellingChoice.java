package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SpellingChoice extends AppCompatActivity {

    TextView testText;
    TextView testText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling_choice);
        Intent intent = getIntent();
        testText = findViewById(R.id.textViewChoiceWord);
        testText2 = findViewById(R.id.textViewTestingText1);

        String test = intent.getStringExtra("word");
        testText.setText(test);
        testText2.setText("Test Choices");
    }
}