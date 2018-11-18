package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.CircleView
import com.agrawalsuneet.loaderspack.contracts.RippleAbstractView

/**
 * Created by suneet on 04/28/18.
 */
class MultipleRippleLoader : RippleAbstractView {

    var noOfRipples: Int = 3

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

    constructor(context: Context, circleInitialRadius: Int, circleColor: Int, noOfRipples: Int) : super(context) {
        this.circleInitialRadius = circleInitialRadius
        this.circleColor = circleColor
        this.noOfRipples = noOfRipples
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultipleRippleLoader, 0, 0)

        circleInitialRadius = typedArray
                .getDimensionPixelSize(R.styleable.MultipleRippleLoader_multipleripple_circleInitialRadius, 40)

        circleColor = typedArray.getColor(R.styleable.MultipleRippleLoader_multipleripple_circleColor,
                resources.getColor(android.R.color.holo_red_dark))

        noOfRipples = typedArray.getInteger(R.styleable.MultipleRippleLoader_multipleripple_noOfRipples, 3)


        fromAlpha = typedArray.getFloat(R.styleable.MultipleRippleLoader_multipleripple_fromAlpha, 0.9f)
        toAlpha = typedArray.getFloat(R.styleable.MultipleRippleLoader_multipleripple_toAplha, 0.01f)

        animationDuration = typedArray.getInteger(R.styleable.MultipleRippleLoader_multipleripple_animDuration, 2000)

        interpolator = AnimationUtils.loadInterpolator(context,
                typedArray.getResourceId(R.styleable.MultipleRippleLoader_multipleripple_interpolator,
                        android.R.anim.decelerate_interpolator))

        typedArray.recycle()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)

        if(visibility == View.VISIBLE){
            initView()
        }
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

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startLoading()

                this@MultipleRippleLoader.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun startLoading() {
        for (i in 0 until noOfRipples) {

            Handler().postDelayed({
                val animSet = getAnimSet()
                rippleCircles[i]!!.startAnimation(animSet)

            }, ((i * animationDuration) / noOfRipples).toLong())
        }
    }


}