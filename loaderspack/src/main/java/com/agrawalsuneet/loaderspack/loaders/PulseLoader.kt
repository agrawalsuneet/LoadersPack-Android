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

    var radius: Int = 20

    var calWidth: Int = 0
    var calHeight: Int = 0

    var pulseColor: Int = resources.getColor(android.R.color.holo_red_dark)

    private val paint: Paint = Paint()


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

        calWidth = (40 * radius)
        calHeight = (20 * radius)
        setMeasuredDimension(calWidth, calHeight)
    }

    private fun initValues() {

        paint.color = pulseColor
        paint.isAntiAlias = true

        paint.style = Paint.Style.FILL
        paint.strokeWidth = radius.toFloat()
        paint.strokeCap = Paint.Cap.ROUND
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        //first line
        canvas.drawLine(
                (0.5 * radius).toFloat(), // startX
                (10 * radius).toFloat(), // startY
                (16 * radius).toFloat(), // stopX
                (10 * radius).toFloat(), // stopY
                paint // Paint
        )

        canvas.drawLine(
                (16 * radius).toFloat(),
                (10 * radius).toFloat(),
                (18 * radius).toFloat(),
                (0.5 * radius).toFloat(),
                paint
        )

        canvas.drawLine(
                (18 * radius).toFloat(),
                (radius).toFloat(),
                (21 * radius).toFloat(),
                (19 * radius).toFloat(),
                paint
        )

        canvas.drawLine(
                (18 * radius).toFloat(),
                (radius).toFloat(),
                (21 * radius).toFloat(),
                (19 * radius).toFloat(),
                paint
        )

        canvas.drawLine(
                (21 * radius).toFloat(),
                (19.5 * radius).toFloat(),
                (23 * radius).toFloat(),
                (6 * radius).toFloat(),
                paint
        )

        canvas.drawLine(
                (23 * radius).toFloat(),
                (6 * radius).toFloat(),
                (24 * radius).toFloat(),
                (10 * radius).toFloat(),
                paint
        )

        canvas.drawLine(
                (24 * radius).toFloat(),
                (10 * radius).toFloat(),
                (39.5 * radius).toFloat(),
                (10 * radius).toFloat(),
                paint
        )


    }

}