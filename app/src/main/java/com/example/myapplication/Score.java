package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Score extends AppCompatActivity {
    private TextView score_textview, description;
    private ImageView image_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score_textview = findViewById(R.id.score_text);
        description = findViewById(R.id.description);
        image_description = findViewById(R.id.image_description);

        Intent intent = getIntent();
        String score = intent.getStringExtra("SCORE");

        int parse_score = Integer.parseInt(score);

        if (parse_score >= 0 && parse_score <= 2){
            description.setText("You Are A Failure!");
            image_description.setImageResource(R.drawable.img0_2);
        }
        else if(parse_score == 3 && parse_score == 4){
            description.setText("What Are You Doing!");
            image_description.setImageResource(R.drawable.img3_4);
        }
        else if(parse_score >= 5 && parse_score <= 7){
            description.setText("Ehhhhhh!");
            image_description.setImageResource(R.drawable.img5_7);
        }
        else if(parse_score == 3 && parse_score == 4){
            description.setText("Very Wise!");
            image_description.setImageResource(R.drawable.img8_9);
        }
        else if(parse_score == 10){
            description.setText("You Are A Legend!");
            image_description.setImageResource(R.drawable.img10);
        }

        score_textview.setText(score);
    }

    public void play_again(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void home(View v){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }
}
