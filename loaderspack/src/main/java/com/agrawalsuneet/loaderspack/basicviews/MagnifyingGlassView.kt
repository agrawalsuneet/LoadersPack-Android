package com.agrawalsuneet.loaderspack.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R

/**
 * Created by agrawalsuneet on 11/1/18.
 */

class MagnifyingGlassView : View {

    var glassRadius: Int = 50
    var glassBorderWidth: Int = 20

    var glassHandleLength: Int = 80

    var glassColor: Int = resources.getColor(android.R.color.holo_green_light)

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

    constructor(context: Context?, glassRadius: Int, glassBorderWidth: Int, glassHandleLength: Int, glassColor: Int) : super(context) {
        this.glassRadius = glassRadius
        this.glassBorderWidth = glassBorderWidth
        this.glassHandleLength = glassHandleLength
        this.glassColor = glassColor
        initValues()
    }

    fun initAttributes(attrs: AttributeSet) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MagnifyingGlassView, 0, 0)

        this.glassRadius = typedArray.getDimensionPixelSize(R.styleable.MagnifyingGlassView_glassRadius, 50)
        this.glassBorderWidth = typedArray.getDimensionPixelSize(R.styleable.MagnifyingGlassView_glassBorderWidth, 20)

        this.glassHandleLength = typedArray.getDimensionPixelSize(R.styleable.MagnifyingGlassView_glassHandleLength, 80)

        this.glassColor = typedArray.getColor(R.styleable.MagnifyingGlassView_glassColor,
                resources.getColor(android.R.color.holo_green_light))

        typedArray.recycle()
    }

    private fun initValues() {

        paint.color = glassColor
        paint.isAntiAlias = true
        paint.strokeWidth = glassBorderWidth.toFloat()

        paint.strokeCap = Paint.Cap.ROUND

        circleCenterPoint = (glassRadius + glassBorderWidth / 2).toFloat()
        handleStartPoint = (circleCenterPoint + (glassRadius * sin45))

        handleEndPoint = (handleStartPoint + (glassHandleLength * sin45))

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthHeight = ((2 * glassRadius) + (glassBorderWidth)
                + (glassHandleLength * sin45) - (0.29 * (glassRadius))).toInt()

        setMeasuredDimension(widthHeight, widthHeight)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        canvas.drawCircle(circleCenterPoint,
                circleCenterPoint,
                glassRadius.toFloat(),
                paint)

        paint.style = Paint.Style.FILL

        canvas.drawLine(handleStartPoint, handleStartPoint,
                handleEndPoint, handleEndPoint,
                paint)
    }

}