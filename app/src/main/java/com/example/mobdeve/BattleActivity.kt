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
import android.view.SurfaceView
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat

class BattleActivity : AppCompatActivity() {
    lateinit var battle: Battle

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
        val svArena = findViewById<SurfaceView>(R.id.svArena)

    }

    fun end(v: View) {
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}