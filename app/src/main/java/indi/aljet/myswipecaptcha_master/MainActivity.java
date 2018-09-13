package indi.aljet.myswipecaptcha_master;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import indi.aljet.myswipecaptcha_master.captchalib.SwipeCaptchaView;

public class MainActivity extends AppCompatActivity {

    SwipeCaptchaView mSwipeCaptchaView;
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeCaptchaView = findViewById(R.id.swipeCaptchaView);
        mSeekBar = findViewById(R.id.dragBar);
        findViewById(R.id.btnChange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeCaptchaView.createCaptcha();
                mSeekBar.setEnabled(true);
                mSeekBar.setProgress(0);

            }
        });

        mSwipeCaptchaView.setOnCaptchaMatchCallback(new SwipeCaptchaView.OnCaptchaMatchCallback() {
            @Override
            public void matchSuccess(SwipeCaptchaView swipeCaptchaView) {
                Toast.makeText(MainActivity.this, "恭喜你啊 验证成功 可以搞事情了", Toast.LENGTH_SHORT).show();
                //swipeCaptcha.createCaptcha();
                mSeekBar.setEnabled(false);
            }

            @Override
            public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
                Log.d("zxt", "matchFailed() called with: swipeCaptchaView = [" + swipeCaptchaView + "]");
                Toast.makeText(MainActivity.this, "你有80%的可能是机器人，现在走还来得及", Toast.LENGTH_SHORT).show();
                swipeCaptchaView.resetCaptcha();
                mSeekBar.setProgress(0);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mSwipeCaptchaView.setCurrentSwipeValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mSeekBar.setMax(mSwipeCaptchaView.getMaxSwipeValue());

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("zxt", "onStopTrackingTouch() called with: seekBar = [" + seekBar + "]");
                mSwipeCaptchaView.matchCaptcha();
            }
        });

        //测试从网络加载图片是否ok
        Glide.with(this)
                .load("http://www.investide.cn/data/edata/image/20151201/20151201180507_281.jpg")
                .into(mSwipeCaptchaView);


    }
}
