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

    var increamentalAngle: Float = 6.0f

    var maxArcAngle: Int = 270
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
    private var endAngle: Float = ZeroAngle + increamentalAngle
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

        /*val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SVGLoader)

        markerLength = typedArray.getDimensionPixelSize(R.styleable.SVGLoader_svgloader_markerLength, 50)

        viewportWidth = typedArray.getFloat(R.styleable.SVGLoader_svgloader_viewportWidth, 500f)
        viewportHeight = typedArray.getFloat(R.styleable.SVGLoader_svgloader_viewportHeight, 500f)

        fillTime = typedArray.getInteger(R.styleable.SVGLoader_svgloader_fillTime, 1000)
        timePerShape = typedArray.getInteger(R.styleable.SVGLoader_svgloader_timePerShape, 2000)

        interpolator = AnimationUtils.loadInterpolator(context,
                typedArray.getResourceId(R.styleable.SVGLoader_svgloader_interpolator,
                        android.R.anim.decelerate_interpolator))

        viewportWidth = viewportWidth
        viewportHeight = viewportHeight

        val shapesStringArrayId = typedArray.getResourceId(R.styleable.SVGLoader_svgloader_shapesStringArray, 0)
        val traceColorArrayId = typedArray.getResourceId(R.styleable.SVGLoader_svgloader_traceColorsArray, 0)
        val traceResidueColorsArrayId = typedArray.getResourceId(R.styleable.SVGLoader_svgloader_residueColorsArray, 0)
        val fillColorsArrayId = typedArray.getResourceId(R.styleable.SVGLoader_svgloader_fillColorsArray, 0)

        typedArray.recycle()

        if (shapesStringArrayId == 0) {
            throw RuntimeException("You need to set the shapes string array first.")
        } else if (fillColorsArrayId == 0) {
            throw RuntimeException("You need to set the shapes color array first.")
        }

        shapesStringArray = resources.getStringArray(shapesStringArrayId)
        fillColorsArray = resources.getIntArray(fillColorsArrayId)

        if (shapesStringArray.size > fillColorsArray.size) {
            throw RuntimeException("Not enough colors to match all shapes. " +
                    "Please check the size of shapes arting array and colors array")
        }

        traceColorsArray = validateColorsArray(traceColorArrayId, Color.BLACK, 1.0f)
        traceResidueColorsArray = validateColorsArray(traceResidueColorsArrayId, Color.GRAY, 0.3f)*/
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
            endAngle = ZeroAngle + increamentalAngle


            if (colorIndex == arcColorsArray.lastIndex) {
                colorIndex = 0
            } else {
                colorIndex++
            }
            paint.color = arcColorsArray[colorIndex]

        }

        //endangle didn't reach end, keep increasing
        else if (endAngle < ThreeSixtyAngle) {

            endAngle += increamentalAngle

            //max arc angel reached increase start angel also
            if (sweepAngle >= maxArcAngle) {
                startAngle += increamentalAngle
            }
        }

        //end angel reached limit, increase only start angel
        else {
            startAngle += increamentalAngle
        }


        postInvalidateOnAnimation()

    }

}