package com.rob.monopoly;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashScreenMain extends AppCompatActivity {

    LinearLayout l1,l2;
    Animation uptodown,downtoup;
    private static int WELCOME_TIMEOUT = 10000;
    MediaPlayer introSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen_main);

        introSong = MediaPlayer.create(SplashScreenMain.this,R.raw.intro);
        introSong.start();

        TextView tx = (TextView)findViewById(R.id.textview1);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/rickmorty.ttf");
        tx.setTypeface(custom_font);

        TextView tx2 = (TextView)findViewById(R.id.textview2);
        Typeface customfont = Typeface.createFromAsset(getAssets(),  "fonts/Starjedi.ttf");
        tx2.setTypeface(customfont);

        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenMain.this, MainMenuGame.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, WELCOME_TIMEOUT);
    }

    @Override
    protected void onPause(){
        super.onPause();
        introSong.release();
        finish();
    }
}