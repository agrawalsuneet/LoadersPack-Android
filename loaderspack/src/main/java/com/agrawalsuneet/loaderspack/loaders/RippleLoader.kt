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
open class RippleLoader : LinearLayout, LoaderContract {


    var circleInitialRadius: Int = 40
        set(value) {
            field = value
            initView()
        }

    var circleColor: Int = resources.getColor(android.R.color.holo_red_dark)
        set(value) {
            field = value
            initView()
        }

    var fromAlpha: Float = 0.9f

    var toAlpha: Float = 0.01f

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


    protected open fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER
        circleView = CircleView(context, circleInitialRadius, circleColor)

        addView(circleView)

        val viewTreeObserver = this.viewTreeObserver
        val loaderView = this

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startLoading()

                val vto = loaderView.viewTreeObserver
                vto.removeOnGlobalLayoutListener(this)
            }
        })
    }

    open fun startLoading() {
        var animSet = getAnimSet()
        circleView.startAnimation(animSet)
    }

    protected fun getAnimSet(): Animation {
        var set = AnimationSet(true)

        val scaleAnim = ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnim.duration = animationDuration.toLong()
        scaleAnim.repeatCount = Animation.INFINITE


        val alphaAnim = AlphaAnimation(fromAlpha, toAlpha)
        alphaAnim.duration = animationDuration.toLong()
        alphaAnim.repeatCount = Animation.INFINITE

        set.addAnimation(scaleAnim)
        set.addAnimation(alphaAnim)

        set.duration = animationDuration.toLong()
        set.interpolator = interpolator
        set.repeatCount = Animation.INFINITE
        set.repeatMode = Animation.RESTART

        return set
    }
}