package com.agrawalsuneet.loaderspack.scenes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.agrawalsuneet.dotsloader.utils.random
import com.agrawalsuneet.loaderspack.basicviews.CircleView
import com.agrawalsuneet.loaderspack.contracts.LoaderContract

class UniverseScene : LinearLayout, LoaderContract {

    var numberOfStars = 30

    private val randomRadiusMultiplier = 200

    private var calWidth = 0
    private var calHeight = 0

    private var sunRelativeLayout: RelativeLayout? = null
    private var relativeLayout: RelativeLayout? = null


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

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        Log.d("Suneet Agrawal", "onSizeChanged")

        calWidth = w
        calHeight = h
    }

    private fun initView() {
        setBackgroundColor(Color.parseColor("#352f7b"))

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                drawViews()

                this@UniverseScene.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun drawViews() {

        Log.d("Suneet Agrawal", "drawViews")

        removeAllViews()
        removeAllViewsInLayout()

        relativeLayout = RelativeLayout(context)
        val relParam = RelativeLayout.LayoutParams(calWidth, calHeight)


        //draw stars
        val lowerRange: Int
        val upperRange: Int
        val sunRadius: Int

        if (calWidth < calHeight) {
            lowerRange = (calWidth / randomRadiusMultiplier)
            upperRange = (calHeight / randomRadiusMultiplier)

            sunRadius = calWidth / 5
        } else {
            lowerRange = (calHeight / randomRadiusMultiplier)
            upperRange = (calWidth / randomRadiusMultiplier)

            sunRadius = calHeight / 5
        }

        for (pos in 0..numberOfStars) {
            val radius = (lowerRange..upperRange).random()
            val starsCircleView = CircleView(context, radius, Color.parseColor(getRandomAlphaColor("ffffff")))

            relativeLayout?.addView(starsCircleView, getStarsRandomPosition(pos, radius))
        }

        //draw sun
        sunRelativeLayout = RelativeLayout(context)
        val sunCircleView = CircleView(context = context,
                circleRadius = sunRadius / 2,
                circleColor = Color.parseColor("#f5ac2f"),
                isAntiAlias = false)

        val sunParams = RelativeLayout.LayoutParams(sunRadius / 2, sunRadius / 2)
        sunParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        sunRelativeLayout?.addView(sunCircleView, sunParams)

        val sunGradient = GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                intArrayOf(Color.parseColor("#ddf5ac2f"), Color.parseColor("#00f5ac2f")))
        sunGradient.gradientType = GradientDrawable.RADIAL_GRADIENT
        sunGradient.gradientRadius = (sunRadius * 1.5).toFloat()
        sunRelativeLayout?.background = sunGradient


        val sunRelParam = RelativeLayout.LayoutParams(3 * sunRadius, 3 * sunRadius)
        sunRelParam.addRule(RelativeLayout.CENTER_VERTICAL)
        sunRelParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
        sunRelParam.rightMargin = (sunRadius * 0.2).toInt()
        sunRelParam.bottomMargin = sunRadius

        relativeLayout?.addView(sunRelativeLayout, sunRelParam)

        this.addView(relativeLayout, relParam)
    }

    private fun getRandomAlphaColor(colorCode: String): String {
        val random = (33..99).random()
        return "#$random$colorCode"
    }

    private fun getStarsRandomPosition(pos: Int, radius: Int): RelativeLayout.LayoutParams {
        val param = RelativeLayout.LayoutParams(radius, radius)

        when (pos % 5) {
            0 -> {
                param.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
            }
            1 -> {
                param.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)
            }
            2 -> {
                param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
            }
            3 -> {
                param.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
            }
            4 -> {
                param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
            }
        }

        param.topMargin = (0..radius * randomRadiusMultiplier).random()
        param.bottomMargin = (0..radius * randomRadiusMultiplier).random()
        param.leftMargin = (0..radius * randomRadiusMultiplier).random()
        param.rightMargin = (0..radius * randomRadiusMultiplier).random()

        return param
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.d("Suneet Agrawal", "onLayout")
        //drawViews()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d("Suneet Agrawal", "onMeasure")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("Suneet Agrawal", "onDraw")
        //drawViews()
    }

}