package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.*
import android.widget.LinearLayout
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.CircleView
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

/**
 * Created by suneet on 11/15/17.
 */
class RippleLoader : LinearLayout, LoaderContract {


    var circleRadius: Int = 120
        set(value) {
            field = value
            invalidate()
        }

    var circleColor: Int = resources.getColor(R.color.blue)

    var fromAlpha: Float = 0.6f

    var toAlpha: Float = 0.01f

    var startLoadingDefault = true

    var animationDuration = 2000

    var interpolator: Interpolator = DecelerateInterpolator()


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

        circleRadius = typedArray
                .getDimensionPixelSize(R.styleable.RippleLoader_ripple_circleRadius, 120)



        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(4 * circleRadius, 4 * circleRadius)
    }


    private fun initView() {
        this.gravity = Gravity.CENTER
        circleView = CircleView(context, circleRadius, circleColor)

        addView(circleView)

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

    fun startLoading() {
        var animSet = getAnimSet()

        circleView.startAnimation(animSet)
    }

    private fun getAnimSet(): Animation {
        var set = AnimationSet(true)

        val scaleAnim = ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnim.duration = animationDuration.toLong()
        scaleAnim.interpolator = interpolator
        scaleAnim.repeatCount = Animation.INFINITE
        scaleAnim.repeatMode = Animation.RESTART


        val alphaAnim = AlphaAnimation(fromAlpha, toAlpha)
        alphaAnim.duration = animationDuration.toLong()
        alphaAnim.interpolator = interpolator
        alphaAnim.repeatCount = Animation.INFINITE
        alphaAnim.repeatMode = Animation.RESTART

        set.duration = animationDuration.toLong()
        set.interpolator = interpolator
        set.repeatCount = Animation.INFINITE
        set.repeatMode = Animation.RESTART

        set.addAnimation(scaleAnim)
        set.addAnimation(alphaAnim)

        return set
    }
}