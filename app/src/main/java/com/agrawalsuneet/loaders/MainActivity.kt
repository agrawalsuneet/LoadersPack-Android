package com.agrawalsuneet.loaders

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.agrawalsuneet.loaderspack.loaders.ClockLoader

class MainActivity : AppCompatActivity() {

    lateinit var containerLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        containerLayout = findViewById(R.id.container)

        initClockLoader();

    }

    private fun initClockLoader() {
        var clock = ClockLoader(this)
        containerLayout.addView(clock)
    }
}
