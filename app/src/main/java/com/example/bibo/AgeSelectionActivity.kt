package com.example.bibo

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AgeSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_selection)

        val biboImage = findViewById<ImageView>(R.id.biboImage)
        val btn3to5 = findViewById<Button>(R.id.buttonAge3to5)
        val btn6to8 = findViewById<Button>(R.id.buttonAge6to8)
        val btn9to12 = findViewById<Button>(R.id.buttonAge9to12)

        setupButtonAction(btn3to5, biboImage, R.drawable.purplebibo3to5, HomeAge3to5::class.java)
        setupButtonAction(btn6to8, biboImage, R.drawable.purplebibo6to8, HomeAge6to8::class.java)
        setupButtonAction(btn9to12, biboImage, R.drawable.purplebibo9to12, HomeAge9to12::class.java)
    }

    private fun setupButtonAction(
        button: Button,
        bibo: ImageView,
        imageRes: Int,
        targetActivity: Class<*>
    ) {
        button.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    bibo.setImageResource(imageRes)
                    animateBibo(bibo) {
                        // After animation ends, go to the target screen
                        val intent = Intent(this, targetActivity)
                        startActivity(intent)
                    }
                }

            }
            true
        }
    }

    private fun animateBibo(bibo: ImageView, onEnd: () -> Unit) {
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.3f, 1f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.1f, 1f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(bibo, scaleX, scaleY)
        animator.duration = 350
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) { onEnd() }
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        animator.start()
    }
}
