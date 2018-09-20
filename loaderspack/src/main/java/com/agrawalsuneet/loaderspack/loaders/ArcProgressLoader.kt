package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract

/**
 * Created by agrawalsuneet on 9/16/18.
 */

class ArcProgressLoader : View, LoaderContract {

    var arcRadius: Int = 200
    var arcWidth: Int = 40

    var incrementalAngle: Float = 6.0f

    var maxArcAngle: Float = 200.0f
        set(value) {
            field = if (value > 360) (value % 360) else value
        }

    var arcColorsArray: IntArray = intArrayOf(resources.getColor(R.color.red),
            resources.getColor(R.color.amber),
            resources.getColor(R.color.green))

    private val paint: Paint = Paint()
    private var centerPoint: Float = 0.0f
    private var acrRect = RectF()

    private val ZeroAngle: Float = 270.0f
    private val ThreeSixtyAngle: Float = 630.0f

    private var startAngle: Float = ZeroAngle
    private var endAngle: Float = ZeroAngle + incrementalAngle
    private var colorIndex: Int = 0

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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcProgressLoader)

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
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthHeight = (2 * (arcRadius)) + arcWidth
        setMeasuredDimension(widthHeight, widthHeight)
    }

    private fun initValues() {
        centerPoint = (arcRadius + (arcWidth / 2)).toFloat()

        acrRect = RectF().apply {
            left = centerPoint - arcRadius
            right = centerPoint + arcRadius
            top = centerPoint - arcRadius
            bottom = centerPoint + arcRadius
        }

        paint.isAntiAlias = true

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = arcWidth.toFloat()
        paint.strokeCap = Paint.Cap.ROUND
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        val sweepAngle = endAngle - startAngle

        paint.color = arcColorsArray[colorIndex]
        canvas.drawArc(acrRect, startAngle, sweepAngle, false, paint)


        //both reached the end, restart with next color
        if (startAngle >= ThreeSixtyAngle && endAngle >= ThreeSixtyAngle) {
            startAngle = ZeroAngle
            endAngle = ZeroAngle + incrementalAngle


            if (colorIndex == arcColorsArray.lastIndex) {
                colorIndex = 0
            } else {
                colorIndex++
            }
            paint.color = arcColorsArray[colorIndex]

        }

        //endangle didn't reach end, keep increasing
        else if (endAngle < ThreeSixtyAngle) {

            endAngle += incrementalAngle

            //max arc angel reached increase start angel also
            if (sweepAngle >= maxArcAngle) {
                startAngle += incrementalAngle
            }
        }

        //end angel reached limit, increase only start angel
        else {
            startAngle += incrementalAngle
        }

        postInvalidate()
    }

}