package com.agrawalsuneet.loaders

import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.agrawalsuneet.loaderspack.loaders.*
import kotlinx.android.synthetic.main.main_curves.*

class MainActivity : AppCompatActivity() {

    lateinit var containerLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_curves)

        supportActionBar?.setTitle("CurvesLoader")

        containerLayout = findViewById(R.id.container)

        //initClockLoader();
        //initRippleLoader()
        //initRotatingCircularSticksLoader()
        //initCircularSticksLoader()

        //initMultipleRippleLoader()
        //initCurvesLoader()
        //initRingAndCircleLoader()
        //initArcProgressLoader()

        //initFidgetLoader()
        //initWifiLoader()
        //initPulseLoader()

        //initGaugeLoader()
        //initSearchLoader()
    }

    private fun initSearchLoader() {
        val searchLoader = SearchLoader(this,
                60, 20, 80,
                ContextCompat.getColor(this, R.color.red),
                500, 500, true)

        containerLayout.addView(searchLoader)
    }

    private fun initGaugeLoader() {
        /*val gaugeLoader = GaugeLoader(this, 150, 80,
                20, 50,
                ContextCompat.getColor(this, R.color.blue_delfault),
                ContextCompat.getColor(this, R.color.blue_selected),
                ContextCompat.getColor(this, android.R.color.black), true)*/

        val gaugeLoader = GaugeLoader(this, 150, 80,
                20, 50,
                ContextCompat.getColor(this, R.color.blue_delfault),
                ContextCompat.getColor(this, R.color.blue_selected),
                ContextCompat.getColor(this, android.R.color.black), false)

        Handler().postDelayed({
            val anim = RotateAnimation(270.0f, 450.0f, gaugeLoader.needlePivotX, gaugeLoader.needlePivotY)
            anim.duration = 1000
            anim.interpolator = LinearInterpolator()
            anim.repeatMode = Animation.REVERSE
            anim.repeatCount = Animation.INFINITE
            gaugeLoader.startLoading(anim)
        }, 500)

        containerLayout.addView(gaugeLoader)
    }

    private fun initPulseLoader() {
        val pulseLoader = PulseLoader(this,
                15,
                400,
                4.0f,
                15.0f,
                ContextCompat.getColor(this, R.color.blue_selected))

        containerLayout.addView(pulseLoader)
    }

    private fun initWifiLoader() {
        val wifiLoader = WifiLoader(this,
                20,
                ContextCompat.getColor(this, R.color.blue_selected))
                .apply {
                    incrementalAngle = 2.0f
                }

        containerLayout.addView(wifiLoader)
    }

    private fun initFidgetLoader() {
        val fidgetLoader = FidgetLoader(this,
                20,
                ContextCompat.getColor(this, R.color.blue_selected),
                ContextCompat.getColor(this, R.color.amber))
                .apply {
                    animDuration = 3000
                    noOfRotation = 1
                    interpolator = BounceInterpolator()
                }

        containerLayout.addView(fidgetLoader)
    }

    private fun initArcProgressLoader() {
        val arcProgressLoader = ArcProgressLoader(this,
                120, 20,
                10.0f, 180.0f,
                resources.getIntArray(R.array.colors_rgb))

        containerLayout.addView(arcProgressLoader)

    }

    private fun initRingAndCircleLoader() {
        val ringAndCircleLoader = RingAndCircleLoader(
                this,
                20,
                100,
                10,
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.blue_delfault))
                .apply {
                    animDuration = 1000
                }

        containerLayout.addView(ringAndCircleLoader)
    }

    private fun initCurvesLoader() {
        val curvesLoader = CurvesLoader(
                this,
                4,
                100,
                10,
                10,
                160.0f,
                ContextCompat.getColor(this, R.color.blue_selected))
                .apply {
                    animDuration = 1000
                    interpolator = LinearInterpolator()
                }

        containerLayout.addView(curvesLoader)
    }

    private fun initMultipleRippleLoader() {
        val multipleRippleLoader = MultipleRippleLoader(this,
                40,
                ContextCompat.getColor(this, R.color.blue),
                2)
                .apply {
                    fromAlpha = 0.9f
                    toAlpha = 0.2f
                    animationDuration = 2000
                    interpolator = LinearInterpolator()
                }

        containerLayout.addView(multipleRippleLoader)
    }

    private fun initCircularSticksLoader() {
        val loader = CircularSticksLoader(this, 16, 200f, 100f,
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, android.R.color.white))
                .apply {
                    showRunningShadow = true
                    firstShadowColor = ContextCompat.getColor(context, R.color.green)
                    secondShadowColor = ContextCompat.getColor(context, R.color.yellow)
                    animDuration = 100
                }

        containerLayout.addView(loader)
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
            wifiColor = resources.getColor(R.color.black)
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
