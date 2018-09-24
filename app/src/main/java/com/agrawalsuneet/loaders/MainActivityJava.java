package com.agrawalsuneet.loaders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

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

        /*String str = "asdqwe";
        //str.charAt()

        char a = 'a';
        char c = 'c';
        char z = 'z';

        *//*int av = Character.getNumericValue(a) - 10;
        int cv = Character.getNumericValue(c) - 10;
        int zv = Character.getNumericValue(z) - 10;*//*

        int av = Integer.valueOf(a - 'a');
        int cv = Integer.valueOf(c - 'a');
        int zv = Integer.valueOf(z - 'a');

        Log.d("Suneet Agrawal", Integer.toString(av));
        Log.d("Suneet Agrawal", Integer.toString(cv));
        Log.d("Suneet Agrawal", Integer.toString(zv));

        int result = 0;

        for(int pos = 0; pos< oldVar.length(); pos++){
            int i = Character.getNumericValue(oldVar.charAt(pos)) - 10;
            int j = Character.getNumericValue(newVar.charAt(pos)) - 10;

            result += A[i][j];
        }

        return result;*/

    }


}
