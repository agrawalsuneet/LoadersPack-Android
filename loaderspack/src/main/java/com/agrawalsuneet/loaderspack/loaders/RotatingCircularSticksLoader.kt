package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.CircularSticksBaseView
import com.agrawalsuneet.loaderspack.contracts.LoaderContract

/**
 * Created by suneet on 1/1/18.
 */
class RotatingCircularSticksLoader : LinearLayout, LoaderContract {

    var noOfSticks: Int = 50

    var outerCircleRadius: Float = 200.0f
    var innerCircleRadius: Float = 100.0f

    var sticksColor: Int = resources.getColor(android.R.color.darker_gray)
    var viewBackgroundColor: Int = resources.getColor(android.R.color.white)

    var animDuration: Int = 5000

    private lateinit var circularSticksBaseView: CircularSticksBaseView

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

    constructor(context: Context, noOfSticks: Int, outerCircleRadius: Float, innerCircleRadius: Float, sticksColor: Int, viewBackgroundColor: Int) : super(context) {
        this.noOfSticks = noOfSticks
        this.outerCircleRadius = outerCircleRadius
        this.innerCircleRadius = innerCircleRadius
        this.sticksColor = sticksColor
        this.viewBackgroundColor = viewBackgroundColor
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotatingCircularSticksLoader, 0, 0)

        this.noOfSticks = typedArray
                .getInteger(R.styleable.RotatingCircularSticksLoader_rotatingsticks_noOfSticks, 50)

        this.outerCircleRadius = typedArray
                .getDimension(R.styleable.RotatingCircularSticksLoader_rotatingsticks_outerCircleRadius, 200.0f)
        this.innerCircleRadius = typedArray
                .getDimension(R.styleable.RotatingCircularSticksLoader_rotatingsticks_innerCircleRadius, 100.0f)


        this.sticksColor = typedArray
                .getColor(R.styleable.RotatingCircularSticksLoader_rotatingsticks_stickColor, resources.getColor(android.R.color.darker_gray))
        this.viewBackgroundColor = typedArray
                .getColor(R.styleable.RotatingCircularSticksLoader_rotatingsticks_viewBackgroundColor, resources.getColor(android.R.color.white))

        this.animDuration = typedArray
                .getInteger(R.styleable.RotatingCircularSticksLoader_rotatingsticks_animDuration, 5000)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(2 * outerCircleRadius.toInt(), 2 * outerCircleRadius.toInt())
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        circularSticksBaseView = CircularSticksBaseView(context, noOfSticks,
                outerCircleRadius, innerCircleRadius,
                sticksColor, viewBackgroundColor)


        addView(circularSticksBaseView)

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

        val rotationAnim = getRotateAnimation()
        circularSticksBaseView.startAnimation(rotationAnim)
    }

    private fun getRotateAnimation(): RotateAnimation {

        val transAnim = RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        transAnim.duration = animDuration.toLong()
        transAnim.fillAfter = true
        transAnim.repeatCount = Animation.INFINITE
        transAnim.repeatMode = Animation.RESTART
        transAnim.interpolator = LinearInterpolator()

        return transAnim
    }
}