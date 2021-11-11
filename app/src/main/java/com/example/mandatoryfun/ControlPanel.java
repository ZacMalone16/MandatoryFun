package com.example.mandatoryfun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

public class ControlPanel extends AppCompatActivity {
    EditText spellAnswer, vocabAnswer /*, vocabDefin , spellDefin, date, counter*/;
    Button insert, view, update, delete, insert2, view2, update2, delete2;
    DBSpelling DB;
    DBVocabulary DBv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);


        spellAnswer = findViewById(R.id.spellAnswer);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        DB = new DBSpelling(this);

        vocabAnswer = findViewById(R.id.vocabAnswer);
        insert2 = findViewById(R.id.btnInsert2);
        view2 = findViewById(R.id.btnView2);
        update2 = findViewById(R.id.btnUpdate2);
        delete2 = findViewById(R.id.btnDelete2);
        DBv = new DBVocabulary(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answerTXT = spellAnswer.getText().toString();
                //String definTXT = spellDefin.getText().toString();
                //Integer dateTXT = Integer.parseInt(String.valueOf(date.getText()));
                //Integer nmTXT = Integer.parseInt(String.valueOf(numMissed.getText()));

                Boolean checkinsertdata = DB.insertuseranswer(answerTXT /*, dateTXT, ncTXT, nmTXT*/);
                if(checkinsertdata==true)
                    Toast.makeText(ControlPanel.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ControlPanel.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answerTXT = spellAnswer.getText().toString();
                //String definTXT = spellDefin.getText().toString();
                //String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(answerTXT /*definTXT*/);
                if(checkupdatedata==true)
                    Toast.makeText(ControlPanel.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ControlPanel.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answerTXT = spellAnswer.getText().toString();
                Boolean checkudeletedata = DB.deletedata(answerTXT);
                if(checkudeletedata==true)
                    Toast.makeText(ControlPanel.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ControlPanel.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(ControlPanel.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Answer: "+res.getString(0)+"\n");
                    //buffer.append("Definition :"+res.getString(1)+"\n");
                    //buffer.append("Date :"+res.getString(2)+"\n");
                    //buffer.append("Counter :"+res.getString(3)+"\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ControlPanel.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }       });

        insert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                String answerTXT = vocabAnswer.getText().toString();
                //String definTXT = vocabDefin.getText().toString();
                //Integer dateTXT = Integer.parseInt(String.valueOf(date.getText()));
                //Integer nmTXT = Integer.parseInt(String.valueOf(numMissed.getText()));

                Boolean checkinsertdata = DBv.insertuseranswer(answerTXT /*definTXT, ncTXT, nmTXT*/);
                if(checkinsertdata==true)
                    Toast.makeText(ControlPanel.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ControlPanel.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                String answerTXT = vocabAnswer.getText().toString();
                //String definTXT = vocabDefin.getText().toString();
                //String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DBv.updateuserdata(answerTXT /*definTXT*/);
                if(checkupdatedata==true)
                    Toast.makeText(ControlPanel.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ControlPanel.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                String answerTXT = vocabAnswer.getText().toString();
                Boolean checkudeletedata = DBv.deletedata(answerTXT);
                if(checkudeletedata==true)
                    Toast.makeText(ControlPanel.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ControlPanel.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view2) {
                                        Cursor res = DBv.getdata();
                                        if(res.getCount()==0){
                                            Toast.makeText(ControlPanel.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        StringBuffer buffer = new StringBuffer();
                                        while(res.moveToNext()){
                                            buffer.append("Answer: "+res.getString(0)+"\n");
                                            //buffer.append("Definition :"+res.getString(1)+"\n");
                                            //buffer.append("Date :"+res.getString(2)+"\n");
                                            //buffer.append("Counter :"+res.getString(3)+"\n");

                                        }

                                        AlertDialog.Builder builder = new AlertDialog.Builder(ControlPanel.this);
                                        builder.setCancelable(true);
                                        builder.setTitle("User Entries");
                                        builder.setMessage(buffer.toString());
                                        builder.show();

                                    }       });

    }
}
