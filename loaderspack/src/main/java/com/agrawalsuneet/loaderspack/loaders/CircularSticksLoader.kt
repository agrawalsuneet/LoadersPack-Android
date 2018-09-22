package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import com.agrawalsuneet.dotsloader.utils.Helper
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.CircularSticksBaseView

/**
 * Created by suneet on 1/5/18.
 */
class CircularSticksLoader : CircularSticksBaseView {

    override var sticksColor: Int = resources.getColor(android.R.color.darker_gray)
        set(defaultColor) {
            field = defaultColor
            if (defaultStickPaint != null) {
                defaultStickPaint?.color = defaultColor
            }
        }

    open var selectedStickColor: Int = resources.getColor(android.R.color.black)
        set(selectedColor) {
            field = selectedColor
            if (selectedStickPaint != null) {
                selectedStickPaint?.color = selectedColor
                initShadowPaints()
            }
        }

    var showRunningShadow: Boolean = true

    var firstShadowColor: Int = 0
        set(value) {
            if (value != 0) {
                field = value
                isShadowColorSet = true
                if (firstShadowPaint != null) {
                    firstShadowPaint?.color = value
                }
            }
        }


    var secondShadowColor: Int = 0
        set(value) {
            if (value != 0) {
                field = value
                isShadowColorSet = true
                if (secondShadowPaint != null) {
                    secondShadowPaint?.color = value
                }
            }
        }

    var animDuration = 100

    private var isShadowColorSet = false

    private var selectedStickPos = 1
    private var shouldAnimate = true

    private var defaultStickPaint: Paint? = null
    private var selectedStickPaint: Paint? = null

    private var firstShadowPaint: Paint? = null
    private var secondShadowPaint: Paint? = null

    private var logTime: Long = 0

    constructor(context: Context) : super(context) {
        initPaints()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initPaints()
        initShadowPaints()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initPaints()
        initShadowPaints()
    }

    constructor(context: Context, noOfSticks: Int, outerCircleRadius: Float, innerCircleRadius: Float,
                sticksColor: Int, selectedStickColor: Int, viewBackgroundColor: Int) : super(context) {
        this.noOfSticks = noOfSticks
        this.outerCircleRadius = outerCircleRadius
        this.innerCircleRadius = innerCircleRadius
        this.sticksColor = sticksColor
        this.selectedStickColor = selectedStickColor
        this.viewBackgroundColor = viewBackgroundColor
        initPaints()
        initShadowPaints()
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularSticksLoader, 0, 0)

        this.noOfSticks = typedArray
                .getInteger(R.styleable.CircularSticksLoader_circularsticks_noOfSticks, 80)

        this.outerCircleRadius = typedArray
                .getDimension(R.styleable.CircularSticksLoader_circularsticks_outerCircleRadius, 200.0f)
        this.innerCircleRadius = typedArray
                .getDimension(R.styleable.CircularSticksLoader_circularsticks_innerCircleRadius, 100.0f)


        this.sticksColor = typedArray
                .getColor(R.styleable.CircularSticksLoader_circularsticks_stickColor, resources.getColor(android.R.color.darker_gray))
        this.selectedStickColor = typedArray
                .getColor(R.styleable.CircularSticksLoader_circularsticks_selectedStickColor, resources.getColor(android.R.color.black))

        this.viewBackgroundColor = typedArray
                .getColor(R.styleable.CircularSticksLoader_circularsticks_viewBackgroundColor, resources.getColor(android.R.color.white))

        this.showRunningShadow = typedArray.getBoolean(R.styleable.CircularSticksLoader_circularsticks_showRunningShadow, true)

        this.firstShadowColor = typedArray.getColor(R.styleable.CircularSticksLoader_circularsticks_firstShadowColor, 0)
        this.secondShadowColor = typedArray.getColor(R.styleable.CircularSticksLoader_circularsticks_secondShadowColor, 0)

        animDuration = typedArray.getInteger(R.styleable.CircularSticksLoader_circularsticks_animDuration, 100)

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCircle(canvas)

        if (shouldAnimate) {
            Handler().postDelayed({
                if (System.currentTimeMillis() - logTime >= animDuration) {

                    selectedStickPos++

                    if (selectedStickPos > noOfSticks) {
                        selectedStickPos = 1
                    }

                    invalidate()
                    logTime = System.currentTimeMillis()
                }
            }, animDuration.toLong())
        }
    }

    private fun drawCircle(canvas: Canvas) {
        val firstShadowPos = if (selectedStickPos == 1) noOfSticks else selectedStickPos - 1
        val secondShadowPos = if (firstShadowPos == 1) noOfSticks else firstShadowPos - 1

        val sweepAngle: Float = (360f / (2 * noOfSticks))
        var startAngle: Float = (0 - (sweepAngle / 2))

        for (i in 0 until noOfSticks) {

            if (i + 1 == selectedStickPos) {
                canvas.drawArc(outerCircleOval, startAngle, sweepAngle, true, selectedStickPaint)
            } else if (this.showRunningShadow && i + 1 == firstShadowPos) {
                canvas.drawArc(outerCircleOval, startAngle, sweepAngle, true, firstShadowPaint)
            } else if (this.showRunningShadow && i + 1 == secondShadowPos) {
                canvas.drawArc(outerCircleOval, startAngle, sweepAngle, true, secondShadowPaint)
            } else {
                canvas.drawArc(outerCircleOval, startAngle, sweepAngle, true, sticksPaint)
            }

            startAngle += (2 * sweepAngle)

        }

        canvas.drawCircle(outerCircleRadius, outerCircleRadius, innerCircleRadius, innerCirclePaint)
    }

    override fun initPaints() {
        super.initPaints()

        selectedStickPaint = Paint()
        selectedStickPaint?.color = selectedStickColor
        selectedStickPaint?.style = Paint.Style.FILL
        selectedStickPaint?.isAntiAlias = true
    }

    private fun initShadowPaints() {
        if (showRunningShadow) {
            if (!isShadowColorSet) {
                firstShadowColor = Helper.adjustAlpha(selectedStickColor, 0.7f)
                secondShadowColor = Helper.adjustAlpha(selectedStickColor, 0.5f)
                isShadowColorSet = true
            }

            firstShadowPaint = Paint()
            firstShadowPaint?.isAntiAlias = true
            firstShadowPaint?.style = Paint.Style.FILL
            firstShadowPaint?.color = firstShadowColor

            secondShadowPaint = Paint()
            secondShadowPaint?.isAntiAlias = true
            secondShadowPaint?.style = Paint.Style.FILL
            secondShadowPaint?.color = secondShadowColor
        }
    }

    fun startAnimation() {
        shouldAnimate = true
        invalidate()
    }

    fun stopAnimation() {
        shouldAnimate = false
        invalidate()
    }
}