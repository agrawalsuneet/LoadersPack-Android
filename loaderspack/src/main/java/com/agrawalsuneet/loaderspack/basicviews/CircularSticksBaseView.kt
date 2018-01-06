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

open class CircularSticksBaseView : View, LoaderContract {

    var noOfSticks: Int = 80

    var outerCircleRadius: Float = 200.0f
    var innerCircleRadius: Float = 100.0f

    open var sticksColor: Int = resources.getColor(R.color.grey)
    var viewBackgroundColor: Int = resources.getColor(android.R.color.white)

    protected lateinit var sticksPaint: Paint
    protected lateinit var innerCirclePaint: Paint

    protected lateinit var outerCircleOval: RectF


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

    constructor(context: Context, noOfSticks: Int, outerCircleRadius: Float, innerCircleRadius: Float, sticksColor: Int, viewBackgroundColor: Int) : super(context) {
        this.noOfSticks = noOfSticks
        this.outerCircleRadius = outerCircleRadius
        this.innerCircleRadius = innerCircleRadius
        this.sticksColor = sticksColor
        this.viewBackgroundColor = viewBackgroundColor
        initPaints()
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularSticksBaseView, 0, 0)

        this.noOfSticks = typedArray
                .getInteger(R.styleable.CircularSticksBaseView_sticksbase_noOfSticks, 80)

        this.outerCircleRadius = typedArray
                .getDimension(R.styleable.CircularSticksBaseView_sticksbase_outerCircleRadius, 200.0f)
        this.innerCircleRadius = typedArray
                .getDimension(R.styleable.CircularSticksBaseView_sticksbase_innerCircleRadius, 100.0f)


        this.sticksColor = typedArray
                .getColor(R.styleable.CircularSticksBaseView_sticksbase_stickColor, resources.getColor(R.color.grey))
        this.viewBackgroundColor = typedArray
                .getColor(R.styleable.CircularSticksBaseView_sticksbase_viewBackgroundColor, resources.getColor(android.R.color.white))

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(2 * outerCircleRadius.toInt(), 2 * outerCircleRadius.toInt())
    }

    open protected fun initPaints() {
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
