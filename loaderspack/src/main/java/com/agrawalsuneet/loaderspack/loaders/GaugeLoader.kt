package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.*
import android.widget.RelativeLayout
import com.agrawalsuneet.dotsloader.utils.random
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.ArcView
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract
import com.agrawalsuneet.loaderspack.basicviews.NeedleView

class GaugeLoader : RelativeLayout, LoaderContract {

    var rangeIndicatorRadius: Int = 140
    var rangeIndicatorWidth: Int = 100
    var needleWidth: Int = 20
    var needleJointRadius: Int = 45

    var lowerRangeColor: Int = resources.getColor(android.R.color.holo_green_light)
    var higherRangeColor: Int = resources.getColor(android.R.color.holo_green_dark)
    var needleColor: Int = resources.getColor(android.R.color.holo_orange_dark)

    var defaultStartLoading: Boolean = true

    var needlePivotX: Float = 0.0f
        private set
        get() {
            return needleJointRadius.toFloat()
        }

    var needlePivotY: Float = 0.0f
        private set
        get() {
            return (needleView.height - needleJointRadius).toFloat()
        }

    private lateinit var lowerRangeArcView: ArcView
    private lateinit var higherRangeArcView: ArcView

    private lateinit var needleView: NeedleView


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

    constructor(context: Context, rangeIndicatorRadius: Int,
                rangeIndicatorWidth: Int, needleWidth: Int,
                needleJointRadius: Int, lowerRangeColor: Int,
                higherRangeColor: Int, needleColor: Int, defaultStartLoading: Boolean) : super(context) {
        this.rangeIndicatorRadius = rangeIndicatorRadius
        this.rangeIndicatorWidth = rangeIndicatorWidth
        this.needleWidth = needleWidth
        this.needleJointRadius = needleJointRadius
        this.lowerRangeColor = lowerRangeColor
        this.higherRangeColor = higherRangeColor
        this.needleColor = needleColor
        this.defaultStartLoading = defaultStartLoading
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GaugeLoader, 0, 0)

        this.rangeIndicatorRadius = typedArray
                .getDimensionPixelSize(R.styleable.GaugeLoader_gauge_rangeIndicatorRadius, 140)

        this.rangeIndicatorWidth = typedArray
                .getDimensionPixelSize(R.styleable.GaugeLoader_gauge_rangeIndicatorWidth, 100)

        this.needleWidth = typedArray
                .getDimensionPixelSize(R.styleable.GaugeLoader_gauge_needleWidth, 20)

        this.needleJointRadius = typedArray
                .getDimensionPixelSize(R.styleable.GaugeLoader_gauge_needleJointRadius, 45)

        this.lowerRangeColor = typedArray
                .getColor(R.styleable.GaugeLoader_gauge_lowerRangeColor, resources.getColor(android.R.color.holo_green_light))

        this.higherRangeColor = typedArray
                .getColor(R.styleable.GaugeLoader_gauge_higherRangeColor, resources.getColor(android.R.color.holo_green_dark))

        this.needleColor = typedArray
                .getColor(R.styleable.GaugeLoader_gauge_needleColor, resources.getColor(android.R.color.holo_orange_dark))

        this.defaultStartLoading = typedArray
                .getBoolean(R.styleable.GaugeLoader_gauge_defaultStartLoading, true)

        typedArray.recycle()
    }

    /*override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthHeight = (2 * (rangeIndicatorRadius)) + rangeIndicatorWidth
        setMeasuredDimension(widthHeight, widthHeight)
    }*/

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        lowerRangeArcView = ArcView(context, rangeIndicatorRadius, rangeIndicatorWidth,
                150.0f, 155.0f, lowerRangeColor, true, false)

        higherRangeArcView = ArcView(context, rangeIndicatorRadius, rangeIndicatorWidth,
                300.0f, 90.0f, higherRangeColor, true, false)


        val arcLayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        arcLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        addView(lowerRangeArcView, arcLayoutParams)
        addView(higherRangeArcView, arcLayoutParams)

        needleView = NeedleView(context, (rangeIndicatorRadius - needleJointRadius), needleWidth,
                needleJointRadius, needleColor)

        val needleLayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        needleLayoutParams.topMargin = rangeIndicatorWidth / 2
        needleLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)

        addView(needleView, needleLayoutParams)

        if (defaultStartLoading) {

            val viewTreeObserver = this.viewTreeObserver
            val loaderView = this

            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    startLoading(null)

                    val vto = loaderView.viewTreeObserver
                    vto.removeOnGlobalLayoutListener(this)
                }
            })
        }
    }

    fun startLoading(animation: Animation?) {

        var rotateAnim = animation

        if (rotateAnim == null) {
            rotateAnim = getRotateAnimation()

            rotateAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(anim: Animation?) {
                }

                override fun onAnimationEnd(anim: Animation?) {
                    startLoading(null)
                }

                override fun onAnimationStart(anim: Animation?) {
                }
            })
        }

        needleView.startAnimation(rotateAnim)
    }

    fun getRotateAnimation(): RotateAnimation {

        val toDegree = (30..90).random()
        val animDuration = (500..1000).random()

        val anim = RotateAnimation(-90.0f, toDegree.toFloat(),
                needlePivotX,
                needlePivotY)

        anim.duration = animDuration.toLong()
        anim.repeatCount = 1
        anim.repeatMode = Animation.REVERSE

        val random = (0..6).random()
        when (random) {
            0 -> anim.interpolator = LinearInterpolator()
            1 -> anim.interpolator = AccelerateInterpolator()
            2 -> anim.interpolator = DecelerateInterpolator()
            3 -> anim.interpolator = AccelerateDecelerateInterpolator()
            4 -> anim.interpolator = AnticipateInterpolator()
            5 -> anim.interpolator = OvershootInterpolator()
            6 -> anim.interpolator = AnticipateOvershootInterpolator()
        }

        return anim
    }

}