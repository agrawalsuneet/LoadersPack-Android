package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.CircleView
import com.agrawalsuneet.loaderspack.contracts.LoaderContract

/**
 * Created by agrawalsuneet on 9/7/18.
 */
class RingAndCircleLoader : LinearLayout, LoaderContract {

    var circleRadius: Int = 30

    var ringRadius: Int = 180
    var ringWidth: Int = 10

    var circleColor: Int = resources.getColor(android.R.color.holo_green_dark)
    var ringColor: Int = resources.getColor(android.R.color.darker_gray)

    var animDuration: Int = 2000

    private lateinit var relativeLayout: RelativeLayout
    private lateinit var ringView: CircleView
    private lateinit var circleView: CircleView

    private var calWidthHeight: Int = 0


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

    constructor(context: Context?, circleRadius: Int, ringRadius: Int, ringWidth: Int, circleColor: Int, ringColor: Int) : super(context) {
        this.circleRadius = circleRadius
        this.ringRadius = ringRadius
        this.ringWidth = ringWidth
        this.circleColor = circleColor
        this.ringColor = ringColor
        initView()
    }


    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RingAndCircleLoader, 0, 0)


        this.circleRadius = typedArray.getDimensionPixelSize(R.styleable.RingAndCircleLoader_ringandcircle_circleRadius, 30)

        this.ringRadius = typedArray.getDimensionPixelSize(R.styleable.RingAndCircleLoader_ringandcircle_ringRadius, 180)
        this.ringWidth = typedArray.getDimensionPixelSize(R.styleable.RingAndCircleLoader_ringandcircle_ringWidth, 10)


        this.circleColor = typedArray.getColor(R.styleable.RingAndCircleLoader_ringandcircle_circleColor,
                resources.getColor(android.R.color.holo_green_dark))

        this.ringColor = typedArray.getColor(R.styleable.RingAndCircleLoader_ringandcircle_ringColor,
                resources.getColor(android.R.color.darker_gray))

        this.animDuration = typedArray.getInt(R.styleable.RingAndCircleLoader_ringandcircle_animDuration, 2000)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (calWidthHeight == 0) {
            calWidthHeight = (2 * ringRadius) + (2 * circleRadius)
        }

        setMeasuredDimension(calWidthHeight, calWidthHeight)
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER_HORIZONTAL

        relativeLayout = RelativeLayout(context)
        relativeLayout.gravity = Gravity.CENTER_HORIZONTAL

        if (calWidthHeight == 0) {
            calWidthHeight = (2 * ringRadius) + (2 * circleRadius)
        }


        ringView = CircleView(context, ringRadius, ringColor, true, ringWidth)

        val widthHeight = (2 * ringRadius) + ringWidth
        val ringParam = RelativeLayout.LayoutParams(widthHeight, widthHeight)
        ringParam.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        relativeLayout.addView(ringView, ringParam)

        circleView = CircleView(context, circleRadius, circleColor)

        val circleParam = RelativeLayout.LayoutParams((2 * circleRadius), (2 * circleRadius))
        circleParam.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)

        relativeLayout.addView(circleView, circleParam)

        val relParam = LinearLayout.LayoutParams(calWidthHeight, calWidthHeight)
        this.addView(relativeLayout, relParam)

        val loaderView = this


        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startLoading()

                val vto = loaderView.viewTreeObserver
                vto.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun startLoading() {
        val rotateAnimation = getRotateAnim()
        circleView.startAnimation(rotateAnimation)
    }

    private fun getRotateAnim(): RotateAnimation {

        val rotateAnimation = RotateAnimation(0.0f, 360.0f,
                (circleRadius).toFloat(), (ringRadius + circleRadius).toFloat())
        rotateAnimation.duration = animDuration.toLong()
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.repeatMode = Animation.RESTART

        return rotateAnimation
    }
}