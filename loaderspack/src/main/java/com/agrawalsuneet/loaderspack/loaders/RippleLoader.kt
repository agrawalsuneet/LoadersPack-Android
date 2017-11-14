package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

/**
 * Created by suneet on 11/14/17.
 */
class RippleLoader : View, LoaderContract {

    var fromRadius: Float = 20.0f

    var toRadius: Float = 160.0f
        set(value) {
            field = value
            invalidate()
        }

    var fromStroke: Float = 4.0f

    var toStroke: Float = 16.0f
        set(value) {
            field = value
            invalidate()
        }
    var fromAlpha: Int = 255
        set(value) {
            field = if (value >= 255) 255 else value
        }
    var toAlpha: Int = 40
        set(value) {
            field = if (value >= 255) 255 else value
        }

    var waveColor: Int = resources.getColor(R.color.blue)

    private lateinit var wavePaint: Paint
    private var currentRadius: Float = 0f
    private var currentStroke: Float = 0f
    private var currentAlpha: Int = 0

    private var centerPoint: Float = 0.0f

    constructor(context: Context) : super(context) {
        initPaints()
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initPaints()
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initPaints()
        initValues()
    }


    override fun initAttributes(attrs: AttributeSet) {

    }

    private fun initValues() {
        currentRadius = fromRadius
        currentStroke = fromStroke
        currentAlpha = fromAlpha

        centerPoint = toRadius + toStroke
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(2 * (toRadius + toStroke).toInt(),
                2 * (toRadius + toStroke).toInt())
    }

    private fun initPaints() {
        wavePaint = Paint()
        wavePaint.color = waveColor
        wavePaint.style = Paint.Style.STROKE
        wavePaint.isAntiAlias = true
        wavePaint.strokeWidth = fromStroke
        wavePaint.alpha = currentAlpha
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(centerPoint, centerPoint, currentRadius, wavePaint)

        //ViewCompat.postInvalidateOnAnimation(this)
    }
}