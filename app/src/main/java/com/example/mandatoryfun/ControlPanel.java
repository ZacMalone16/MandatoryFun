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
    private TextView spellingWord;
    private TextView VocabWord;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);
        spellingButton = findViewById(R.id.buttonCPSpellAdd);
        vocabButton = findViewById(R.id.buttonCPVocabAdd);
        spellingWord = findViewById(R.id.editTextCPSpellWordtext);
        VocabWord = findViewById(R.id.editTextCPVocabWordtext);



        spellingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spellingWord.getText().length() > 0) {
                    DictionaryHelper dh = new DictionaryHelper(ControlPanel.this, true, spellingWord.getText().toString().toLowerCase());
                    url = dictionaryEntries(spellingWord.getText().toString());
                    dh.execute(url);

                    Toast.makeText(ControlPanel.this, "Added!",
                            Toast.LENGTH_LONG).show();
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

                if (VocabWord.getText().length() > 0) {
                    DictionaryHelper dh = new DictionaryHelper(ControlPanel.this, false, VocabWord.getText().toString().toLowerCase());
                    url = dictionaryEntries(VocabWord.getText().toString());
                    dh.execute(url);

                    Toast.makeText(ControlPanel.this, "Added!",
                            Toast.LENGTH_LONG).show();
                    VocabWord.setText("");
                }
                else {
                    Toast.makeText(ControlPanel.this, "Not Added",
                            Toast.LENGTH_LONG).show();
                    spellingWord.setText("");
                }
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