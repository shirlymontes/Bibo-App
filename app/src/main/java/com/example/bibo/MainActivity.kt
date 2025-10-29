package com.example.bibo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.Html
import android.widget.TextView
import android.R.attr.button
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.buttonStart)

        startButton.setOnClickListener {
            val intent = Intent(this, AgeSelectionActivity::class.java)
            startActivity(intent)
        }

    }
}