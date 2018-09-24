package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

/**
 * Created by agrawalsuneet on 9/24/18.
 */

class WifiLoader : View, LoaderContract {

    var centerCircleRadius: Int = 30

    var circleColor: Int = resources.getColor(android.R.color.holo_red_dark)

    private val centerCirclePaint: Paint = Paint()
    private val sidesPaint: Paint = Paint()

    private var calWidth = 0
    private var calHeight = 0

    constructor(context: Context) : super(context) {
        initPaints()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initPaints()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initPaints()
    }

    override fun initAttributes(attrs: AttributeSet) {
        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockLoader, 0, 0)

        this.outerCircleBorderWidth = typedArray
                .getDimension(R.styleable.ClockLoader_clock_outerCircleBorderWidth, 30.0f)
        this.bigCircleRadius = typedArray
                .getDimension(R.styleable.ClockLoader_clock_bigCircleRadius, 350.0f)
        this.innerCircleRadius = typedArray
                .getDimension(R.styleable.ClockLoader_clock_innerCircleRadius, 20.0f)
        this.hourHandLength = typedArray
                .getDimension(R.styleable.ClockLoader_clock_hourHandLength, 240.0f)
        this.minuteHandLength = typedArray
                .getDimension(R.styleable.ClockLoader_clock_minuteHandLength, 300.0f)

        this.outerCircleBorderColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_outerCircleBorderColor, resources.getColor(android.R.color.darker_gray))
        this.bigCircleColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_bigCircleColor, resources.getColor(android.R.color.black))
        this.innerCircleColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_innerCircleColor, resources.getColor(android.R.color.darker_gray))
        this.hourHandColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_hourHandColor, resources.getColor(android.R.color.darker_gray))
        this.minuteHandColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_minuteHandColor, resources.getColor(android.R.color.darker_gray))

        this.animSpeedMultiplier = typedArray
                .getFloat(R.styleable.ClockLoader_clock_animSpeedMultiplier, 1.0f)

        typedArray.recycle()*/
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (calWidth == 0 || calHeight == 0) {
            calWidth = 18 * centerCircleRadius
            calHeight = 14 * centerCircleRadius
        }

        setMeasuredDimension(calWidth, calHeight)
    }


    private fun initPaints() {
        /*borderPaint = Paint()
        borderPaint.color = outerCircleBorderColor
        borderPaint.style = Paint.Style.STROKE
        borderPaint.isAntiAlias = true
        borderPaint.strokeWidth = outerCircleBorderWidth

        bigCirclePaint = Paint()
        bigCirclePaint.color = bigCircleColor
        bigCirclePaint.style = Paint.Style.FILL
        bigCirclePaint.isAntiAlias = true

        innerCirclePaint = Paint()
        innerCirclePaint.color = innerCircleColor
        innerCirclePaint.style = Paint.Style.FILL
        innerCirclePaint.isAntiAlias = true

        hourHandPaint = Paint()
        hourHandPaint.color = hourHandColor
        hourHandPaint.style = Paint.Style.FILL
        hourHandPaint.isAntiAlias = true

        minuteHandPaint = Paint()
        minuteHandPaint.color = minuteHandColor
        minuteHandPaint.style = Paint.Style.FILL
        minuteHandPaint.isAntiAlias = true*/

        centerCirclePaint.isAntiAlias = true
        centerCirclePaint.color = circleColor
        centerCirclePaint.style = Paint.Style.FILL


        sidesPaint.isAntiAlias = true
        sidesPaint.color = resources.getColor(android.R.color.holo_green_light)
        sidesPaint.style = Paint.Style.STROKE
        sidesPaint.strokeWidth = (2 * centerCircleRadius).toFloat()
        sidesPaint.strokeCap = Paint.Cap.ROUND
    }

    private fun initValues() {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (calWidth == 0 || calHeight == 0) {
            calWidth = 18 * centerCircleRadius
            calHeight = 14 * centerCircleRadius
        }

        var xCor = (calWidth / 2).toFloat()
        var yCor = (calHeight - centerCircleRadius).toFloat()

        canvas.drawCircle(xCor, yCor,
                centerCircleRadius.toFloat(),
                centerCirclePaint)



        for (i in 1..3) {

            val acrRectF = RectF().apply {
                left = ((calWidth / 2) - (i * 4 * centerCircleRadius)).toFloat()
                right = ((calWidth / 2) + (i * 4 * centerCircleRadius)).toFloat()
                top = ((calHeight - centerCircleRadius) - (((i * 4)) * centerCircleRadius)).toFloat()
                bottom = ((calHeight - centerCircleRadius) + (((i * 4)) * centerCircleRadius)).toFloat()
            }

            canvas.drawArc(acrRectF, 230.0f, 80.0f, false, sidesPaint)

            yCor = (calHeight - ((i * 4) + 1) * centerCircleRadius).toFloat()
            canvas.drawCircle(xCor, yCor,
                    centerCircleRadius.toFloat(),
                    centerCirclePaint)
        }


        /*for (i in 1..3) {

            val acrRectF = RectF().apply {
                left = ((calWidth / 2) - (((i * 4)) * centerCircleRadius)).toFloat()
                right = ((calWidth / 2) + (((i * 4)) * centerCircleRadius)).toFloat()
                top = (calHeight - (((i * 4) + 1) * centerCircleRadius) - (2 * centerCircleRadius)).toFloat()
                bottom = (calHeight + (((i * 4)) * centerCircleRadius) - (2 * centerCircleRadius)).toFloat()
            }

            canvas.drawArc(acrRectF, 0.0f, 360.0f, false, sidesPaint)

            yCor = (calHeight - ((i * 4) + 1) * centerCircleRadius).toFloat()
            canvas.drawCircle(xCor, yCor,
                    centerCircleRadius.toFloat(),
                    centerCirclePaint)
        }*/


/*canvas.drawCircle(xCor, yCor,
        centerCircleRadius.toFloat(),
        centerCirclePaint)


yCor = (calHeight - 5 * centerCircleRadius).toFloat()
canvas.drawCircle(xCor, yCor,
        centerCircleRadius.toFloat(),
        centerCirclePaint)*/


/*for (i in 1..3) {
    val acrRectF = RectF().apply {
        left = ((calWidth / 2) - (((i * 4) + 1) * centerCircleRadius)).toFloat()
        right = ((calWidth / 2) + (((i * 4) + 1) * centerCircleRadius)).toFloat()
        top = (calHeight - (((i * 4) + 1) * centerCircleRadius) - (2 * centerCircleRadius)).toFloat()
        bottom = (calHeight + (((i * 4) + 1) * centerCircleRadius) - (2 * centerCircleRadius)).toFloat()
    }

    canvas.drawArc(acrRectF, 225.0f, 90.0f, false, sidesPaint)
}*/

//postInvalidateOnAnimation()
    }
}