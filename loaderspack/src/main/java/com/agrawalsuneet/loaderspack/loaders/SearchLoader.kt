package com.agrawalsuneet.loaderspack.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.*
import android.widget.RelativeLayout
import com.agrawalsuneet.dotsloader.utils.random
import com.agrawalsuneet.loaderspack.R
import com.agrawalsuneet.loaderspack.basicviews.LoaderContract
import com.agrawalsuneet.loaderspack.basicviews.MagnifyingGlassView

/**
 * Created by agrawalsuneet on 11/2/18.
 */

class SearchLoader : RelativeLayout, LoaderContract {

    var lensRadius: Int = 50
    var lensBorderWidth: Int = 20

    var lensHandleLength: Int = 80

    var lensColor: Int = resources.getColor(android.R.color.holo_green_light)

    var xRangeToSearch: Int = 400
    var yRangeToSearch: Int = 400

    var defaultStartLoading: Boolean = true

    private lateinit var magnifyingGlassView: MagnifyingGlassView

    private var xCor: Float = 0.0f
    private var yCor: Float = 0.0f


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initView()
    }

    constructor(context: Context?, lensRadius: Int, lensBorderWidth: Int, lensHandleLength: Int, lensColor: Int, defaultStartLoading: Boolean) : super(context) {
        this.lensRadius = lensRadius
        this.lensBorderWidth = lensBorderWidth
        this.lensHandleLength = lensHandleLength
        this.lensColor = lensColor
        this.defaultStartLoading = defaultStartLoading
        initView()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(xRangeToSearch, yRangeToSearch)
    }


    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchLoader, 0, 0)

        this.lensRadius = typedArray
                .getDimensionPixelSize(R.styleable.SearchLoader_search_lensRadius, 50)

        this.lensBorderWidth = typedArray
                .getDimensionPixelSize(R.styleable.SearchLoader_search_lensBorderWidth, 20)

        this.lensHandleLength = typedArray
                .getDimensionPixelSize(R.styleable.SearchLoader_search_lensHandleLength, 80)


        this.lensColor = typedArray
                .getColor(R.styleable.SearchLoader_search_lensColor, resources.getColor(android.R.color.holo_green_light))

        this.xRangeToSearch = typedArray
                .getDimensionPixelSize(R.styleable.SearchLoader_search_xRangeToSearch, 400)

        this.yRangeToSearch = typedArray
                .getDimensionPixelSize(R.styleable.SearchLoader_search_yRangeToSearch, 400)

        this.defaultStartLoading = typedArray
                .getBoolean(R.styleable.GaugeLoader_gauge_defaultStartLoading, true)

        typedArray.recycle()
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        magnifyingGlassView = MagnifyingGlassView(context, lensRadius, lensBorderWidth, lensHandleLength, lensColor)

        val lensParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        lensParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        addView(magnifyingGlassView, lensParams)


        if (defaultStartLoading) {

            val viewTreeObserver = this.viewTreeObserver
            val loaderView = this

            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    startLoading(null)

                    val vto = loaderView.viewTreeObserver
                    vto.removeOnGlobalLayoutListener(this)
                }
            })
        }
    }

    fun startLoading(animation: Animation?) {

        var translateAnim = animation

        if (translateAnim == null) {
            translateAnim = getTranslateAnimation()

            translateAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(anim: Animation?) {
                }

                override fun onAnimationEnd(anim: Animation?) {
                    startLoading(null)
                }

                override fun onAnimationStart(anim: Animation?) {
                }
            })
        }

        magnifyingGlassView.startAnimation(translateAnim)
    }

    private fun getTranslateAnimation(): TranslateAnimation {

        val animDuration = (500..1000).random()

        val toXCor = (0..(xRangeToSearch - magnifyingGlassView.width)).random().toFloat()
        val toYCor = (0..(yRangeToSearch - magnifyingGlassView.height)).random().toFloat()

        val anim = TranslateAnimation(xCor, toXCor, yCor, toYCor)

        anim.duration = animDuration.toLong()
        anim.repeatCount = 0
        anim.fillAfter = true

        val random = (0..3).random()
        //val random = 0
        when (random) {
            0 -> anim.interpolator = LinearInterpolator()
            1 -> anim.interpolator = AccelerateInterpolator()
            2 -> anim.interpolator = DecelerateInterpolator()
            3 -> anim.interpolator = AccelerateDecelerateInterpolator()
        }

        xCor = toXCor
        yCor = toYCor

        return anim
    }
}
