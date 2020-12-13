package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    private LottieAnimationView mAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAnim = findViewById(R.id.anim_splash);

        //TODO: check internet and request to net
        mAnim.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.newIntent(SplashActivity.this));
                finish();
            }
        }, 3000);
    }


}