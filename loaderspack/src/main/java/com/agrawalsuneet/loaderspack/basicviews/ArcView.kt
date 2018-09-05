package com.agrawalsuneet.loaderspack.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R

/**
 * Created by agrawalsuneet on 9/4/18.
 */

class ArcView : View {

    var arcRadius: Int = 60
    var arcWidth: Int = 10

    var startAngle: Float = 0.0f

    var sweepAngle: Float = 180.0f

    var arcColor: Int = resources.getColor(R.color.red)
    var drawOnlyStroke: Boolean = true

    private val paint: Paint = Paint()
    private var centerPoint: Float = 0.0f
    private var acrRect = RectF()

    constructor(context: Context?, arcRadius: Int, arcWidth: Int, startAngle: Float, sweepAngle: Float, arcColor: Int, drawOnlyStroke: Boolean) : super(context) {
        this.arcRadius = arcRadius
        this.arcWidth = arcWidth
        this.startAngle = startAngle
        this.sweepAngle = sweepAngle
        this.arcColor = arcColor
        this.drawOnlyStroke = drawOnlyStroke

        initValues()
    }

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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcView, 0, 0)

        this.arcRadius = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcRadius, 60)
        this.arcWidth = typedArray.getDimensionPixelSize(R.styleable.ArcView_arcWidth, 10)

        this.startAngle = typedArray.getFloat(R.styleable.ArcView_startAngle, 0.0f)
        this.sweepAngle = typedArray.getFloat(R.styleable.ArcView_sweepAngle, 180.0f)

        this.arcColor = typedArray.getColor(R.styleable.ArcView_arcColor, resources.getColor(R.color.red))

        this.drawOnlyStroke = typedArray.getBoolean(R.styleable.ArcView_drawOnlyStroke, true)

        typedArray.recycle()
    }

    private fun initValues() {
        centerPoint = (arcRadius + (arcWidth / 2)).toFloat()

        acrRect = RectF().apply {
            left = centerPoint - arcRadius
            right = centerPoint + arcRadius
            top = centerPoint - arcRadius
            bottom = centerPoint + arcRadius
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthHeight = (2 * (arcRadius)) + arcWidth
        setMeasuredDimension(widthHeight, widthHeight)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.isAntiAlias = true

        if (drawOnlyStroke) {
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = arcWidth.toFloat()
            paint.strokeCap = Paint.Cap.ROUND
        } else {
            paint.style = Paint.Style.FILL
        }
        paint.color = arcColor

        canvas.drawArc(acrRect, startAngle, sweepAngle, false, paint)
    }


}