package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract


/**
 * Created by suneet on 10/29/17.
 */

class ClockLoader : View, LoaderContract {

    var outerCircleStroke: Float = 20.0f
    var outerCircleRadius: Int = 300

    var innerCircleRadius: Int = 30

    var minuteHandLength: Int = 260
    var hourHandLength: Int = 200

    private var centerPoint: Float = 0.0f

    private lateinit var outerCirclePaint: Paint
    private lateinit var innerCirclePaint: Paint
    private lateinit var minuteHandPaint: Paint
    private lateinit var hourHandPaint: Paint

    constructor(context: Context) : super(context) {
        initPaints()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(2 * (outerCircleRadius + outerCircleStroke.toInt()),
                2 * (outerCircleRadius + outerCircleStroke.toInt()))
    }

    override fun initAttributes(attrs: AttributeSet) {

    }

    private fun initPaints() {
        outerCirclePaint = Paint()
        outerCirclePaint.color = resources.getColor(R.color.black)
        outerCirclePaint.style = Paint.Style.STROKE
        outerCirclePaint.isAntiAlias = true
        outerCirclePaint.strokeWidth = outerCircleStroke

        innerCirclePaint = Paint()
        innerCirclePaint.color = resources.getColor(R.color.black)
        innerCirclePaint.style = Paint.Style.FILL
        innerCirclePaint.isAntiAlias = true

        minuteHandPaint = Paint()
        minuteHandPaint.color = resources.getColor(android.R.color.holo_purple)
        minuteHandPaint.style = Paint.Style.FILL
        minuteHandPaint.isAntiAlias = true

        hourHandPaint = Paint()
        hourHandPaint.color = resources.getColor(android.R.color.holo_red_light)
        hourHandPaint.style = Paint.Style.FILL
        hourHandPaint.isAntiAlias = true

        centerPoint = outerCircleRadius.toFloat() + outerCircleStroke
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(centerPoint,
                centerPoint,
                outerCircleRadius.toFloat(), outerCirclePaint)


        var hourOval = RectF().apply {
            left = centerPoint - hourHandLength
            right = centerPoint + hourHandLength
            top = centerPoint - hourHandLength
            bottom = centerPoint + hourHandLength
        }

        canvas.drawArc(hourOval, 300.0f, 6.0f, true, hourHandPaint)

        var minuteOval = RectF().apply {
            left = centerPoint - minuteHandLength
            right = centerPoint + minuteHandLength
            top = centerPoint - minuteHandLength
            bottom = centerPoint + minuteHandLength
        }

        canvas.drawArc(minuteOval, 270.0f, 6.0f, true, minuteHandPaint)

        canvas.drawCircle(centerPoint,
                centerPoint,
                innerCircleRadius.toFloat(), innerCirclePaint)

    }

}