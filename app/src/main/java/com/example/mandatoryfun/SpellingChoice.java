
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

public class SpellingChoice extends AppCompatActivity {
    TextView testText;
    TextView testText2;
    TextToSpeech mTTS;
    Button sound;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        setContentView(R.layout.activity_spelling_choice);
        DBHelper db = new DBHelper(this);
        sound = findViewById(R.id.buttonSpellingSound);
        testText = findViewById(R.id.textViewSpellingWord);
        testText = findViewById(R.id.textViewSpellingWord);
        Integer level = Integer.parseInt(intent.getStringExtra("word"));
        button1 = findViewById(R.id.buttonSpell1);
        button2 = findViewById(R.id.buttonSpell2);
        button3 = findViewById(R.id.buttonSpell3);
        button4 = findViewById(R.id.buttonSpell4);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Integer result = mTTS.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {

                        Toast.makeText(SpellingChoice.this, "Language Error!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        sound.setEnabled(true);
                    }
                } else {
                    Toast.makeText(SpellingChoice.this, "Error!",
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

        String text = db.getWord(level);
        String text2 = db.getDef(level);
        testText.setText(text2);
        List<String> words = sortList(text);
        button1.setText(words.get(0));
        button2.setText(words.get(1));
        button3.setText(words.get(2));
        button4.setText(words.get(3));

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(button1.getText().toString() == text){

                    Toast.makeText(SpellingChoice.this, "Good Job!",
                            Toast.LENGTH_LONG).show();

                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    db.getWritableDatabase();
                    db.updateSpellStar(level);

                    Intent intent = new Intent(SpellingChoice.this, Levels.class);
                    intent.putExtra(MainActivity.EXTRA_TYPE, "choice");
                    startActivity(intent);

                }
                else
                    Toast.makeText(SpellingChoice.this, "Try Again!",
                            Toast.LENGTH_LONG).show();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button2.getText().toString() == text) {

                    Toast.makeText(SpellingChoice.this, "Amazing!",
                            Toast.LENGTH_LONG).show();

                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    db.getWritableDatabase();
                    db.updateSpellStar(level);

                    Intent intent = new Intent(SpellingChoice.this, Levels.class);
                    intent.putExtra(MainActivity.EXTRA_TYPE, "choice");
                    startActivity(intent);
                } else
                    Toast.makeText(SpellingChoice.this, "So Close!",
                            Toast.LENGTH_LONG).show();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button3.getText().toString() == text) {
                    Toast.makeText(SpellingChoice.this, "You Rock!",
                            Toast.LENGTH_LONG).show();

                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    db.getWritableDatabase();
                    db.updateSpellStar(level);

                    Intent intent = new Intent(SpellingChoice.this, Levels.class);
                    intent.putExtra(MainActivity.EXTRA_TYPE, "choice");
                    startActivity(intent);

                } else
                    Toast.makeText(SpellingChoice.this, "Almost!",
                            Toast.LENGTH_LONG).show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button4.getText().toString() == text) {
                    Toast.makeText(SpellingChoice.this, "Fantastic Work!",
                            Toast.LENGTH_LONG).show();

                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    db.getWritableDatabase();
                    db.updateSpellStar(level);

                    Intent intent = new Intent(SpellingChoice.this, Levels.class);
                    intent.putExtra(MainActivity.EXTRA_TYPE, "choice");
                    startActivity(intent);

                } else
                    Toast.makeText(SpellingChoice.this, "Keep Trying!",
                            Toast.LENGTH_LONG).show();
            }

        });

    }

    private List<String> sortList(String text) {
        List<String> words = new ArrayList<>();
        words.add(text);
        Integer length = text.length();
        char[] charArray1 = text.toCharArray();

        if (charArray1[length / 2] != 'a')
            charArray1[length / 2] = 'a';
        else
            charArray1[length / 2] = 'o';
        words.add(String.valueOf(charArray1));

        char[] charArray2 = text.toCharArray();
        if (charArray2[length / 2] != 'e')
            charArray2[length / 2] = 'e';
        else
            charArray2[length / 2] = 'o';
        words.add(String.valueOf(charArray2));

        char[] charArray3 = text.toCharArray();
        if (charArray2[length / 2] != 'i')
            charArray3[length / 2] = 'i';
        else
            charArray3[length / 2] = 'o';

        words.add(String.valueOf(charArray3));

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