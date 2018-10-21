package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.*
import android.widget.RelativeLayout
import com.agrawalsuneet.dotsloader.utils.random
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

    override fun initAttributes(attrs: AttributeSet) {
        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotatingCircularSticksLoader, 0, 0)

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

        typedArray.recycle()*/
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        gravity = CENTER_IN_PARENT

        lowerRangeArcView = ArcView(context, rangeIndicatorRadius, rangeIndicatorWidth,
                150.0f, 155.0f, lowerRangeColor, true, false)

        higherRangeArcView = ArcView(context, rangeIndicatorRadius, rangeIndicatorWidth,
                300.0f, 90.0f, higherRangeColor, true, false)


        addView(lowerRangeArcView)
        addView(higherRangeArcView)

        needleView = NeedleView(context, (rangeIndicatorRadius - needleJointRadius), needleWidth,
                needleJointRadius, needleColor)

        val layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        layoutParams.topMargin = rangeIndicatorWidth / 2
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)

        addView(needleView, layoutParams)

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

        val anim = RotateAnimation(-90.0f, toDegree.toFloat(),
                needleJointRadius.toFloat(),
                (needleView.height - needleJointRadius).toFloat())

        anim.duration = 1000
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