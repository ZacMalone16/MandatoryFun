package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button spellingType;
    private Button spellingChoice;
    private Button vocab;
    private Button control;
    public static final String EXTRA_TYPE = "com.example.mandatoryfun.EXTRA_TYPE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spellingType = (Button) findViewById(R.id.buttonSpelling);
        spellingChoice = (Button) findViewById(R.id.buttonSpellingMulti);
        vocab = (Button) findViewById(R.id.buttonVocabulary);
        control = (Button) findViewById(R.id.buttoncontrol_panel);

        spellingType.setBackgroundColor(Color.GREEN);
        spellingChoice.setBackgroundColor(Color.CYAN);
        vocab.setBackgroundColor(Color.YELLOW);
        control.setBackgroundColor(Color.LTGRAY);

        spellingType.setOnClickListener(new View.OnClickListener() {
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

        spellingChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSpellingChoice();
            }
        });
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openControlPanel();
            }
        });

    }
    public void openSpelling(){
        Intent intent = new Intent(this, Levels.class);
        intent.putExtra(EXTRA_TYPE, "typed");
        startActivity(intent);
    }

    public void openVocab(){
        Intent intent = new Intent(this, Levels.class);
        intent.putExtra(EXTRA_TYPE, "vocab");
        startActivity(intent);
    }

    public void openSpellingChoice(){
        Intent intent = new Intent(this, Levels.class);
        intent.putExtra(EXTRA_TYPE, "choice");
        startActivity(intent);
    }

    public void openControlPanel(){
        Intent intent = new Intent(this, ControlPanel.class);
        startActivity(intent);
    }
}