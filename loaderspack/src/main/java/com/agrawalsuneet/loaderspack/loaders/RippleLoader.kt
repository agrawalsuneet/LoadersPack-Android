package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.AnimationUtils
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.CircleView
import com.agrawalsuneet.loaderspack.contracts.RippleAbstractView

/**
 * Created by suneet on 11/15/17.
 */
open class RippleLoader : RippleAbstractView {


    private lateinit var circleView: CircleView

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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleLoader, 0, 0)

        circleInitialRadius = typedArray
                .getDimensionPixelSize(R.styleable.RippleLoader_ripple_circleInitialRadius, 40)

        circleColor = typedArray.getColor(R.styleable.RippleLoader_ripple_circleColor,
                resources.getColor(android.R.color.holo_red_dark))


        fromAlpha = typedArray.getFloat(R.styleable.RippleLoader_ripple_fromAlpha, 0.9f)
        toAlpha = typedArray.getFloat(R.styleable.RippleLoader_ripple_toAplha, 0.01f)

        animationDuration = typedArray.getInteger(R.styleable.RippleLoader_ripple_animDuration, 2000)

        interpolator = AnimationUtils.loadInterpolator(context,
                typedArray.getResourceId(R.styleable.RippleLoader_ripple_interpolator,
                        android.R.anim.decelerate_interpolator))

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(4 * circleInitialRadius, 4 * circleInitialRadius)
    }


    override fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER
        circleView = CircleView(context, circleInitialRadius, circleColor)

        addView(circleView)

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startLoading()

                this@RippleLoader.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun startLoading() {
        var animSet = getAnimSet()
        circleView.startAnimation(animSet)
    }
}