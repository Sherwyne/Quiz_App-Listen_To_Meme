package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import static android.graphics.Color.rgb;


public class ViewDialog {
    private MediaPlayer videoPlayer;
    private int mVideoPosition;
    private TextView dialogButton, popup_text;
    private LinearLayout linear_popup;
    private VideoView mVideoView;
    private static boolean dismiss;


    public static boolean isDismiss() {
        return dismiss;
    }

    public static void setDismiss(boolean dismiss) {
        ViewDialog.dismiss = dismiss;
    }

    public void showDialog(Activity activity, String question_answer, Boolean isAnsCorrect){
        final Dialog dialog = new Dialog(activity);// add here your class name
        dialog.setContentView(R.layout.correct);//add your own xml with defied with and height of videoview

        mVideoView = dialog.findViewById(R.id.video_view);
        dialogButton = dialog.findViewById(R.id.next_button_popup);
        linear_popup = dialog.findViewById(R.id.linear_body_popup);
        popup_text = dialog.findViewById(R.id.popup_text);


        mVideoView.setVideoURI(Uri.parse(question_answer));
        mVideoView.setZOrderOnTop(true);
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoPlayer = mp;
                videoPlayer.setLooping(true);
                if(mVideoPosition != 0){
                    videoPlayer.seekTo(mVideoPosition);
                    videoPlayer.start();
                }
            }
        });

        if(isAnsCorrect){
            dialogButton.setTextColor(rgb(139,195,74));
            linear_popup.setBackgroundColor(rgb(139,195,74));
            popup_text.setText("CORRECT!");
        }
        else{
            dialogButton.setTextColor(rgb(244,67,54));
            linear_popup.setBackgroundColor(rgb(244,67,54));
            popup_text.setText("INCORRECT!");
        }

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                setDismiss(true);
            }
        });
        dialog.show();
    }

}
