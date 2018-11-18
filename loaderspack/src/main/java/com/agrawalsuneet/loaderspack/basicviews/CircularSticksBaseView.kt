package com.agrawalsuneet.loaderspack.basicviews

import android.content.Context
import android.util.AttributeSet
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.contracts.CircularSticksAbstractView

/**
 * Created by suneet on 12/31/17.
 */

class CircularSticksBaseView : CircularSticksAbstractView {

    constructor(context: Context) : super(context) {
        initPaints()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initPaints()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initPaints()
    }

    constructor(context: Context, noOfSticks: Int, outerCircleRadius: Float, innerCircleRadius: Float, sticksColor: Int, viewBackgroundColor: Int) : super(context) {
        this.noOfSticks = noOfSticks
        this.outerCircleRadius = outerCircleRadius
        this.innerCircleRadius = innerCircleRadius
        this.sticksColor = sticksColor
        this.viewBackgroundColor = viewBackgroundColor
        initPaints()
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularSticksBaseView, 0, 0)

        this.noOfSticks = typedArray
                .getInteger(R.styleable.CircularSticksBaseView_sticksbase_noOfSticks, 80)

        this.outerCircleRadius = typedArray
                .getDimension(R.styleable.CircularSticksBaseView_sticksbase_outerCircleRadius, 200.0f)
        this.innerCircleRadius = typedArray
                .getDimension(R.styleable.CircularSticksBaseView_sticksbase_innerCircleRadius, 100.0f)


        this.sticksColor = typedArray
                .getColor(R.styleable.CircularSticksBaseView_sticksbase_stickColor, resources.getColor(android.R.color.darker_gray))
        this.viewBackgroundColor = typedArray
                .getColor(R.styleable.CircularSticksBaseView_sticksbase_viewBackgroundColor, resources.getColor(android.R.color.white))

        typedArray.recycle()
    }
}
