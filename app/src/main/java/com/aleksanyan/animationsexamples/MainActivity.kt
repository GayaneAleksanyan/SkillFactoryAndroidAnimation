package com.aleksanyan.animationsexamples

import android.graphics.drawable.AnimationDrawable
import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(this, R.anim.my_animation)

        val clickButton = findViewById<Button>(R.id.button_one)
        clickButton.setOnClickListener {
           clickButton.startAnimation(animation)
        }

        val ad = resources.getDrawable(R.drawable.frame_animation) as AnimationDrawable
        ad.start()

    }
}
