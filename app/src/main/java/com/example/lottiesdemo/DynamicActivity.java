package com.example.lottiesdemo;

import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author : cuiqiang
 * @DATE : 2019/4/11 14:24
 * @Description :
 */
public class DynamicActivity extends AppCompatActivity {

    private float[] mJmupArray = { 0f, 20f, 50f };
    private int[] mColorArray = { 0xff5a5f, 0x008489, 0xa61d55};
    private LottieAnimationView mAnimationView;
    private View mBtnJumpHeight;
    private View mBtncolor;
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        initView();
        initData();

    }

    private void initView() {
        mAnimationView = findViewById(R.id.animation_view);
        mBtnJumpHeight = findViewById(R.id.btn_jump_height);
        mBtncolor = findViewById(R.id.btn_color);
    }

    private void initData() { ;
        mBtncolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIndex();
                setColor();
            }
        });
        mBtnJumpHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIndex();
                setJumpHeight();
            }
        });
        play("AndroidWave.json");

    }

    private void setColor(){
        KeyPath shirt = new KeyPath("Shirt", "Group 5", "Fill 1");
        KeyPath leftArm = new KeyPath("LeftArmWave", "LeftArm", "Group 6", "Fill 1");
        KeyPath rightArm = new KeyPath("RightArm", "Group 6", "Fill 1");

        //关键path，需要改变的属性
        mAnimationView.addValueCallback(shirt, LottieProperty.COLOR,
                new LottieValueCallback<Integer>(mColorArray[mIndex]){});

        mAnimationView.addValueCallback(leftArm, LottieProperty.COLOR,
                new LottieValueCallback<Integer>(mColorArray[mIndex]){});

        mAnimationView.addValueCallback(rightArm, LottieProperty.COLOR,
                new LottieValueCallback<Integer>(mColorArray[mIndex]){});
    }

    private void setJumpHeight(){
        final PointF pointF = new PointF();
        mAnimationView.addValueCallback(new KeyPath("Body"), LottieProperty.TRANSFORM_POSITION, new SimpleLottieValueCallback<PointF>() {
            @Override
            public PointF getValue(LottieFrameInfo<PointF> frameInfo) {
                float startX = frameInfo.getStartValue().x;
                float startY = frameInfo.getStartValue().y;
                float endY = frameInfo.getEndValue().y;

                if (startY > endY) {
                    startY += mJmupArray[mIndex];
                } else if (endY > startY) {
                    endY += mJmupArray[mIndex];
                }
                pointF.set(startX, MiscUtils.lerp(startY, endY, frameInfo.getInterpolatedKeyframeProgress()));
                return pointF;
            }
        });
    }

    private void play(String name){
        mAnimationView.loop(true);
        mAnimationView.setAnimation(name);
        mAnimationView.playAnimation();
    }

    private void setIndex(){
        mIndex++;
        if (mIndex > 2){
            mIndex = 0;
        }
    }

}
