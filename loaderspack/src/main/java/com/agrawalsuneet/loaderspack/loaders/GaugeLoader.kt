package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.agrawalsuneet.loaderspack.basicviews.ArcView
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

class GaugeLoader : RelativeLayout, LoaderContract {

    var rangeIndicatorRadius: Int = 140
    var rangeIndicatorWidth = 100

    var lowerRangeColor: Int = resources.getColor(android.R.color.holo_green_light)
    var higherRangeColor: Int = resources.getColor(android.R.color.holo_green_dark)

    private lateinit var lowerRangeArcView: ArcView
    private lateinit var higherRangeArcView: ArcView


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
                150.0f, 150.0f, lowerRangeColor, true, false)

        higherRangeArcView = ArcView(context, rangeIndicatorRadius, rangeIndicatorWidth,
                300.0f, 90.0f, higherRangeColor, true, false)

        addView(lowerRangeArcView)
        addView(higherRangeArcView)

    }

}