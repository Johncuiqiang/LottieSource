package com.example.lottiesdemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LottieAnimationView mAnimationView;
    private ValueAnimator animator;
    private Button mBtnOne;
    private Button mBtnTwo;
    private Button mBtnThree;
    private Button mBtnFour;
    private Button mBtnFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mAnimationView = findViewById(R.id.animation_view);
        mBtnOne = findViewById(R.id.btn_one);
        mBtnTwo = findViewById(R.id.btn_two);
        mBtnThree = findViewById(R.id.btn_three);
        mBtnFour = findViewById(R.id.btn_four);
        mBtnFive = findViewById(R.id.btn_five);
    }

    private void initData() {
        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play("lottiefiles.com - ATM.json");
            }
        });
        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play("MotionCorpse-Jrcanest.json");
            }
        });
        mBtnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play("lottiefiles.com - Im Thirsty.json");
            }
        });
        mBtnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play("PinJump.json");
            }
        });
        mBtnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play("Hosts.json");
            }
        });
    }

    private void play(String name){
        mAnimationView.cancelAnimation();
        mAnimationView.setAnimation(name);
        mAnimationView.loop(false);
        mAnimationView.playAnimation();

    }
}
