package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.contracts.LoaderContract

/**
 * Created by agrawalsuneet on 10/6/18.
 */

class PulseLoader : View, LoaderContract {

    var pulseLineThickness: Int = 15
    var sideLength: Int = 300

    var normalIncrementalValue: Float = 4.0f
        set(value) {
            field = if (value <= 0) 1.0f else value
        }

    var pulseIncrementalValue: Float = 20.0f
        set(value) {
            field = if (value <= 0) 1.0f else value
        }

    var pulseColor: Int = resources.getColor(android.R.color.holo_green_light)

    private val paint: Paint = Paint()

    private var step: Int = 0
    private val maxSteps: Int = 6

    private var currentXValue = 0.0f
    private var currentYValue = 0.0f

    private var posArray = Array(maxSteps) { LineCordinates() }

    constructor(context: Context) : super(context) {
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initAttributes(attrs)
        initValues()
    }

    constructor(context: Context, pulseLineThickness: Int, sideLength: Int, normalIncrementalValue: Float,
                pulseIncrementalValue: Float, pulseColor: Int) : super(context) {
        this.pulseLineThickness = pulseLineThickness
        this.sideLength = sideLength
        this.normalIncrementalValue = normalIncrementalValue
        this.pulseIncrementalValue = pulseIncrementalValue
        this.pulseColor = pulseColor
        initValues()
    }

    override fun initAttributes(attrs: AttributeSet) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PulseLoader)

        pulseLineThickness = typedArray.getDimensionPixelSize(R.styleable.PulseLoader_pulse_LineThickness, 15)
        sideLength = typedArray.getDimensionPixelSize(R.styleable.PulseLoader_pulse_sideLength, 300)

        normalIncrementalValue = typedArray.getFloat(R.styleable.PulseLoader_pulse_normalIncrementalValue, 4.0f)

        pulseIncrementalValue = typedArray.getFloat(R.styleable.PulseLoader_pulse_pulseIncrementalValue, 20.0f)

        pulseColor = typedArray
                .getColor(R.styleable.PulseLoader_pulse_pulseColor, resources.getColor(android.R.color.holo_green_light))

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        val calWidth = (2 * sideLength) + (8 * pulseLineThickness)
        val calHeight = (20 * pulseLineThickness)
        setMeasuredDimension(calWidth, calHeight)
    }

    private fun initValues() {

        initCordinates()

        paint.color = pulseColor
        paint.isAntiAlias = true

        paint.style = Paint.Style.FILL
        paint.strokeWidth = pulseLineThickness.toFloat()
        paint.strokeCap = Paint.Cap.ROUND
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (pos in 0 until step) {
            val lineCordinates = posArray[pos]
            canvas.drawLine(lineCordinates.xStartCor,
                    lineCordinates.yStartCor,
                    lineCordinates.xEndCor,
                    lineCordinates.yEndCor,
                    paint)
        }

        // draw current line
        val lineCordinates = posArray[step]
        canvas.drawLine(lineCordinates.xStartCor,
                lineCordinates.yStartCor,
                currentXValue,
                currentYValue,
                paint)

        currentXValue += lineCordinates.xIncrementalValue
        currentYValue += lineCordinates.yIncrementalValue

        if (currentXValue >= lineCordinates.xEndCor) {
            step = (step + 1) % maxSteps
            currentXValue = posArray[step].xStartCor
            currentYValue = posArray[step].yStartCor
        }

        postInvalidateOnAnimation()
    }

    private inner class LineCordinates {
        var xStartCor = 0.0f
        var xEndCor = 0.0f
        var yStartCor = 0.0f
        var yEndCor = 0.0f

        var xIncrementalValue = 0.0f
        var yIncrementalValue = 0.0f
    }

    private fun initCordinates() {
        for (pos in 0 until maxSteps) {
            val lineCordinates = LineCordinates()

            when (pos) {
                0 -> {
                    lineCordinates.xStartCor = (0.5 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = (sideLength).toFloat()
                    lineCordinates.yStartCor = (10 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (10 * pulseLineThickness).toFloat()

                    lineCordinates.xIncrementalValue = normalIncrementalValue
                }

                1 -> {
                    lineCordinates.xStartCor = (sideLength).toFloat()
                    lineCordinates.xEndCor = (sideLength + (2 * pulseLineThickness)).toFloat()
                    lineCordinates.yStartCor = (10 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (0.5 * pulseLineThickness).toFloat()

                    lineCordinates.yIncrementalValue = -pulseIncrementalValue
                    //negative because going up

                    lineCordinates.xIncrementalValue =
                            (((lineCordinates.xEndCor - lineCordinates.xStartCor) * pulseIncrementalValue)
                                    / (lineCordinates.yStartCor - lineCordinates.yEndCor))
                }

                2 -> {
                    lineCordinates.xStartCor = (sideLength + (2 * pulseLineThickness)).toFloat()
                    lineCordinates.xEndCor = sideLength + (5 * pulseLineThickness).toFloat()
                    lineCordinates.yStartCor = (0.5 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (19.5 * pulseLineThickness).toFloat()

                    lineCordinates.yIncrementalValue = pulseIncrementalValue

                    lineCordinates.xIncrementalValue =
                            (((lineCordinates.xEndCor - lineCordinates.xStartCor) * pulseIncrementalValue)
                                    / (lineCordinates.yEndCor - lineCordinates.yStartCor))
                }

                3 -> {
                    lineCordinates.xStartCor = sideLength + (5 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = sideLength + (7 * pulseLineThickness).toFloat()
                    lineCordinates.yStartCor = (19.5 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (6 * pulseLineThickness).toFloat()

                    lineCordinates.yIncrementalValue = -pulseIncrementalValue
                    //negative because going up

                    lineCordinates.xIncrementalValue =
                            (((lineCordinates.xEndCor - lineCordinates.xStartCor) * pulseIncrementalValue)
                                    / (lineCordinates.yStartCor - lineCordinates.yEndCor))
                }

                4 -> {
                    lineCordinates.xStartCor = sideLength + (7 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = sideLength + (8 * pulseLineThickness).toFloat()
                    lineCordinates.yStartCor = (6 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (10 * pulseLineThickness).toFloat()

                    lineCordinates.yIncrementalValue = pulseIncrementalValue

                    lineCordinates.xIncrementalValue =
                            (((lineCordinates.xEndCor - lineCordinates.xStartCor) * pulseIncrementalValue)
                                    / (lineCordinates.yEndCor - lineCordinates.yStartCor))
                }

                5 -> {
                    lineCordinates.xStartCor = sideLength + (8 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = ((2 * sideLength)
                            + (8 * pulseLineThickness)
                            - (pulseLineThickness / 2)).toFloat()
                    lineCordinates.yStartCor = (10 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (10 * pulseLineThickness).toFloat()

                    lineCordinates.xIncrementalValue = normalIncrementalValue
                }
            }

            posArray[pos] = lineCordinates
        }

        currentXValue = posArray[0].xStartCor
        currentYValue = posArray[0].yStartCor
    }

}