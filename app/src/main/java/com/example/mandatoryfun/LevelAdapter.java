package com.example.mandatoryfun;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.MyViewHolder> {
    //public static final String EXTRA_TYPE_WORD = "com.example.mandatoryfun.EXTRA_TYPE_WORD";
    //public static final String EXTRA_TYPE_DEF = "com.example.mandatoryfun.EXTRA_TYPE_DEF";

    String data1[], data2[];
    String testType;
    Context context;

    public LevelAdapter(Context ct, String s1[], String s2[], String type){
        context = ct;
        data1 = s1;
        data2 = s2;
        testType = type;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.level_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.score.setText(data2[position]);
        holder.levelNumber.setText(data1[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView score;
        Button levelNumber;

        public MyViewHolder(@NonNull View view) {
            super(view);
            score = view.findViewById(R.id.textViewScoreText);
            levelNumber = view.findViewById(R.id.buttonLevelSelect);

            itemView.findViewById(R.id.buttonLevelSelect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (testType.equals("typed")) {
                        Intent intent = new Intent(context, Spelling.class);
                        Integer level = getLayoutPosition();
                        intent.putExtra("word", data1[level]);
                        context.startActivity(intent);
                    }
                    else if (testType.equals("choice")) {
                        Intent intent = new Intent(context, SpellingChoice.class);
                        Integer level = getLayoutPosition();
                        intent.putExtra("word", data1[level]);
                        context.startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(context, Vocab.class);
                        Integer level = getLayoutPosition();
                        intent.putExtra("word", data1[level]);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }


    }

