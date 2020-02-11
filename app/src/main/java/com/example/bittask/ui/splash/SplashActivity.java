package com.example.bittask.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.bittask.R;
import com.example.bittask.ui.onbording.OnBoardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.Looper.getMainLooper;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.ivSplash) ImageView ivSplash;

    private Animation animSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        // Load the animation like this
        animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.swipe);

        // Start the animation like this
        ivSplash.startAnimation(animSlide);

        // show splash and disappear after 1 sec delay
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Wait For Splash Screen
                openOnBoardActivity();
            }
        }, 3000);
    }

    public void openOnBoardActivity() {
        Intent intent = new Intent(this, OnBoardActivity.class);
        startActivity(intent);
        finish();
    }
}
