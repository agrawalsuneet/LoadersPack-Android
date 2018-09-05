package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.agrawalsuneet.dotsloader.utils.random
import com.agrawalsuneet.loaderspack.basicviews.ArcView
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract
import java.util.*

/**
 * Created by agrawalsuneet on 9/4/18.
 */

class CurvesLoader : LinearLayout, LoaderContract {

    var noOfCurves: Int = 4

    var outermostCurveRadius: Int = 300
    var curveWidth: Int = 20
    var distanceBetweenCurve: Int = 40
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


    override fun initAttributes(attrs: AttributeSet) {
        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZeeLoader, 0, 0)

        this.dotsRadius = typedArray.getDimensionPixelSize(R.styleable.ZeeLoader_zee_dotsRadius, 50)

        this.distanceMultiplier = typedArray.getInteger(R.styleable.ZeeLoader_zee_distanceMultiplier, 4)

        this.firsDotColor = typedArray.getColor(R.styleable.ZeeLoader_zee_firstDotsColor,
                resources.getColor(R.color.loader_selected))

        this.secondDotColor = typedArray.getColor(R.styleable.ZeeLoader_zee_secondDotsColor,
                resources.getColor(R.color.loader_selected))

        this.animDuration = typedArray.getInt(R.styleable.ZeeLoader_zee_animDuration, 500)

        typedArray.recycle()*/
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

            val circleRadius = outermostCurveRadius - (i * curveWidth) - (i * distanceBetweenCurve)

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

        for (i in 0..curvesArray.lastIndex) {
            val arcView = curvesArray.get(i)
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
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.repeatCount = Animation.INFINITE

        return rotateAnimation
    }

}