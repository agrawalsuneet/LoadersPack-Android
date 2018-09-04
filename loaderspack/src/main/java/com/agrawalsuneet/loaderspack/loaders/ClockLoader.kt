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
 * Created by suneet on 10/29/17.
 */

class ClockLoader : View, LoaderContract {

    var outerCircleBorderWidth: Float = 30.0f
        set(value) {
            field = value
            initPaints()
            initValues()
        }

    var bigCircleRadius: Float = 350.0f
        set(value) {
            field = value
            initValues()
        }

    var innerCircleRadius: Float = 20.0f
        set(value) {
            field = value
            initValues()
        }

    var hourHandLength: Float = 240.0f
        set(value) {
            field = value
            initValues()
        }
    var minuteHandLength: Float = 300.0f
        set(value) {
            field = value
            initValues()
        }

    var outerCircleBorderColor: Int = resources.getColor(R.color.grey)
        set(value) {
            field = value
            initPaints()
        }

    var bigCircleColor: Int = resources.getColor(R.color.black)
        set(value) {
            field = value
            initPaints()
        }

    var hourHandColor: Int = resources.getColor(R.color.grey)
        set(value) {
            field = value
            initPaints()
        }

    var minuteHandColor: Int = resources.getColor(R.color.grey)
        set(value) {
            field = value
            initPaints()
        }

    var innerCircleColor: Int = resources.getColor(R.color.grey)
        set(value) {
            field = value
            initPaints()
        }

    var animSpeedMultiplier: Float = 1.0f

    private var minuteHandAngle: Float = 267.0f
    private var hourHandAngle: Float = 327.0f

    private var centerPoint: Float = 0.0f

    private lateinit var hourOval: RectF
    private lateinit var minuteOval: RectF

    private lateinit var borderPaint: Paint
    private lateinit var bigCirclePaint: Paint
    private lateinit var innerCirclePaint: Paint
    private lateinit var minuteHandPaint: Paint
    private lateinit var hourHandPaint: Paint

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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockLoader, 0, 0)

        this.outerCircleBorderWidth = typedArray
                .getDimension(R.styleable.ClockLoader_clock_outerCircleBorderWidth, 30.0f)
        this.bigCircleRadius = typedArray
                .getDimension(R.styleable.ClockLoader_clock_bigCircleRadius, 350.0f)
        this.innerCircleRadius = typedArray
                .getDimension(R.styleable.ClockLoader_clock_innerCircleRadius, 20.0f)
        this.hourHandLength = typedArray
                .getDimension(R.styleable.ClockLoader_clock_hourHandLength, 240.0f)
        this.minuteHandLength = typedArray
                .getDimension(R.styleable.ClockLoader_clock_minuteHandLength, 300.0f)

        this.outerCircleBorderColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_outerCircleBorderColor, resources.getColor(R.color.grey))
        this.bigCircleColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_bigCircleColor, resources.getColor(R.color.black))
        this.innerCircleColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_innerCircleColor, resources.getColor(R.color.grey))
        this.hourHandColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_hourHandColor, resources.getColor(R.color.grey))
        this.minuteHandColor = typedArray
                .getColor(R.styleable.ClockLoader_clock_minuteHandColor, resources.getColor(R.color.grey))

        this.animSpeedMultiplier = typedArray
                .getFloat(R.styleable.ClockLoader_clock_animSpeedMultiplier, 1.0f)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(2 * (bigCircleRadius + outerCircleBorderWidth).toInt(),
                2 * (bigCircleRadius + outerCircleBorderWidth).toInt())
    }


    private fun initPaints() {
        borderPaint = Paint()
        borderPaint.color = outerCircleBorderColor
        borderPaint.style = Paint.Style.STROKE
        borderPaint.isAntiAlias = true
        borderPaint.strokeWidth = outerCircleBorderWidth

        bigCirclePaint = Paint()
        bigCirclePaint.color = bigCircleColor
        bigCirclePaint.style = Paint.Style.FILL
        bigCirclePaint.isAntiAlias = true

        innerCirclePaint = Paint()
        innerCirclePaint.color = innerCircleColor
        innerCirclePaint.style = Paint.Style.FILL
        innerCirclePaint.isAntiAlias = true

        hourHandPaint = Paint()
        hourHandPaint.color = hourHandColor
        hourHandPaint.style = Paint.Style.FILL
        hourHandPaint.isAntiAlias = true

        minuteHandPaint = Paint()
        minuteHandPaint.color = minuteHandColor
        minuteHandPaint.style = Paint.Style.FILL
        minuteHandPaint.isAntiAlias = true
    }

    private fun initValues() {
        centerPoint = bigCircleRadius + outerCircleBorderWidth

        hourOval = RectF().apply {
            left = centerPoint - hourHandLength
            right = centerPoint + hourHandLength
            top = centerPoint - hourHandLength
            bottom = centerPoint + hourHandLength
        }

        minuteOval = RectF().apply {
            left = centerPoint - minuteHandLength
            right = centerPoint + minuteHandLength
            top = centerPoint - minuteHandLength
            bottom = centerPoint + minuteHandLength
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(centerPoint,
                centerPoint,
                bigCircleRadius, bigCirclePaint)

        canvas.drawCircle(centerPoint,
                centerPoint,
                bigCircleRadius, borderPaint)

        canvas.drawArc(hourOval, hourHandAngle, 6.0f, true, hourHandPaint)

        canvas.drawArc(minuteOval, minuteHandAngle, 6.0f, true, minuteHandPaint)

        canvas.drawCircle(centerPoint,
                centerPoint,
                innerCircleRadius, innerCirclePaint)

        minuteHandAngle += (6.0f * animSpeedMultiplier)
        hourHandAngle += (0.5f * animSpeedMultiplier)

        if (minuteHandAngle > 360.0f) {
            minuteHandAngle -= 360.0f
        }

        if (hourHandAngle > 360.0f) {
            hourHandAngle -= 360.0f
        }

        postInvalidateOnAnimation()
    }

}