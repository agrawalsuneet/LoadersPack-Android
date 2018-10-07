package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

/**
 * Created by agrawalsuneet on 10/6/18.
 */

class PulseLoader : View, LoaderContract {

    var pulseLineThickness: Int = 10
    var normalIncrementalValue: Float = 4.0f
    var pulseIncrementalValue: Float = 20.0f

    var pulseColor: Int = resources.getColor(android.R.color.holo_green_light)

    private val paint: Paint = Paint()

    private var step: Int = 0
    private val maxSteps : Int = 6

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

    override fun initAttributes(attrs: AttributeSet) {

        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcProgressLoader)

        arcRadius = typedArray.getDimensionPixelSize(R.styleable.ArcProgressLoader_arcprogress_arcRadius, 200)

        arcWidth = typedArray.getDimensionPixelSize(R.styleable.ArcProgressLoader_arcprogress_arcWidth, 40)

        incrementalAngle = typedArray.getFloat(R.styleable.ArcProgressLoader_arcprogress_incrementalAngle, 6.0f)

        maxArcAngle = typedArray.getFloat(R.styleable.ArcProgressLoader_arcprogress_maxArcAngle, 200.0f)

        val colorsArrayId = typedArray.getResourceId(R.styleable.ArcProgressLoader_arcprogress_arcColorsArray, 0)

        typedArray.recycle()

        if (colorsArrayId != 0) {
            arcColorsArray = resources.getIntArray(colorsArrayId)

            if (arcColorsArray == null || arcColorsArray.size < 1) {
                throw RuntimeException("ArcProgressLoader : Please provide a valid, non-empty colors array")
            }
        }*/
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        val calWidth = (40 * pulseLineThickness)
        val calHeight = (20 * pulseLineThickness)
        setMeasuredDimension(calWidth, calHeight)
    }

    private fun initValues() {

        initCordinated()



        paint.color = pulseColor
        paint.isAntiAlias = true

        paint.style = Paint.Style.FILL
        paint.strokeWidth = pulseLineThickness.toFloat()
        paint.strokeCap = Paint.Cap.ROUND
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        //first line
        /*canvas.drawLine(
                (0.5 * pulseLineThickness).toFloat(),
                (10 * pulseLineThickness).toFloat(),
                (16 * pulseLineThickness).toFloat(),
                (10 * pulseLineThickness).toFloat(),
                paint // Paint
        )

        canvas.drawLine(
                (16 * pulseLineThickness).toFloat(),
                (10 * pulseLineThickness).toFloat(),
                (18 * pulseLineThickness).toFloat(),
                (0.5 * pulseLineThickness).toFloat(),
                paint
        )

        canvas.drawLine(
                (18 * pulseLineThickness).toFloat(),
                (0.5 * pulseLineThickness).toFloat(),
                (21 * pulseLineThickness).toFloat(),
                (19 * pulseLineThickness).toFloat(),
                paint
        )

        canvas.drawLine(
                (18 * pulseLineThickness).toFloat(),
                (pulseLineThickness).toFloat(),
                (21 * pulseLineThickness).toFloat(),
                (19.5 * pulseLineThickness).toFloat(),
                paint
        )

        canvas.drawLine(
                (21 * pulseLineThickness).toFloat(),
                (19.5 * pulseLineThickness).toFloat(),
                (23 * pulseLineThickness).toFloat(),
                (6 * pulseLineThickness).toFloat(),
                paint
        )

        canvas.drawLine(
                (23 * pulseLineThickness).toFloat(),
                (6 * pulseLineThickness).toFloat(),
                (24 * pulseLineThickness).toFloat(),
                (10 * pulseLineThickness).toFloat(),
                paint
        )

        canvas.drawLine(
                (24 * pulseLineThickness).toFloat(),
                (10 * pulseLineThickness).toFloat(),
                (39.5 * pulseLineThickness).toFloat(),
                (10 * pulseLineThickness).toFloat(),
                paint
        )*/

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

        if(currentXValue >= lineCordinates.xEndCor){
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

    private fun initCordinated(){
        for (pos in 0 until maxSteps) {
            val lineCordinates = LineCordinates()

            when (pos) {
                0 -> {
                    lineCordinates.xStartCor = (0.5 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = (16 * pulseLineThickness).toFloat()
                    lineCordinates.yStartCor = (10 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (10 * pulseLineThickness).toFloat()

                    lineCordinates.xIncrementalValue = normalIncrementalValue
                }

                1 -> {
                    lineCordinates.xStartCor = (16 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = (18 * pulseLineThickness).toFloat()
                    lineCordinates.yStartCor = (10 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (0.5 * pulseLineThickness).toFloat()

                    lineCordinates.yIncrementalValue = -pulseIncrementalValue
                    //negative because going up

                    lineCordinates.xIncrementalValue =
                            (( (lineCordinates.xEndCor - lineCordinates.xStartCor) * pulseIncrementalValue)
                                    / (lineCordinates.yStartCor - lineCordinates.yEndCor))
                }

                2 -> {
                    lineCordinates.xStartCor = (18 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = (21 * pulseLineThickness).toFloat()
                    lineCordinates.yStartCor = (0.5 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (19.5 * pulseLineThickness).toFloat()

                    lineCordinates.yIncrementalValue = pulseIncrementalValue

                    lineCordinates.xIncrementalValue =
                            (( (lineCordinates.xEndCor - lineCordinates.xStartCor) * pulseIncrementalValue)
                                    / (lineCordinates.yEndCor - lineCordinates.yStartCor))
                }

                3 -> {
                    lineCordinates.xStartCor = (21 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = (23 * pulseLineThickness).toFloat()
                    lineCordinates.yStartCor = (19.5 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (6 * pulseLineThickness).toFloat()

                    lineCordinates.yIncrementalValue = -pulseIncrementalValue
                    //negative because going up

                    lineCordinates.xIncrementalValue =
                            (( (lineCordinates.xEndCor - lineCordinates.xStartCor) * pulseIncrementalValue)
                                    / (lineCordinates.yStartCor - lineCordinates.yEndCor))
                }

                4 -> {
                    lineCordinates.xStartCor = (23 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = (24 * pulseLineThickness).toFloat()
                    lineCordinates.yStartCor = (6 * pulseLineThickness).toFloat()
                    lineCordinates.yEndCor = (10 * pulseLineThickness).toFloat()

                    lineCordinates.yIncrementalValue = pulseIncrementalValue

                    lineCordinates.xIncrementalValue =
                            (( (lineCordinates.xEndCor - lineCordinates.xStartCor) * pulseIncrementalValue)
                                    / (lineCordinates.yEndCor - lineCordinates.yStartCor))
                }

                5 ->{
                    lineCordinates.xStartCor = (24 * pulseLineThickness).toFloat()
                    lineCordinates.xEndCor = (39.5 * pulseLineThickness).toFloat()
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