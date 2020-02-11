package com.example.bittask.ui.onbording;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bittask.R;
import com.example.bittask.ui.home.HomeActivity;
import com.example.bittask.ui.onbording.adap.SliderAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnBoardActivity extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager mViewPager;

    @BindView(R.id.tab_indicator) TabLayout tabIndicator;

    @BindView(R.id.dotsLayout) LinearLayout mDotsLayout;

    @BindView(R.id.btn_get_started)
    Button btnGetStarted;

    Animation btnAnim ;

    private TextView[] mDots;

    SliderAdapter sliderAdapter;

    private int customPosition = 0;
    private Context mContext;
    private int sliderSize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_on_board);

        ButterKnife.bind(this);

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);

        mContext = this;

        sliderAdapter = new SliderAdapter(this);

        mViewPager.setAdapter(sliderAdapter);

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(mViewPager);

        prepareDots( customPosition++, sliderSize );

        mViewPager.addOnPageChangeListener(viewListener);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnGetStarted.setVisibility(View.VISIBLE);
        // tabIndicator.setVisibility(View.INVISIBLE);
        // setup animation
        btnGetStarted.setAnimation(btnAnim);

    }

    private void addDotsIndicator(){

        mDots = new TextView[3];

        for (int i = 0; i < mDots.length; ++i){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.transparent));

            mDotsLayout.addView(mDots[i]);
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            Log.d("position", position + "");

            Log.d("customPosition", customPosition + "");

            Log.d("sliderSize", sliderSize + "");

            prepareDots(customPosition++, sliderSize);

            if (customPosition == sliderSize){
                loaddLastScreen();
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void prepareDots(int currentSlidePosition, int sliderSize){

        Log.d("sliderSizeDots", sliderSize + "");

        if (mDotsLayout.getChildCount() > 0){
            mDotsLayout.removeAllViews();
        }

        ImageView dots[] = new ImageView[sliderSize];

        for (int i = 0; i < sliderSize; ++i){

            dots[i] = new ImageView(mContext);

            Log.d("currentSlidePosition", i + ", "  + currentSlidePosition + "");

            if ( i == currentSlidePosition ){
                dots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.circle_select));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.circle_no_select));
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(4, 0, 4, 0);

            mDotsLayout.addView(dots[i], layoutParams);

        }
    }

    public void openHomeActivity() {

        // Wait For OnBoard Screen
        Intent intent = new Intent(mContext, HomeActivity.class);
        startActivity(intent);
        finish();

        /*// wait for last Image And Go To Home Screen
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3000);*/

    }

}
