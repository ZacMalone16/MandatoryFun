package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ControlPanel extends AppCompatActivity {
    private Button spellingButton;
    private Button vocabButton;
    private Button spellingRemove;
    private Button vocabRemove;
    private TextView spellingWord;
    private TextView vocabWord;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);
        spellingButton = findViewById(R.id.buttonCPSpellAdd);
        vocabButton = findViewById(R.id.buttonCPVocabAdd);
        spellingWord = findViewById(R.id.editTextCPSpellWordtext);
        vocabWord = findViewById(R.id.editTextCPVocabWordtext);
        spellingRemove = findViewById(R.id.buttonCPSpellDelete);
        vocabRemove = findViewById(R.id.buttonCPVocabDelete);

        spellingRemove.setVisibility(View.GONE);
        vocabRemove.setVisibility(View.GONE);

        spellingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spellingWord.getText().length() > 0) {
                    DictionaryHelper dh = new DictionaryHelper(ControlPanel.this, true, spellingWord.getText().toString().toLowerCase());
                    url = dictionaryEntries(spellingWord.getText().toString());
                    dh.execute(url);

                    spellingWord.setText("");
                }
                else{
                        Toast.makeText(ControlPanel.this, "Not Added",
                                Toast.LENGTH_LONG).show();
                        spellingWord.setText("");
                    }
            }
        });

        vocabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (vocabWord.getText().length() > 0) {
                    DictionaryHelper dh = new DictionaryHelper(ControlPanel.this, false, vocabWord.getText().toString().toLowerCase());
                    url = dictionaryEntries(vocabWord.getText().toString());
                    dh.execute(url);


                    vocabWord.setText("");
                }
                else {
                    Toast.makeText(ControlPanel.this, "Not Added",
                            Toast.LENGTH_LONG).show();
                    spellingWord.setText("");
                }
            }
        });
        spellingRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ControlPanel.this);
                db.getWritableDatabase();
                db.deleteSpelling(spellingWord.getText().toString());
            }
        });
        vocabRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ControlPanel.this);
                db.getWritableDatabase();
                db.deleteSpelling(vocabWord.getText().toString());
            }
        });
    }

    private String dictionaryEntries (String theWord) {
        final String language = "en-us";
        final String word = theWord;
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }
}