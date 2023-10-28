package com.example.mobdeve

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TitleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title_screen)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        var scaledTopMargin = 0
        var scaledBotMargin = 0

        scaledTopMargin = (height * (0.75 / 5.0)).toInt()
        scaledBotMargin = (width * (1.0 / 20.0)).toInt()

        val title = findViewById<TextView>(R.id.tvTitle)
        val margin = title.layoutParams as ViewGroup.MarginLayoutParams
        margin.setMargins(0,scaledTopMargin,0,scaledBotMargin)
        title.layoutParams = margin

//        Toast.makeText(this, height.toString() + " x " + width.toString(), Toast.LENGTH_LONG).show()
    }

    fun toCharacterSelect(v: View){
        val i = Intent(this, CharacterSelectActivity::class.java)
        startActivity(i)
        finish()
    }
}