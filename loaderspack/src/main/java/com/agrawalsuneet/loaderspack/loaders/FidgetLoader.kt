package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.*
import android.widget.LinearLayout
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.FidgetView
import com.agrawalsuneet.loaderspack.contracts.LoaderContract

/**
 * Created by agrawalsuneet on 9/20/18.
 */
class FidgetLoader : LinearLayout, LoaderContract {

    var fidgetRadius: Int = 100
    var bodyColor: Int = resources.getColor(android.R.color.holo_red_light)
    var sideCirclesColor = resources.getColor(android.R.color.darker_gray)

    var noOfRotation: Int = 20
    var animDuration: Int = 3000
    var interpolator: Interpolator = AccelerateDecelerateInterpolator()

    private lateinit var fidgetView: FidgetView

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

    constructor(context: Context, fidgetRadius: Int, bodyColor: Int, sideCirclesColor: Int) : super(context) {
        this.fidgetRadius = fidgetRadius
        this.bodyColor = bodyColor
        this.sideCirclesColor = sideCirclesColor
        initView()
    }


    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FidgetLoader, 0, 0)

        fidgetRadius = typedArray.getDimensionPixelSize(R.styleable.FidgetLoader_fidget_fidgetRadius, 100)

        bodyColor = typedArray.getColor(R.styleable.FidgetLoader_fidget_bodyColor,
                resources.getColor(android.R.color.holo_red_light))

        sideCirclesColor = typedArray.getColor(R.styleable.FidgetLoader_fidget_sideCirclesColor,
                resources.getColor(android.R.color.darker_gray))


        noOfRotation = typedArray.getInt(R.styleable.FidgetLoader_fidget_noOfRotation, 20)

        animDuration = typedArray.getInt(R.styleable.FidgetLoader_fidget_animDuration, 3000)

        interpolator = AnimationUtils.loadInterpolator(context,
                typedArray.getResourceId(R.styleable.FidgetLoader_fidget_interpolator,
                        android.R.anim.accelerate_decelerate_interpolator))

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val calWidthHeight = (2 * fidgetRadius) + (4 * FidgetView.distanceFactor * (fidgetRadius)).toFloat()
        setMeasuredDimension(calWidthHeight.toInt(), calWidthHeight.toInt())
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER
        this.orientation = LinearLayout.VERTICAL

        fidgetView = FidgetView(context, fidgetRadius, bodyColor, sideCirclesColor)
        this.addView(fidgetView)

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
        val anim = getRotateAnim()
        fidgetView.startAnimation(anim)
    }

    private fun getRotateAnim(): RotateAnimation {

        val fromDegree = 0.0f
        val toDegree = 360f * noOfRotation

        val rotateAnimation = RotateAnimation(fromDegree, toDegree,
                fidgetView.pivotX, fidgetView.pivotY)
        rotateAnimation.duration = animDuration.toLong()
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = interpolator
        rotateAnimation.repeatCount = Animation.INFINITE

        return rotateAnimation
    }

}