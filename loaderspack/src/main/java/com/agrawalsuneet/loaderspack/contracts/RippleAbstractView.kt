package com.agrawalsuneet.loaderspack.contracts

import android.content.Context
import android.util.AttributeSet
import android.view.animation.*
import android.widget.LinearLayout
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

abstract class RippleAbstractView : LinearLayout, LoaderContract {

    var circleInitialRadius: Int = 40

    var circleColor: Int = resources.getColor(android.R.color.holo_red_dark)

    var fromAlpha: Float = 0.9f

    var toAlpha: Float = 0.01f

    var animationDuration = 2000

    var interpolator: Interpolator = DecelerateInterpolator()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    abstract fun initView()

    abstract fun startLoading()

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