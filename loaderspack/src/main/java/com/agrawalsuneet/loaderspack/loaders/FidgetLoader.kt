package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.FidgetView
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

/**
 * Created by agrawalsuneet on 9/20/18.
 */
class FidgetLoader : LinearLayout, LoaderContract {


    var fidgetRadius: Int = 130
    var bodyColor: Int = resources.getColor(R.color.red)
    var sideCirclesColor = resources.getColor(R.color.grey)

    var animDuration: Int = 50000

    private val sin30 = 0.5
    private val cos30 = 0.866

    private val distanceFactor = 0.8

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


    override fun initAttributes(attrs: AttributeSet) {
        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CurvesLoader, 0, 0)

        this.noOfCurves = typedArray.getInteger(R.styleable.CurvesLoader_curves_noOfCurves, 4)

        this.outermostCurveRadius = typedArray.getDimensionPixelSize(R.styleable.CurvesLoader_curves_outermostCurveRadius, 100)

        this.curveWidth = typedArray.getDimensionPixelSize(R.styleable.CurvesLoader_curves_curveWidth, 10)
        this.distanceBetweenCurves = typedArray.getDimensionPixelSize(R.styleable.CurvesLoader_curves_distanceBetweenCurves, 10)

        this.curveSweepAngle = typedArray.getFloat(R.styleable.CurvesLoader_curves_curveSweepAngle, 160.0f)

        this.curveColor = typedArray.getColor(R.styleable.CurvesLoader_curves_curveColor,
                resources.getColor(android.R.color.holo_red_light))

        this.animDuration = typedArray.getInt(R.styleable.CurvesLoader_curves_animDuration, 1500)

        this.interpolator = AnimationUtils.loadInterpolator(context,
                typedArray.getResourceId(R.styleable.CurvesLoader_curves_interpolator,
                        android.R.anim.linear_interpolator))

        typedArray.recycle()*/
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthHeight = (1.2 * fidgetRadius) + (4 * fidgetRadius).toFloat()
        setMeasuredDimension(widthHeight.toInt(), widthHeight.toInt())
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        this.gravity = Gravity.CENTER_HORIZONTAL
        this.orientation = LinearLayout.VERTICAL

        fidgetView = FidgetView(context, fidgetRadius, fidgetRadius, bodyColor, sideCirclesColor)
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
        val toDegree = 360f * 5.0f

        val centerX = (fidgetRadius) + (cos30 * distanceFactor * (fidgetRadius + fidgetRadius)).toFloat()
        val centerY = (fidgetRadius + (distanceFactor * (fidgetRadius + fidgetRadius))).toFloat()

        val rotateAnimation = RotateAnimation(fromDegree, toDegree,
                centerX, centerY)
        rotateAnimation.duration = animDuration.toLong()
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.repeatCount = Animation.INFINITE

        return rotateAnimation
    }

}