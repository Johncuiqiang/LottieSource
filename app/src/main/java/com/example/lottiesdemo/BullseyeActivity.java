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


         ViewDragHelper viewDragHelper = ViewDragHelper.create(mContainerView, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return child == mTargetView;
            }

            public void onViewPositionChanged(@NonNull View changedView, int left, int top, @Px int dx,
                                              @Px int dy) {
                totalDx += dx;
                totalDy += dy;
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
