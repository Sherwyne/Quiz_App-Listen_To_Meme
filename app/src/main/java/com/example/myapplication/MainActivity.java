package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView answer1, answer2, answer3, answer4, play;
    private TextView question_text, correct_text;
    private Button submit_button;
    private MediaPlayer player;
    private LinearLayout listen;
    private List<Question> questionList;
    private String USER_ANSWER, option1, option2, option3, option4, answer, sound_question;
    private Question question;
    private int CORRECT_COUNTER=0, QUESTION_COUNTER=1, QUESTION_SIZE;
    private List<String> options = new ArrayList<String>();
    private String uriPath;
    private ViewDialog dialog = new ViewDialog();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        QUESTION_SIZE = questionList.size();

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        correct_text = findViewById(R.id.counter_answers);
        question_text = findViewById(R.id.counter_question);
        play = findViewById(R.id.play_question);
        submit_button = findViewById(R.id.submit_button);
        listen = findViewById(R.id.linear_listen);

        Collections.shuffle(questionList);
        question = questionList.get(QUESTION_COUNTER - 1);
        option1 = question.getImage_option1();
        option2 = question.getImage_option2();
        option3 = question.getImage_option3();
        option4 = question.getImage_option4();
        answer = question.getAnswer();


        if (player != null) {
            player.stop();
            player = null;
        }
        sound_question = question.getSound_question();
        uriPath = "android.resource://" + getPackageName() + "/" + getResources().getIdentifier(sound_question + "_" + "video", "raw", getPackageName());

        get_player(sound_question);

        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        Collections.shuffle(options);

        answer1.setImageResource(getResources().getIdentifier(options.get(0), "drawable", getPackageName()));
        answer2.setImageResource(getResources().getIdentifier(options.get(1), "drawable", getPackageName()));
        answer3.setImageResource(getResources().getIdentifier(options.get(2), "drawable", getPackageName()));
        answer4.setImageResource(getResources().getIdentifier(options.get(3), "drawable", getPackageName()));

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play.setImageResource(R.drawable.play);
            }
        });
        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_button.setEnabled(true);
                submit_button.setAlpha(1f);
                if(!player.isPlaying()) {
                    player.start();
                    play.setImageResource(R.drawable.pause);

                }
                else{
                    player.pause();
                    play.setImageResource(R.drawable.play);
                }

            }
        });
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer2.getImageAlpha() == 50 && answer3.getImageAlpha() == 50 && answer4.getImageAlpha() == 50){
                    answer2.setImageAlpha(511);
                    answer3.setImageAlpha(511);
                    answer4.setImageAlpha(511);
                    USER_ANSWER = null;

                }
                else{
                    answer1.setImageAlpha(511);
                    answer2.setImageAlpha(50);
                    answer3.setImageAlpha(50);
                    answer4.setImageAlpha(50);
                    USER_ANSWER = options.get(0);
                }

            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1.getImageAlpha() == 50 && answer3.getImageAlpha() == 50 && answer4.getImageAlpha() == 50){
                    answer1.setImageAlpha(511);
                    answer3.setImageAlpha(511);
                    answer4.setImageAlpha(511);
                    USER_ANSWER = null;

                }
                else{
                    answer1.setImageAlpha(50);
                    answer2.setImageAlpha(511);
                    answer3.setImageAlpha(50);
                    answer4.setImageAlpha(50);
                    USER_ANSWER = options.get(1);
                }


            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1.getImageAlpha() == 50 && answer2.getImageAlpha() == 50 && answer4.getImageAlpha() == 50){
                    answer1.setImageAlpha(511);
                    answer2.setImageAlpha(511);
                    answer4.setImageAlpha(511);
                    USER_ANSWER = null;

                }
                else{
                    answer1.setImageAlpha(50);
                    answer2.setImageAlpha(50);
                    answer3.setImageAlpha(511);
                    answer4.setImageAlpha(50);
                    USER_ANSWER = options.get(2);
                }


            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1.getImageAlpha() == 50 && answer2.getImageAlpha() == 50 && answer3.getImageAlpha() == 50){
                    answer1.setImageAlpha(511);
                    answer2.setImageAlpha(511);
                    answer3.setImageAlpha(511);
                    USER_ANSWER = null;

                }
                else{

                    answer1.setImageAlpha(50);
                    answer2.setImageAlpha(50);
                    answer3.setImageAlpha(50);
                    answer4.setImageAlpha(511);
                    USER_ANSWER = options.get(3);
                }

            }
        });

    }


    private void get_player(String nameOfFile){
        if(player == null) {
            player = MediaPlayer.create(this, getResources().getIdentifier(nameOfFile, "raw", getPackageName()));
        }
    }

    public void submit(View v){
        submit_button.setEnabled(false);
        submit_button.setAlpha(0.5f);
        play.setImageResource(R.drawable.play);

        if (player != null) {
            player.stop();
            if (QUESTION_COUNTER < QUESTION_SIZE) {
                if (answer.equals(USER_ANSWER)) {
                    correct_text.setText(String.valueOf(++CORRECT_COUNTER));
                    dialog.showDialog(this, uriPath, true);
                }
                else {
                    dialog.showDialog(this, uriPath, false);
                }
                player = null;
                USER_ANSWER = null;
                question_text.setText(String.valueOf(++QUESTION_COUNTER));

                Question question = questionList.get(QUESTION_COUNTER - 1);
                answer = question.getAnswer();
                option1 = question.getImage_option1();
                option2 = question.getImage_option2();
                option3 = question.getImage_option3();
                option4 = question.getImage_option4();
                sound_question = question.getSound_question();
                options.set(3, option1);
                options.set(0, option2);
                options.set(1, option3);
                options.set(2, option4);
                Collections.shuffle(options);

                answer1.setImageAlpha(255);
                answer2.setImageAlpha(255);
                answer3.setImageAlpha(255);
                answer4.setImageAlpha(255);
                answer1.setImageResource(getResources().getIdentifier(options.get(0), "drawable", getPackageName()));
                answer2.setImageResource(getResources().getIdentifier(options.get(1), "drawable", getPackageName()));
                answer3.setImageResource(getResources().getIdentifier(options.get(2), "drawable", getPackageName()));
                answer4.setImageResource(getResources().getIdentifier(options.get(3), "drawable", getPackageName()));
                uriPath = "android.resource://" + getPackageName() + "/" + getResources().getIdentifier(sound_question + "_" + "video", "raw", getPackageName());

                get_player(sound_question);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        play.setImageResource(R.drawable.play);
                    }
                });
            } else {
                if (answer.equals(USER_ANSWER)) {
                    correct_text.setText(String.valueOf(++CORRECT_COUNTER));
                    dialog.showDialog(this, uriPath, true);
                }
                else {
                    dialog.showDialog(this, uriPath, false);
                }

                player.stop();
                Log.d("DISMISS", String.valueOf(dialog.isDismiss()));
                if(dialog.isDismiss()){
                    Intent intent = new Intent(this, Score.class);
                    intent.putExtra("SCORE", String.valueOf(CORRECT_COUNTER));
                    startActivity(intent);
                }


            }
        }
        dialog.setDismiss(false);
    }



}
