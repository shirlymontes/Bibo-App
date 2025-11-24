package com.example.bibo

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AgeSelectionActivity : AppCompatActivity() {

    private lateinit var biboImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_selection)

        biboImage = findViewById(R.id.biboImage)
        val btn3to5 = findViewById<Button>(R.id.buttonAge3to5)
        val btn6to8 = findViewById<Button>(R.id.buttonAge6to8)
        val btn9to12 = findViewById<Button>(R.id.buttonAge9to12)

        setupButtonAction(btn3to5, R.drawable.purplebibo3to5, HomeAge3to5::class.java)
        setupButtonAction(btn6to8, R.drawable.purplebibo6to8, HomeAge6to8::class.java)
        setupButtonAction(btn9to12, R.drawable.purplebibo9to12, HomeAge9to12::class.java)
    }


    override fun onResume() {
        super.onResume()
        biboImage.setImageResource(R.drawable.purplebibo_unselected)
    }

    private fun setupButtonAction(
        button: Button,
        imageRes: Int,
        targetActivity: Class<*>
    ) {
        button.setOnClickListener {

            biboImage.setImageResource(imageRes)

            animateBibo(biboImage) {
                val intent = Intent(this, targetActivity)
                startActivity(intent)
            }
        }
    }

    private fun animateBibo(bibo: ImageView, onEnd: () -> Unit) {
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.1f, 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.3f, 1f)
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
