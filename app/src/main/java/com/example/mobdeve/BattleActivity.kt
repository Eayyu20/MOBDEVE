package com.example.mobdeve

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewTreeObserver
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

    var arena_height : Int = 0
    var arena_width : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_screen)

        val p1CharId = intent.getStringExtra("p1")
        val p2CharId = intent.getStringExtra("p2")

        p1 = Player(this, Integer.parseInt(p1CharId))
        p2 = Player(this, Integer.parseInt(p2CharId))

        // Initialize map
        arena = findViewById<SurfaceView>(R.id.arena)

        arena.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                arena_height = arena.width
                arena_width = arena.height

                // Remove the listener to avoid it being called multiple times
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    arena.viewTreeObserver.removeOnGlobalLayoutListener(this)
                } else {
                    arena.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }

                Log.w("Arena", arena.toString())
                Log.w("BA", "ah: " + arena_height.toString() + " aw: " + arena_width.toString())

                battle = Battle(p1, p2, arena_height, arena_width)
            }
        })

        player1AButton = findViewById<ImageView>(R.id.player1AButton)
        player1BButton = findViewById<ImageView>(R.id.player1BButton)
        player2AButton = findViewById<ImageView>(R.id.player2AButton)
        player2BButton = findViewById<ImageView>(R.id.player2BButton)

        player1Joystick = findViewById<Joystick>(R.id.player1joystick)
        player2Joystick = findViewById<Joystick>(R.id.player2joystick)

        Log.w("p1js", player1Joystick.toString() + " h: " + player1Joystick.height.toString() + " w: " + player1Joystick.width.toString())
    }

    fun end(v: View) {
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}