package com.example.mobdeve

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat

// This file should only instantiate the different classes and variables as well as hold the main loop.
// think of it as the controller that ensures the correct data can be passed to from the model to the gui
// updating the canvas object needs to be done in BattleDisplay.kt
// updating player data needs to be done via Battle.kt

class BattleActivity : AppCompatActivity() {
    lateinit var battle: Battle
    lateinit var display: BattleDisplay

    lateinit var mCanvas: Canvas
    lateinit var mPaint: Paint
    lateinit var mBitmap: Bitmap

    var mColorBackground = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_screen)

        val p1CharId = intent.getStringExtra("p1")
        val p2CharId = intent.getStringExtra("p2")

        var p1 : Player = Player(this, Integer.parseInt(p1CharId))
        var p2 : Player = Player(this, Integer.parseInt(p2CharId))

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val displayMetrics = DisplayMetrics()
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        val arena_height = 0
        val arena_width = 0

        this.battle = Battle(p1, p2, arena_height, arena_width)

        // Initialize arena Canvas
        val ivArena = findViewById<ImageView>(R.id.ivArena)
        this.mBitmap = Bitmap.createBitmap(width, height/3, Bitmap.Config.ARGB_8888)
        ivArena.setImageBitmap(mBitmap)
        mCanvas = Canvas(mBitmap)
        mCanvas.drawColor(Color.BLUE)

//        this.display = BattleDisplay(this.battle)

//         main game loop
//        while (true) {
//
//        }

    }


    fun end(v: View){
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}