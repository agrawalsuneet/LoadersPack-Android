package com.agrawalsuneet.loaders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.agrawalsuneet.loaderspack.loaders.ClockLoader;

/**
 * Created by suneet on 10/31/17.
 */

public class MainActivityJava extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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
    }
}
