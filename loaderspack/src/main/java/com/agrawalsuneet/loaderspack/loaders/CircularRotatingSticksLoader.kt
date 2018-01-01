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
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

/**
 * Created by suneet on 1/1/18.
 */
class CircularRotatingSticksLoader : LinearLayout, LoaderContract {

    var noOfSticks: Int = 50

    var outerCircleRadius: Float = 200.0f
    var innerCircleRadius: Float = 100.0f

    var sticksColor: Int = resources.getColor(R.color.grey)
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

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularRotatingSticksLoader, 0, 0)

        this.noOfSticks = typedArray
                .getInteger(R.styleable.CircularRotatingSticksLoader_rotatingstick_noOfSticks, 50)

        this.outerCircleRadius = typedArray
                .getDimension(R.styleable.CircularRotatingSticksLoader_rotatingstick_outerCircleRadius, 200.0f)
        this.innerCircleRadius = typedArray
                .getDimension(R.styleable.CircularRotatingSticksLoader_rotatingstick_innerCircleRadius, 100.0f)


        this.sticksColor = typedArray
                .getColor(R.styleable.CircularRotatingSticksLoader_rotatingstick_stickColor, resources.getColor(R.color.grey))
        this.viewBackgroundColor = typedArray
                .getColor(R.styleable.CircularRotatingSticksLoader_rotatingstick_viewBackgroundColor, resources.getColor(android.R.color.white))

        this.animDuration = typedArray
                .getInteger(R.styleable.CircularRotatingSticksLoader_rotatingstick_animDuration, 5000)

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