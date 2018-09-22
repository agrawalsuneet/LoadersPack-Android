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

    companion object {
        const val distanceFactor = 0.8
    }

    var fidgetRadius: Int = 100
    var bodyColor: Int = resources.getColor(R.color.red)
    var sideCirclesColor = resources.getColor(R.color.grey)

    private val centerPaint: Paint = Paint()
    private val sidesPaint: Paint = Paint()

    private var calWidthHeight = 0.0f

    private val sin30 = 0.5
    private val cos30 = 0.866

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

    constructor(context: Context, fidgetRadius: Int, bodyColor: Int, sideCirclesColor: Int) : super(context) {
        this.fidgetRadius = fidgetRadius
        this.bodyColor = bodyColor
        this.sideCirclesColor = sideCirclesColor
        initValues()
    }


    fun initAttributes(attrs: AttributeSet) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FidgetView, 0, 0)

        fidgetRadius = typedArray.getDimensionPixelSize(R.styleable.FidgetView_fidgetRadius, 100)
        bodyColor = typedArray.getColor(R.styleable.FidgetView_fidgetBodyColor, resources.getColor(R.color.red))
        sideCirclesColor = typedArray.getColor(R.styleable.FidgetView_fidgetSideCirclesColor, resources.getColor(R.color.grey))

        typedArray.recycle()
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

        calWidthHeight = (2 * fidgetRadius) + (4 * distanceFactor * (fidgetRadius)).toFloat()
        setMeasuredDimension(calWidthHeight.toInt(), calWidthHeight.toInt())
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerXY = (calWidthHeight / 2)

        //center circle
        canvas.drawCircle(centerXY, centerXY, fidgetRadius.toFloat(), centerPaint)

        //top circle
        var xCor = calWidthHeight / 2
        var yCor = (centerXY - ((2 * fidgetRadius) * distanceFactor)).toFloat()

        canvas.drawCircle(xCor, yCor, fidgetRadius.toFloat(), centerPaint)
        canvas.drawCircle(xCor, yCor, (fidgetRadius * 3 / 5).toFloat(), sidesPaint)

        //right circle
        xCor = centerXY + (cos30 * distanceFactor * (2 * fidgetRadius)).toFloat()
        yCor = centerXY + (sin30 * distanceFactor * (2 * fidgetRadius)).toFloat()

        canvas.drawCircle(xCor, yCor, fidgetRadius.toFloat(), centerPaint)
        canvas.drawCircle(xCor, yCor, (fidgetRadius * 3 / 5).toFloat(), sidesPaint)

        //left circle
        xCor = centerXY - (cos30 * distanceFactor * (2 * fidgetRadius)).toFloat()
        yCor = centerXY + (sin30 * distanceFactor * (2 * fidgetRadius)).toFloat()

        canvas.drawCircle(xCor, yCor, fidgetRadius.toFloat(), centerPaint)
        canvas.drawCircle(xCor, yCor, (fidgetRadius * 3 / 5).toFloat(), sidesPaint)

        pivotX = centerXY
        pivotY = centerXY
    }
}