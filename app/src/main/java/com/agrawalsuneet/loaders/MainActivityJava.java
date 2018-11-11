package com.agrawalsuneet.loaders;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;

import com.agrawalsuneet.loaderspack.loaders.GaugeLoader;
import com.agrawalsuneet.loaderspack.loaders.SearchLoader;

/**
 * Created by suneet on 10/31/17.
 */

public class MainActivityJava extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_clock);


        /*//Clock Loader
        ClockLoader clockLoader = new ClockLoader(this);
        clockLoader.setOuterCircleBorderWidth(8.0f);
        clockLoader.setBigCircleRadius(150.0f);
        clockLoader.setMinuteHandLength(120.0f);
        clockLoader.setHourHandLength(80.0f);
        clockLoader.setInnerCircleRadius(15.0f);

        clockLoader.setOuterCircleBorderColor(ContextCompat.getColor(this, R.color.colorAccent));
        clockLoader.setBigCircleColor(ContextCompat.getColor(this, R.color.colorPrimary));
        clockLoader.setInnerCircleColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        clockLoader.setHourHandColor(ContextCompat.getColor(this, R.color.colorAccent));
        clockLoader.setMinuteHandColor(ContextCompat.getColor(this, R.color.colorAccent));

        clockLoader.setAnimSpeedMultiplier(2.0f);

        container.addView(clockLoader);

        //RotatingCircularSticksLoader
        RotatingCircularSticksLoader loader = new RotatingCircularSticksLoader(this,
                16, 100f, 50f,
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, android.R.color.white));

        loader.setAnimDuration(5000);
        container.addView(loader);


        //CircularSticksLoader
        *//*CircularSticksLoader loader = new CircularSticksLoader(this, 16,
                200f, 100f,
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, android.R.color.white));

        loader.setShowRunningShadow(true);
        loader.setFirstShadowColor(ContextCompat.getColor(this, R.color.green));
        loader.setSecondShadowColor(ContextCompat.getColor(this, R.color.yellow));
        loader.setAnimDuration(100);

        container.addView(loader);*//*


        //MultipleRippleLoader
        MultipleRippleLoader multipleRippleLoader = new MultipleRippleLoader(this,
                40,
                ContextCompat.getColor(this, R.color.blue),
                2);

        multipleRippleLoader.setFromAlpha(0.9f);
        multipleRippleLoader.setToAlpha(0.2f);
        multipleRippleLoader.setAnimationDuration(2000);
        multipleRippleLoader.setInterpolator(new LinearInterpolator());


        container.addView(multipleRippleLoader);

        CurvesLoader curvesLoader = new CurvesLoader(
                this,
                4,
                100,
                10,
                10,
                160.0f,
                ContextCompat.getColor(this, R.color.blue_selected));


        curvesLoader.setAnimDuration(1000);
        curvesLoader.setInterpolator(new LinearInterpolator());

        container.addView(curvesLoader);


        RingAndCircleLoader ringAndCircleLoader = new RingAndCircleLoader(
                this,
                20,
                100,
                10,
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.blue_delfault));

        ringAndCircleLoader.setAnimDuration(1000);

        container.addView(ringAndCircleLoader);


        ArcProgressLoader arcProgressLoader = new ArcProgressLoader(this,
                120, 20,
                10.0f, 180.0f,
                getResources().getIntArray(R.array.colors_rgb));

        container.addView(arcProgressLoader);*/


        /*FidgetLoader fidgetLoader = new FidgetLoader(this,
                20,
                ContextCompat.getColor(this, R.color.blue_selected),
                ContextCompat.getColor(this, R.color.amber));

        fidgetLoader.setAnimDuration(3000);
        fidgetLoader.setNoOfRotation(1);
        fidgetLoader.setInterpolator(new BounceInterpolator());

        container.addView(fidgetLoader);*/

        /*WifiLoader wifiLoader = new WifiLoader(this,
                20,
                ContextCompat.getColor(this, R.color.blue_selected));

        wifiLoader.setIncrementalAngle(1.2f);

        container.addView(wifiLoader);*/

        /*PulseLoader pulseLoader = new PulseLoader(this,
                15,
                400,
                4.0f,
                15.0f,
                ContextCompat.getColor(this, R.color.blue_selected));

        container.addView(pulseLoader);*/


        /*GaugeLoader gaugeLoader = new GaugeLoader(this, 150, 80,
                20, 50,
                ContextCompat.getColor(this, R.color.blue_delfault),
                ContextCompat.getColor(this, R.color.blue_selected),
                ContextCompat.getColor(this, android.R.color.black), true);

        container.addView(gaugeLoader);

        //or you can provide custom rotate animation for needle*/

        /*final GaugeLoader gaugeLoader = new GaugeLoader(this, 150, 80,
                20, 50,
                ContextCompat.getColor(this, R.color.blue_delfault),
                ContextCompat.getColor(this, R.color.blue_selected),
                ContextCompat.getColor(this, android.R.color.black), false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RotateAnimation anim = new RotateAnimation(270.0f, 450.0f,
                        gaugeLoader.getNeedlePivotX(), gaugeLoader.getNeedlePivotY());
                anim.setDuration(1000);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);
                gaugeLoader.startLoading(anim);
            }
        }, 500);

        //delay because view will not be initialized

        container.addView(gaugeLoader);*/


        SearchLoader searchLoader = new SearchLoader(this,
                60, 20, 80,
                ContextCompat.getColor(this, R.color.red),
                500, 500, true);

        container.addView(searchLoader);

    }


}
