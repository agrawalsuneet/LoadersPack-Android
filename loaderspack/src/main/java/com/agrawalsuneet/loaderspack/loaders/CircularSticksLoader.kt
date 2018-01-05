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

    override var sticksColor: Int = resources.getColor(R.color.loader_defalut)
        set(defaultColor) {
            field = defaultColor
            if (defaultStickPaint != null) {
                defaultStickPaint?.color = defaultColor
            }
        }

    open var selectedStickColor: Int = resources.getColor(R.color.red)
        set(selectedColor) {
            field = selectedColor
            if (selectedStickPaint != null) {
                selectedStickPaint?.color = selectedColor
                initShadowPaints()
            }
        }


    var firstShadowColor: Int = 0
        set(value) {
            field = value
            if (value != 0) {
                isShadowColorSet = true
                initShadowPaints()
            }
        }


    var secondShadowColor: Int = 0
        set(value) {
            field = value
            if (value != 0) {
                isShadowColorSet = true
                initShadowPaints()
            }
        }

    var animDur = 100

    var showRunningShadow: Boolean = true
    private var isShadowColorSet = false

    protected var selectedStickPos = 1

    private var shouldAnimate = true

    private var defaultStickPaint: Paint? = null
    private var selectedStickPaint: Paint? = null

    private lateinit var firstShadowPaint: Paint
    private lateinit var secondShadowPaint: Paint

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

    constructor(context: Context, noOfSticks: Int, outerCircleRadius: Float, innerCircleRadius: Float, sticksColor: Int, viewBackgroundColor: Int) : super(context) {
        this.noOfSticks = noOfSticks
        this.outerCircleRadius = outerCircleRadius
        this.innerCircleRadius = innerCircleRadius
        this.sticksColor = sticksColor
        this.viewBackgroundColor = viewBackgroundColor
        initPaints()
        initShadowPaints()
    }

    override fun initAttributes(attrs: AttributeSet) {
        super.initAttributes(attrs)

        noOfSticks =  50
        animDur = 20
        showRunningShadow = false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCircle(canvas)

        if (shouldAnimate) {
            Handler().postDelayed({
                if (System.currentTimeMillis() - logTime >= animDur) {

                    selectedStickPos++

                    if (selectedStickPos > noOfSticks) {
                        selectedStickPos = 1
                    }

                    invalidate()
                    logTime = System.currentTimeMillis()
                }
            }, animDur.toLong())
        }
    }

    private fun drawCircle(canvas: Canvas) {
        val firstShadowPos = if (selectedStickPos == 1) noOfSticks else selectedStickPos - 1
        val secondShadowPos = if (firstShadowPos == 1) noOfSticks else firstShadowPos - 1

        val sweepAngle: Float = (360f / (2 * noOfSticks))
        var startAngle: Float = (0 - (sweepAngle / 2))

        for (i in 0 until noOfSticks) {
            //boolean isSelected = (i + 1 == selectedStickPos);
            //canvas.drawCircle(dotsXCorArr[i], dotsYCorArr[i], radius, isSelected ? selectedStickPaint : defaultStickPaint);


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
            firstShadowPaint.isAntiAlias = true
            firstShadowPaint.style = Paint.Style.FILL
            firstShadowPaint.color = firstShadowColor

            secondShadowPaint = Paint()
            secondShadowPaint.isAntiAlias = true
            secondShadowPaint.style = Paint.Style.FILL
            secondShadowPaint.color = secondShadowColor
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