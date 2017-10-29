package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract


/**
 * Created by suneet on 10/29/17.
 */

class ClockLoader : View, LoaderContract {

    var outerCircleStroke: Float = 30.0f
    var outerCircleRadius: Int = 350

    var minuteHandLength: Int = 300
    var hourHandLength: Int = 240

    private var minutHandAngle : Float = 267.0f
    private var hourHandAngle : Float = 327.0f

    private var centerPoint: Float = 0.0f

    private lateinit var outerCirclePaint: Paint
    private lateinit var midCirclePaint: Paint
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
        outerCirclePaint.color = resources.getColor(R.color.silver)
        outerCirclePaint.style = Paint.Style.STROKE
        outerCirclePaint.isAntiAlias = true
        outerCirclePaint.strokeWidth = outerCircleStroke

        midCirclePaint = Paint()
        midCirclePaint.color = resources.getColor(R.color.black)
        midCirclePaint.style = Paint.Style.FILL
        midCirclePaint.isAntiAlias = true

        innerCirclePaint = Paint()
        innerCirclePaint.color = resources.getColor(R.color.silver)
        innerCirclePaint.style = Paint.Style.FILL
        innerCirclePaint.isAntiAlias = true

        minuteHandPaint = Paint()
        minuteHandPaint.color = resources.getColor(R.color.silver)
        minuteHandPaint.style = Paint.Style.FILL
        minuteHandPaint.isAntiAlias = true

        hourHandPaint = Paint()
        hourHandPaint.color = resources.getColor(R.color.silver)
        hourHandPaint.style = Paint.Style.FILL
        hourHandPaint.isAntiAlias = true

        centerPoint = outerCircleRadius.toFloat() + outerCircleStroke
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        canvas.drawCircle(centerPoint,
                centerPoint,
                outerCircleRadius.toFloat(), midCirclePaint)

        canvas.drawCircle(centerPoint,
                centerPoint,
                outerCircleRadius.toFloat(), outerCirclePaint)


        var hourOval = RectF().apply {
            left = centerPoint - hourHandLength
            right = centerPoint + hourHandLength
            top = centerPoint - hourHandLength
            bottom = centerPoint + hourHandLength
        }

        canvas.drawArc(hourOval, hourHandAngle, 6.0f, true, hourHandPaint)

        var minuteOval = RectF().apply {
            left = centerPoint - minuteHandLength
            right = centerPoint + minuteHandLength
            top = centerPoint - minuteHandLength
            bottom = centerPoint + minuteHandLength
        }

        canvas.drawArc(minuteOval, minutHandAngle, 6.0f, true, minuteHandPaint)

        minutHandAngle += 6.0f
        hourHandAngle += 0.5f

        if (minutHandAngle > 360.0f){
            minutHandAngle -= 360.0f
        }

        if (hourHandAngle > 360.0f){
            hourHandAngle -= 360.0f
        }

        ViewCompat.postInvalidateOnAnimation(this);

        /*canvas.drawCircle(centerPoint,
                centerPoint,
                innerCircleRadius.toFloat(), innerCirclePaint)*/

    }

}