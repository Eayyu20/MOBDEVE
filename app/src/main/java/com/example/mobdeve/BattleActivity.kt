package com.example.mobdeve

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import java.lang.Exception
import com.example.mobdeve.Joystick

class BattleActivity : AppCompatActivity() {
    lateinit var battle: Battle
    lateinit var arena: SurfaceView

    lateinit var p1: Player
    lateinit var p2: Player

    lateinit var player1AButton: ImageView
    lateinit var player1BButton: ImageView
    lateinit var player2AButton: ImageView
    lateinit var player2BButton: ImageView
    lateinit var player1Joystick : Joystick
    lateinit var player2Joystick: Joystick

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_screen)

        val p1CharId = intent.getStringExtra("p1")
        val p2CharId = intent.getStringExtra("p2")

        this.p1 = Player(this, Integer.parseInt(p1CharId))
        this.p2 = Player(this, Integer.parseInt(p2CharId))

        this.battle = Battle(p1, p2, arena.height, arena.width)

        // Initialize map
        this.arena = findViewById<SurfaceView>(R.id.arena)

        this.player1AButton = findViewById<ImageView>(R.id.player1AButton)
        this.player1BButton = findViewById<ImageView>(R.id.player1BButton)
        this.player2AButton = findViewById<ImageView>(R.id.player2AButton)
        this.player2BButton = findViewById<ImageView>(R.id.player2BButton)

        this.player1Joystick = findViewById<Joystick>(R.id.player1joystick)
        this.player2Joystick = findViewById<Joystick>(R.id.player2joystick)
    }

    fun end(v: View) {
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}