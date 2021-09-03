package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button spelling;
    private Button vocab;
    private Button report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spelling = (Button) findViewById(R.id.buttonSpelling);
        vocab = (Button) findViewById(R.id.buttonVocabulary);
        report = (Button) findViewById(R.id.buttonReport);

        spelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSpelling();
            }
        });

        vocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVocab();
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReport();
            }
        });
    }
    public void openSpelling(){
        Intent intent = new Intent(this, Spelling.class);
        startActivity(intent);
    }

    public void openVocab(){
        Intent intent = new Intent(this, Vocab.class);
        startActivity(intent);
    }

    public void openReport(){
        Intent intent = new Intent(this, Report.class);
        startActivity(intent);
    }
}