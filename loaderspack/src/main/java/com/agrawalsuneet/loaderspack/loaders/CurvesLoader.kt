package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.*
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.agrawalsuneet.dotsloader.utils.random
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.ArcView
import com.agrawalsuneet.loaderspack.contracts.LoaderContract
import java.util.*

/**
 * Created by agrawalsuneet on 9/4/18.
 */

class CurvesLoader : LinearLayout, LoaderContract {

    var noOfCurves: Int = 4

    var outermostCurveRadius: Int = 100
    var curveWidth: Int = 10
    var distanceBetweenCurves: Int = 10
    var curveSweepAngle: Float = 160.0f

    var curveColor: Int = resources.getColor(android.R.color.holo_red_light)

    var animDuration: Int = 1500

    var interpolator: Interpolator = LinearInterpolator()

    private lateinit var relativeLayout: RelativeLayout

    private var calWidthHeight: Int = 0
    private val curvesArray: ArrayList<ArcView> = arrayListOf()


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

    constructor(context: Context, noOfCurves: Int, outermostCurveRadius: Int, curveWidth: Int, distanceBetweenCurves: Int, curveSweepAngle: Float, curveColor: Int) : super(context) {
        this.noOfCurves = noOfCurves
        this.outermostCurveRadius = outermostCurveRadius
        this.curveWidth = curveWidth
        this.distanceBetweenCurves = distanceBetweenCurves
        this.curveSweepAngle = curveSweepAngle
        this.curveColor = curveColor
        initView()
    }


    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CurvesLoader, 0, 0)

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

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (calWidthHeight == 0) {
            calWidthHeight = (2 * outermostCurveRadius) + curveWidth
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
            calWidthHeight = (2 * outermostCurveRadius) + curveWidth
        }


        var startAngle = 0.0f


        for (i in 0 until noOfCurves) {

            val circleRadius = outermostCurveRadius - (i * curveWidth) - (i * distanceBetweenCurves)

            val arcView = ArcView(context, circleRadius, curveWidth, startAngle, curveSweepAngle, curveColor, true)
            startAngle += (10..80).random()


            val widthHeight = (2 * circleRadius) + curveWidth
            val param = RelativeLayout.LayoutParams(widthHeight, widthHeight)
            param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

            relativeLayout.addView(arcView, param)


            curvesArray.add(arcView)
        }

        val relParam = LinearLayout.LayoutParams(calWidthHeight, calWidthHeight)
        this.addView(relativeLayout, relParam)


        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startLoading()

                val vto = this@CurvesLoader.viewTreeObserver
                vto.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)

        for (curve in curvesArray) {
            curve.clearAnimation()
        }

        if (visibility == View.VISIBLE) {
            initView()
        }
    }

    private fun startLoading() {

        for (i in 0..curvesArray.lastIndex) {
            val arcView = curvesArray[i]
            val anim = getRotateAnim(i, arcView)
            arcView.startAnimation(anim)
        }
    }

    private fun getRotateAnim(circleCount: Int, arcView: ArcView): RotateAnimation {

        val fromDegree = if (circleCount == curvesArray.lastIndex) 360.0f else 0.0f
        val toDegree = if (circleCount == curvesArray.lastIndex) 0.0f else 360.0f

        val rotateAnimation = RotateAnimation(fromDegree, toDegree,
                (arcView.width / 2).toFloat(), (arcView.height / 2).toFloat())
        rotateAnimation.duration = animDuration.toLong()
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = interpolator
        rotateAnimation.repeatCount = Animation.INFINITE

        return rotateAnimation
    }

}