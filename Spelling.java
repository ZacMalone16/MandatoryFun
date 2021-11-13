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
    EditText userAnswer;
    Button insert, view;
    DB_spelling DB;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling);

        testText = findViewById(R.id.textViewSpellingWord);
        testText2 = findViewById(R.id.textViewTestingText2);

        intent = getIntent();
        testText = findViewById(R.id.textViewSpellingWord);
        String test = intent.getStringExtra("word");
        testText.setText(test);
        testText2.setText("Test Typed Spelling");

        userAnswer = findViewById(R.id.userAnswer);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        DB = new DB_spelling(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answerTXT = userAnswer.getText().toString();


                Boolean checkinsertdata = DB.insertuseranswer(answerTXT);
                if(checkinsertdata==true)
                    Toast.makeText(Spelling.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Spelling.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Spelling.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Answer :"+res.getString(0)+"\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Spelling.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
    }
}