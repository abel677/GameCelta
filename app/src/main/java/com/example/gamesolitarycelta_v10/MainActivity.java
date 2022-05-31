package com.example.gamesolitarycelta_v10;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText txt_player;
    private Button btn_play;
    private ImageView image_sound;
    private TextView label_sound;

    private MediaPlayer sound_click;
    private MediaPlayer sound_background;
    private MediaPlayer sound_notPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadComponent();
        loadAnimation();
        loadSound();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(sound_background.isPlaying()){
            sound_background.stop();
            sound_background.release();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sound_background.isPlaying()){
            sound_background.stop();
            sound_background.release();
        }
    }


    private void loadComponent(){
        txt_player = findViewById(R.id.txt_player);
        btn_play = findViewById(R.id.btn_play);
        image_sound = findViewById(R.id.image_sound);
        label_sound = findViewById(R.id.label_sound);
        loadActionButton();

    }
    private void loadAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);
        txt_player.setAnimation(animation);
        btn_play.setAnimation(animation2);
        image_sound.setAnimation(animation2);
        label_sound.setAnimation(animation2);
    }
    private void loadSound(){
        sound_background = MediaPlayer.create(this,R.raw.sound_electric);
        sound_background.setAudioStreamType(AudioManager.STREAM_MUSIC);
        sound_background.start();
        sound_click = MediaPlayer.create(this,R.raw.sound_click);
        sound_notPlayer = MediaPlayer.create(this,R.raw.sound_error);
        sound_notPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }


    @SuppressLint("SetTextI18n")
    private void loadActionButton(){
        btn_play.setOnClickListener(view -> play(txt_player.getText().toString()));

        image_sound.setOnClickListener(view -> {
            if(sound_background.isPlaying()){
                sound_background.pause();
                image_sound.setImageResource(R.drawable.icon_volume_off);
                label_sound.setText("Deactivate");
                label_sound.setTextColor(Color.RED);
            }else{
                sound_background.start();
                image_sound.setImageResource(R.drawable.icon_volume);
                label_sound.setText("Activate");
                label_sound.setTextColor(Color.WHITE);
            }
        });
    }
    Context context = this;
    private void play(String player) {
        if(!player.isEmpty()){
            sound_click.start();
            Intent i = new Intent(this,GameCeltaActivity.class);
            i.putExtra("player",player);
            startActivity(i);
        }else{
            sound_notPlayer.start();
            new Dialog_player(context);
            txt_player.requestFocus();

        }
    }
}