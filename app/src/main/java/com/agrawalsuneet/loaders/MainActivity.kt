package com.agrawalsuneet.loaders

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.agrawalsuneet.loaderspack.loaders.ClockLoader
import com.agrawalsuneet.loaderspack.loaders.RippleLoader
import com.agrawalsuneet.loaderspack.loaders.RotatingCircularSticksLoader

class MainActivity : AppCompatActivity() {

    lateinit var containerLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_circular)

        containerLayout = findViewById(R.id.container)

        //initClockLoader();
        //initRippleLoader()
        initRotatingCircularSticksLoader()

    }

    private fun initRotatingCircularSticksLoader() {
        val loader = RotatingCircularSticksLoader(this, 16, 100f, 50f,
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, android.R.color.white))
                .apply {
                    animDuration = 5000
                }

        containerLayout.addView(loader)
    }

    private fun initRippleLoader() {
        val ripple = RippleLoader(baseContext)/*.apply {
            circleInitialRadius = 80
            circleColor = resources.getColor(R.color.black)
            fromAlpha = 1.0f
            toAlpha = 0f
            animationDuration = 1000
            interpolator = DecelerateInterpolator()
        }*/

        containerLayout.addView(ripple)
    }

    private fun initClockLoader() {
        var clock = ClockLoader(this)
        /*.apply {
            outerCircleBorderWidth = 10.0f
            bigCircleRadius = 200.0f
            minuteHandLength = 150.0f
            hourHandLength = 100.0f
            innerCircleRadius = 30.0f

            outerCircleBorderColor = ContextCompat.getColor(context, R.color.colorAccent)
            bigCircleColor = ContextCompat.getColor(context, R.color.colorPrimary)
            innerCircleColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
            hourHandColor = ContextCompat.getColor(context, R.color.colorAccent)
            minuteHandColor = ContextCompat.getColor(context, R.color.colorAccent)

            animSpeedMultiplier = 2.0f
        }*/


        containerLayout.addView(clock)
    }
}
