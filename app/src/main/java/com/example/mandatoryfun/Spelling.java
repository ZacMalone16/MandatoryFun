package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Spelling extends AppCompatActivity {

    TextView def;

    Button sound;
    Button submit;
    TextToSpeech mTTS;
    EditText type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling);
        Intent intent = getIntent();
        DBHelper db = new DBHelper(this);
        sound = findViewById(R.id.buttonTypeSound);
        Integer level = Integer.parseInt(intent.getStringExtra("word"));
        submit = findViewById(R.id.buttonSubmit);
        type = findViewById(R.id.editTextTextTypedWord);
        type.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        def = findViewById(R.id.textViewTypedDef);
        def.setText(db.getDef(level));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.length() > 0) {
                    if(db.getWord(level).equals(type.getText().toString().toLowerCase())) {
                        submit.setEnabled(false);

                        db.getWritableDatabase();
                        db.updateSpellStar2(level);

                        Intent intent = new Intent(Spelling.this, Levels.class);
                        intent.putExtra(MainActivity.EXTRA_TYPE, "typed");
                        startActivity(intent);

                        Toast.makeText(Spelling.this, "Great!",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(Spelling.this, "Try Again!",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Spelling.this, "To Few Letters!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Integer result = mTTS.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {

                        Toast.makeText(Spelling.this, "Language Error!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        sound.setEnabled(true);
                    }
                } else {
                    Toast.makeText(Spelling.this, "Error!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak(db.getWord(level));
            }
        });
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