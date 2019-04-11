package com.example.lottiesdemo;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.example.lottiesdemo.lottie.LottieFontViewGroup;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author : cuiqiang
 * @DATE : 2019/4/11 14:24
 * @Description :
 */
public class PinyinActivity extends AppCompatActivity {

    private LottieFontViewGroup mFontView;
    private ScrollView mScrollView;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener(){

        @Override
        public void onGlobalLayout() {
            mScrollView.fullScroll(View.FOCUS_DOWN);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typography_demo);
        initView();

    }

    private void initView() {
        mScrollView = findViewById(R.id.scroll_view);
        mFontView = findViewById(R.id.font_view);
        mFontView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }


    @Override
    protected void onDestroy() {
        mFontView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
        super.onDestroy();
    }
}
