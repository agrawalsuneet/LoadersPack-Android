package com.agrawalsuneet.loaderspack.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R

/**
 * Created by agrawalsuneet on 9/20/18.
 */

class FidgetView : View {

    var fidgetRadius: Int = 130
    var sidesRadius: Int = 130
    var bodyColor: Int = resources.getColor(R.color.red)
    var sideCirclesColor = resources.getColor(R.color.grey)

    private val centerPaint: Paint = Paint()
    private val sidesPaint: Paint = Paint()

    private var calWidth = 0.0f
    private var calHeight = 0.0f
    private val sin30 = 0.5
    private val cos30 = 0.866

    private val distanceFactor = 0.8

    constructor(context: Context) : super(context) {
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initValues()
    }


    fun initAttributes(attrs: AttributeSet) {

        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcView, 0, 0)

        this.arcRadius = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcRadius, 60)
        this.arcWidth = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcWidth, 10)

        this.startAngle = typedArray.getFloat(R.styleable.ArcView_startAngle, 0.0f)
        this.sweepAngle = typedArray.getFloat(R.styleable.ArcView_sweepAngle, 180.0f)

        this.arcColor = typedArray.getColor(R.styleable.ArcView_arcColor, resources.getColor(R.color.red))

        this.drawOnlyStroke = typedArray.getBoolean(R.styleable.ArcView_drawOnlyStroke, true)

        typedArray.recycle()*/
    }

    private fun initValues() {

        centerPaint.isAntiAlias = true
        centerPaint.style = Paint.Style.FILL
        centerPaint.color = bodyColor

        sidesPaint.isAntiAlias = true
        sidesPaint.style = Paint.Style.FILL
        sidesPaint.color = sideCirclesColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        calWidth = (2 * sidesRadius) + (2 * cos30 * distanceFactor * (fidgetRadius + sidesRadius)).toFloat()

        calHeight = ((2 * sidesRadius)
                + (distanceFactor * (fidgetRadius + sidesRadius))
                + (sin30 * distanceFactor * (fidgetRadius + sidesRadius))).toFloat()
        setMeasuredDimension(calWidth.toInt(), calHeight.toInt())
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = (calWidth / 2)
        val centerY = (sidesRadius + (distanceFactor * (fidgetRadius + sidesRadius))).toFloat()

        //center circle
        canvas.drawCircle(centerX, centerY, fidgetRadius.toFloat(), centerPaint)

        //top circle
        var xCor = calWidth / 2
        var yCor = (centerY - ((fidgetRadius + sidesRadius) * distanceFactor)).toFloat()
        canvas.drawCircle(xCor, yCor, sidesRadius.toFloat(), centerPaint)

        canvas.drawCircle(xCor, yCor, (sidesRadius * 3 / 5).toFloat(), sidesPaint)

        //right circle
        xCor = centerX + (cos30 * distanceFactor * (fidgetRadius + sidesRadius)).toFloat()
        yCor = centerY + (sin30 * distanceFactor * (fidgetRadius + sidesRadius)).toFloat()
        canvas.drawCircle(xCor, yCor, sidesRadius.toFloat(), centerPaint)

        canvas.drawCircle(xCor, yCor, (sidesRadius * 3 / 5).toFloat(), sidesPaint)

        //left circle
        xCor = centerX - (cos30 * distanceFactor * (fidgetRadius + sidesRadius)).toFloat()
        yCor = centerY + (sin30 * distanceFactor * (fidgetRadius + sidesRadius)).toFloat()
        canvas.drawCircle(xCor, yCor, sidesRadius.toFloat(), centerPaint)

        canvas.drawCircle(xCor, yCor, (sidesRadius * 3 / 5).toFloat(), sidesPaint)

        pivotX = centerX
        pivotY = centerY
    }
}