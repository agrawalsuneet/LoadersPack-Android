package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.widget.RelativeLayout
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.CircleView

class MultipleRippleLoader : RippleLoader {

    var noOfRipples: Int = 3

    var rippleDelay: Int = animationDuration / noOfRipples

    private lateinit var rippleCircles: Array<CircleView?>

    constructor(context: Context) : super(context) {
        initView()
    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {
        circleInitialRadius = 160
        animationDuration = 3000
    }

    override fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER

        val relativeLayout = RelativeLayout(context)

        relativeLayout.gravity = Gravity.CENTER

        val relParam = RelativeLayout.LayoutParams(
                4 * circleInitialRadius,
                4 * circleInitialRadius)


        this.addView(relativeLayout, relParam)

        rippleCircles = arrayOfNulls(noOfRipples)

        for (i in 0 until noOfRipples) {
            val circle = CircleView(context, circleInitialRadius, circleColor)
            relativeLayout.addView(circle)
            circle.visibility = View.INVISIBLE
            rippleCircles[i] = circle
        }

        if (startLoadingDefault) {
            val viewTreeObserver = this.viewTreeObserver
            val loaderView = this

            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    startLoading()

                    val vto = loaderView.viewTreeObserver
                    vto.removeOnGlobalLayoutListener(this)
                }
            })
            startLoadingDefault = false
        }
    }

    override fun startLoading() {

        for (i in 0 until noOfRipples) {
            var animSet = getAnimSet(1, (i * animationDuration) / noOfRipples)
            rippleCircles[i]!!.startAnimation(animSet)

            if (i == noOfRipples - 1) {
                animSet.setAnimationListener(object : Animation.AnimationListener {

                    override fun onAnimationEnd(animation: Animation?) {
                        startLoading()
                    }

                    override fun onAnimationStart(animation: Animation?) {
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                })
            }
        }
    }


}