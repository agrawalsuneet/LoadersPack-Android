package com.agrawalsuneet.loaderspack.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by agrawalsuneet on 11/1/18.
 */

class LensView : View {

    var lensRadius: Int = 50
    var borderWidth: Int = 20

    var handleLength: Int = 80

    var lensColor: Int = resources.getColor(android.R.color.holo_green_light)
    var drawOnlyStroke: Boolean = true


    private val paint: Paint = Paint()

    private var circleCenterPoint: Float = 0.0f
    private var handleStartPoint: Float = 0.0f
    private var handleEndPoint: Float = 0.0f

    private val sin45 = 0.7075f

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

        /* val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcView, 0, 0)

         this.arcRadius = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcRadius, 60)
         this.arcWidth = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcWidth, 10)

         this.startAngle = typedArray.getFloat(R.styleable.ArcView_startAngle, 0.0f)
         this.sweepAngle = typedArray.getFloat(R.styleable.ArcView_sweepAngle, 180.0f)

         this.lensColor = typedArray.getColor(R.styleable.ArcView_arcColor, resources.getColor(android.R.color.holo_red_dark))

         this.drawOnlyStroke = typedArray.getBoolean(R.styleable.ArcView_drawOnlyStroke, true)

         typedArray.recycle()*/
    }

    private fun initValues() {

        paint.color = lensColor
        paint.isAntiAlias = true
        paint.strokeWidth = borderWidth.toFloat()

        paint.strokeCap = Paint.Cap.ROUND

        circleCenterPoint = (lensRadius + borderWidth / 2).toFloat()
        handleStartPoint = (circleCenterPoint + (lensRadius * sin45))

        handleEndPoint = (handleStartPoint + (handleLength * sin45))

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthHeight = ((2 * lensRadius) + (borderWidth)
                + (handleLength * sin45) - (0.29 * (lensRadius))).toInt()

        setMeasuredDimension(widthHeight, widthHeight)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        canvas.drawCircle(circleCenterPoint,
                circleCenterPoint,
                lensRadius.toFloat(),
                paint)

        paint.style = Paint.Style.FILL

        canvas.drawLine(handleStartPoint, handleStartPoint,
                handleEndPoint, handleEndPoint,
                paint)
    }

}