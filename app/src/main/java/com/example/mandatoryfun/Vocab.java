package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Vocab extends AppCompatActivity {

    TextView testText;
    TextView testText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        testText = findViewById(R.id.textViewVocabWord);
        testText2 = findViewById(R.id.textViewTestingText3);

        Intent intent = getIntent();
        testText = findViewById(R.id.textViewVocabWord);
        testText2 = findViewById(R.id.textViewTestingText1);

        String test = intent.getStringExtra("word");
        testText.setText(test);
        testText2.setText("Test Vocab");
    }
}