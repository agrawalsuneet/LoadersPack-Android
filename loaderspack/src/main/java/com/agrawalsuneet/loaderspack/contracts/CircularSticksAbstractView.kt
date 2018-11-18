package com.agrawalsuneet.loaderspack.contracts

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

abstract class CircularSticksAbstractView : View, LoaderContract {

    var noOfSticks: Int = 80

    var outerCircleRadius: Float = 200.0f
    var innerCircleRadius: Float = 100.0f

    open var sticksColor: Int = resources.getColor(android.R.color.darker_gray)
    var viewBackgroundColor: Int = resources.getColor(android.R.color.white)

    protected lateinit var sticksPaint: Paint
    protected lateinit var innerCirclePaint: Paint

    protected lateinit var outerCircleOval: RectF

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    open fun initPaints() {
        sticksPaint = Paint()
        sticksPaint.color = sticksColor
        sticksPaint.style = Paint.Style.FILL
        sticksPaint.isAntiAlias = true

        outerCircleOval = RectF().apply {
            left = 0f
            top = 0f
            right = 2 * outerCircleRadius
            bottom = 2 * outerCircleRadius
        }

        innerCirclePaint = Paint()
        innerCirclePaint.color = viewBackgroundColor
        innerCirclePaint.style = Paint.Style.FILL
        innerCirclePaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(2 * outerCircleRadius.toInt(), 2 * outerCircleRadius.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (noOfSticks < 1) {
            noOfSticks = 8
        }

        val sweepAngle: Float = (360f / (2 * noOfSticks))
        var startAngle: Float = (0 - (sweepAngle / 2))

        for (i in 0 until noOfSticks) {
            canvas.drawArc(outerCircleOval, startAngle, sweepAngle, true, sticksPaint)
            startAngle += (2 * sweepAngle)
        }

        canvas.drawCircle(outerCircleRadius, outerCircleRadius, innerCircleRadius, innerCirclePaint)
    }

}