package com.agrawalsuneet.loaderspack.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R

/**
 * Created by suneet on 12/31/17.
 */

open class CircularBaseView : View, LoaderContract {

    var noOfSticks: Int = 50

    var circleRadius: Float = 200.0f
    var innerCircleRadius: Float = 100.0f

    var arcsColor: Int = resources.getColor(R.color.grey)
    var viewBackgroundColor: Int = resources.getColor(android.R.color.white)

    private lateinit var arcPaint: Paint
    private lateinit var innerCirclePaint: Paint

    private lateinit var circleOval: RectF

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

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(2 * circleRadius.toInt(), 2 * circleRadius.toInt())
    }

    private fun initPaints() {
        arcPaint = Paint()
        arcPaint.color = arcsColor
        arcPaint.style = Paint.Style.FILL
        arcPaint.isAntiAlias = true

        circleOval = RectF().apply {
            left = 0f
            top = 0f
            right = 2 * circleRadius
            bottom = 2 * circleRadius
        }

        innerCirclePaint = Paint()
        innerCirclePaint.color = viewBackgroundColor
        innerCirclePaint.style = Paint.Style.FILL
        innerCirclePaint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (noOfSticks < 1) {
            noOfSticks = 8
        }

        val sweepAngle: Float = (360f / (2 * noOfSticks))

        var startAngle: Float = (0 - (sweepAngle / 2))

        for (i in 0 until noOfSticks) {
            canvas.drawArc(circleOval, startAngle, sweepAngle, true, arcPaint)
            startAngle += (2 * sweepAngle)
        }

        canvas.drawCircle(circleRadius, circleRadius, innerCircleRadius, innerCirclePaint)
    }


}
