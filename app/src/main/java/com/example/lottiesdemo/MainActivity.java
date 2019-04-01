package com.example.lottiesdemo;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import java.net.CacheRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.Cache;

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
              //  playValueAnimator("lottiefiles.com - ATM.json");
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
        // 取消播放
        mAnimationView.cancelAnimation();
        // 是否循环播放
        mAnimationView.loop(false);
        // 设置播放速率，例如：2代表播放速率是不设置时的二倍
        //mAnimationView.setSpeed(2f);
        // 暂停播放
        mAnimationView.pauseAnimation();
        // 设置播放进度
        //mAnimationView.setProgress(0.5f);
        // 判断是否正在播放
       // mAnimationView.isAnimating();
        mAnimationView.setAnimation(name);
        mAnimationView.playAnimation();
    }

    /**
     * 自定义播放动画和时长
     */
    private void playValueAnimator(String name){
        mAnimationView.setAnimation(name);
        ValueAnimator valueAnimator = ValueAnimator
                .ofFloat(0.2f,1f)
                .setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimationView.setProgress((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

}
