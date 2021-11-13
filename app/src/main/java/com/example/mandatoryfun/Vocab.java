package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Vocab extends AppCompatActivity {

    TextView testText;
    TextToSpeech mTTS;
    Button sound;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        Intent intent = getIntent();
        setContentView(R.layout.activity_vocab);
        DBHelper db = new DBHelper(this);
        sound = findViewById(R.id.buttonVocabSound);
        testText = findViewById(R.id.textViewVocabWord);
        Integer level = Integer.parseInt(intent.getStringExtra("word"));
        button1 = findViewById(R.id.buttonVocab1);
        button2 = findViewById(R.id.buttonVocab2);
        button3 = findViewById(R.id.buttonVocab3);
        button4 = findViewById(R.id.buttonVocab4);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Integer result = mTTS.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {

                        Toast.makeText(Vocab.this, "Language Error!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        sound.setEnabled(true);
                    }
                } else {
                    Toast.makeText(Vocab.this, "Error!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak(db.getVocabDef(level));
            }
        });

        String text = db.getVocabWord(level);
        String text2 = db.getVocabDef(level);
        testText.setText(text2);
        List<String> words = sortList();
        List<String> words2 = new ArrayList<>();
        words2.add(words.get(0));
        words2.add(words.get(1));
        words2.add(words.get(2));
        words2.add(db.getVocabWord(level));
        List<String> words3 = shuffle(words2);

        button1.setText(words3.get(0));
        button2.setText(words3.get(1));
        button3.setText(words3.get(2));
        button4.setText(words3.get(3));

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(button1.getText().toString().equals(db.getVocabWord(level))){

                    Toast.makeText(Vocab.this, "Good Job!",
                            Toast.LENGTH_LONG).show();

                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    db.getWritableDatabase();
                    db.updateVocabStar(level);

                    Intent intent = new Intent(Vocab.this, Levels.class);
                    intent.putExtra(MainActivity.EXTRA_TYPE, "vocab");
                    startActivity(intent);

                }
                else
                    Toast.makeText(Vocab.this, "Try Again!",
                            Toast.LENGTH_LONG).show();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (button2.getText().toString().equals(db.getVocabWord(level))){

                    Toast.makeText(Vocab.this, "Amazing!",
                            Toast.LENGTH_LONG).show();

                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    db.getWritableDatabase();
                    db.updateVocabStar(level);

                    Intent intent = new Intent(Vocab.this, Levels.class);
                    intent.putExtra(MainActivity.EXTRA_TYPE, "vocab");
                    startActivity(intent);
                } else
                    Toast.makeText(Vocab.this, "So Close!",
                            Toast.LENGTH_LONG).show();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button3.getText().toString().equals(db.getVocabWord(level))) {
                    Toast.makeText(Vocab.this, "You Rock!",
                            Toast.LENGTH_LONG).show();

                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    db.getWritableDatabase();
                    db.updateVocabStar(level);

                    Intent intent = new Intent(Vocab.this, Levels.class);
                    intent.putExtra(MainActivity.EXTRA_TYPE, "vocab");
                    startActivity(intent);

                } else
                    Toast.makeText(Vocab.this, "Almost!",
                            Toast.LENGTH_LONG).show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button4.getText().toString().equals(db.getVocabWord(level))) {
                    Toast.makeText(Vocab.this, "Fantastic Work!",
                            Toast.LENGTH_LONG).show();

                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    db.getWritableDatabase();
                    db.updateVocabStar(level);

                    Intent intent = new Intent(Vocab.this, Levels.class);
                    intent.putExtra(MainActivity.EXTRA_TYPE, "vocab");
                    startActivity(intent);

                } else
                    Toast.makeText(Vocab.this, "Keep Trying!",
                            Toast.LENGTH_LONG).show();
            }

        });

    }

    private List<String> sortList() {
        List<String> words = new ArrayList<>();

        words.add("ache");
        words.add("adjust");
        words.add("bashful");
        words.add("basket");
        words.add("cabin");
        words.add("caterpillar");
        words.add("daily");
        words.add("dainty");
        words.add("enormous");
        words.add("equal");
        words.add("fancy");
        words.add("fasten");
        words.add("gather");
        words.add("giant");
        words.add("hatch");
        words.add("heap");
        words.add("illustrator");
        words.add("injury");
        words.add("jealous");
        words.add("knob");
        words.add("lively");
        words.add("loosen");
        words.add("mask");
        words.add("misty");
        words.add("narrow");
        words.add("obey");
        words.add("pain");
        words.add("passenger");
        words.add("pattern");
        words.add("remove");
        words.add("repeat");
        words.add("scold");
        words.add("scratch");
        words.add("terrified");
        words.add("thick");
        words.add("upset");
        words.add("whimper");
        words.add("whirl");
        words.add("yank");

        return shuffle(words);
    }

    private void speak(String text){

        mTTS.setSpeechRate(0.6f);
        mTTS.setPitch(0.9f);
        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH,null);

    }
    @Override
    protected void onDestroy(){
        if(mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
    private List<String> shuffle(List<String> words) {
        Collections.shuffle(words);
        return words;
    }
}