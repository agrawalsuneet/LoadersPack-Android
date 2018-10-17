package com.agrawalsuneet.loaderspack.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.R.attr.*

class NeedleView : View {

    var needleLength: Int = 160
    var needleWidth: Int = 20
    var needleJointRadius: Int = 40

    var needleColor: Int = resources.getColor(android.R.color.holo_red_dark)

    private val paint: Paint = Paint()

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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NeedleView, 0, 0)

        this.needleLength = typedArray.getDimensionPixelSize(R.styleable.NeedleView_needleLength, 160)
        this.needleWidth = typedArray.getDimensionPixelSize(R.styleable.NeedleView_needleWidth, 20)

        this.needleJointRadius = typedArray.getDimensionPixelSize(R.styleable.NeedleView_needleWidth, 140)

        this.needleColor = typedArray.getColor(R.styleable.NeedleView_needleColor, resources.getColor(android.R.color.holo_red_dark))
        typedArray.recycle()
    }

    private fun initValues() {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.strokeWidth = needleWidth.toFloat()
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = needleColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val calWidth = 2 * needleJointRadius
        val calHeight = (2 * needleJointRadius) + needleLength
        setMeasuredDimension(calWidth, calHeight)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //joint circle
        canvas.drawCircle(needleJointRadius.toFloat(), (needleJointRadius + needleLength).toFloat(), needleJointRadius.toFloat(), paint)

        //needle line
        canvas.drawLine(needleJointRadius.toFloat(), (needleWidth / 2).toFloat(), needleJointRadius.toFloat(), needleLength.toFloat(), paint)
    }
}