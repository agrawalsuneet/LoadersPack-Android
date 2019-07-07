package com.agrawalsuneet.loaderspack.scenes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
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
        //drawViews()
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

        removeAllViews()
        removeAllViewsInLayout()

        relativeLayout = RelativeLayout(context)
        val relParam = RelativeLayout.LayoutParams(calWidth, calHeight)


        val lowerRange = if (calWidth < calHeight) (calWidth / randomRadiusMultiplier) else (calHeight / randomRadiusMultiplier)
        val upperRange = if (calWidth < calHeight) (calHeight / randomRadiusMultiplier) else (calWidth / randomRadiusMultiplier)

        for (pos in 0..numberOfStars) {
            val radius = (lowerRange..upperRange).random()
            val circleView = CircleView(context, radius, Color.parseColor(getRandomAlphaColor("ffffff")))

            relativeLayout?.addView(circleView, getStarsRandomPosition(pos, radius))
            Log.d("Suneet Agrawal", "circle Added")
        }

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