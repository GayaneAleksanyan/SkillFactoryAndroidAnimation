package com.aleksanyan.animationsexamples

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aleksanyan.animationsexamples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPanel
            .animate()
            .setDuration(2000)
            .alpha(1f)
            .start()

        val translationPVH = PropertyValuesHolder.ofFloat("1", 0f, 200f)
        val alphaPVH = PropertyValuesHolder.ofFloat("2", 0f, 1f)


        val animation = AnimationUtils.loadAnimation(this, R.anim.my_animation)

        ValueAnimator.ofFloat(0f, 200f).apply {
            duration = 2000
            addUpdateListener {
                val translation = it.getAnimatedValue("1") as Float
                val alpha = it.getAnimatedValue("2") as Float
                binding.buttonPanel.x = it.animatedValue as Float
            }.doOnEnd {
                Toast.makeText(this@MainActivity, "111", Toast.LENGTH_SHORT).show()
            }
        }.start()

        fun exampleKeyframes() {
            val delta: Int = 200
            val pvhTranslateX = PropertyValuesHolder.ofKeyframe(
                View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(10f, delta.toFloat()),
                Keyframe.ofFloat(1f, delta.toFloat())
            )
        }
        exampleKeyframes()

        ObjectAnimator.ofPropertyValuesHolder(binding.buttonPanel, translationPVH, alphaPVH).apply {
            duration = 2500
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
        }.start()


        val clickButton = findViewById<Button>(R.id.button_one)
        clickButton.setOnClickListener {
            clickButton.startAnimation(animation)
        }

        val ad = resources.getDrawable(R.drawable.frame_animation) as AnimationDrawable
        ad.start()

    }
}

private fun Any.doOnEnd(function: () -> Unit) {

}
