package com.example.lottiesdemo;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieRelativePointValueCallback;
import com.example.lottiesdemo.lottie.BackgroundColorView;
import com.example.lottiesdemo.lottie.InterceptingFrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;

/**
 * @Author : cuiqiang
 * @DATE : 2019/4/11 14:24
 * @Description :
 */
public class BullseyeActivity extends AppCompatActivity {


    private LottieAnimationView mAnimationView;
    private InterceptingFrameLayout mContainerView;
    private BackgroundColorView mTargetView;
    private float totalDx = 0f;
    private float totalDy = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bullseye);
        initView();
        initData();

    }

    private void initView() {
        mAnimationView = findViewById(R.id.animation_view);
        mContainerView = findViewById(R.id.container_view);
        mTargetView = findViewById(R.id.target_view);
    }


    private void initData() {
        final LottieRelativePointValueCallback largeValueCallback
                = new LottieRelativePointValueCallback(new PointF(0f, 0f));

        mAnimationView.addValueCallback(new KeyPath("First"), LottieProperty.TRANSFORM_POSITION,
                largeValueCallback);

        final LottieRelativePointValueCallback mediumValueCallback
                = new LottieRelativePointValueCallback(new PointF(0f, 0f));

        mAnimationView.addValueCallback(new KeyPath("Fourth"), LottieProperty.TRANSFORM_POSITION,
                mediumValueCallback);

        final LottieRelativePointValueCallback smallValueCallback
                = new LottieRelativePointValueCallback(new PointF(0f, 0f));

        mAnimationView.addValueCallback(new KeyPath("Seventh"), LottieProperty.TRANSFORM_POSITION,
                smallValueCallback);

        //mContainerView的点击拖动事件委托给ViewDragHelper，ViewDragHelper中对mTargetView做相应处理
        ViewDragHelper viewDragHelper = ViewDragHelper.create(mContainerView, new ViewDragHelper.Callback() {

            /**
             * 捕获拖动的这个View
             */
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return child == mTargetView;
            }

            /**
             * 拖动的这个View的位置发生变化
             *
             * @param changedView  当前拖动的这个View
             * @param left         距离左边的距离
             * @param top          距离右边的距离
             * @param dx           x轴的变化量
             * @param dy           y轴的变化量
             */
            public void onViewPositionChanged(@NonNull View changedView, int left, int top, @Px int dx,
                                              @Px int dy) {
                totalDx += dx;
                totalDy += dy;
                //控制的是圆心然后触发重新绘制,就是位置的距离转换一下设置给新的圆心
                //这个触摸绑定交互可能不具有参考意义，因为动画没有特别复杂，直接canvas画三个圆也能达到同样的效果
                smallValueCallback.setValue(getPoint(totalDx, totalDy, 1.2f));
                mediumValueCallback.setValue(getPoint(totalDx, totalDy, 1f));
                largeValueCallback.setValue(getPoint(totalDx, totalDy, 0.75f));
            }

            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                return left;
            }

            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                return top;
            }

        });
        mContainerView.setViewDragHelper(viewDragHelper);
    }

    private PointF getPoint(float dx, float dy, float factor) {
        return new PointF(dx * factor, dy * factor);
    }


}
