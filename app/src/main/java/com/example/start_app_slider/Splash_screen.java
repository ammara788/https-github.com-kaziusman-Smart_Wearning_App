package com.example.start_app_slider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class Splash_screen extends AppCompatActivity {
    ImageView im;
    private static final String TAG = MainActivity.class.getSimpleName();

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        im = (ImageView) findViewById(R.id.im);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash);
        im.startAnimation(animation);



        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent startActivityIntent = new Intent(Splash_screen.this,MainScreen.class);
                startActivity(startActivityIntent);
                Splash_screen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);



    }
}
