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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BattleActivity : AppCompatActivity() {
    lateinit var battle: Battle
    lateinit var arena: Arena

    lateinit var p1: Player
    lateinit var p2: Player

    lateinit var player1AButton: ImageView
    lateinit var player1BButton: ImageView
    lateinit var player2AButton: ImageView
    lateinit var player2BButton: ImageView
    lateinit var player1Joystick : Joystick
    lateinit var player2Joystick: Joystick

    var p1JsAngle: Float = 0F
    var p1ABool: Boolean = false
    var p1BBool: Boolean = false
    var p2JsAngle: Float = 0F
    var p2ABool: Boolean = false
    var p2BBool: Boolean = false

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
        arena = findViewById<Arena>(R.id.arena)

        player1AButton = findViewById<ImageView>(R.id.player1AButton)
        player1BButton = findViewById<ImageView>(R.id.player1BButton)
        player2AButton = findViewById<ImageView>(R.id.player2AButton)
        player2BButton = findViewById<ImageView>(R.id.player2BButton)

        player1Joystick = findViewById<Joystick>(R.id.player1joystick)
        player2Joystick = findViewById<Joystick>(R.id.player2joystick)

        arena.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                arena_height = arena.height
                arena_width = arena.width

                p1.posX = (arena_width / 2F).toInt()
                p1.posY = (arena_height / 4F).toInt()
                p2.posX = (arena_width / 2F).toInt()
                p2.posY = (3 * arena_height / 4F).toInt()

                // Remove the listener to avoid it being called multiple times
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    arena.viewTreeObserver.removeOnGlobalLayoutListener(this)
                } else {
                    arena.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }

                battle = Battle(p1, p2, arena_height, arena_width)

                runLoopOnThread()
            }
        })
    }

    fun runLoopOnThread() {
        GlobalScope.launch(Dispatchers.Default) { // launch a new coroutine in background
            while (true) { // infinite loop
                p1JsAngle = player1Joystick.angle
                p2JsAngle = player2Joystick.angle

                battle.update(p1JsAngle, p2JsAngle, p1ABool, p1BBool, p2ABool, p2BBool)
                println("Running loop on thread: ${Thread.currentThread().name}")
                delay(1000) // non-blocking delay for 1 second (default time unit is ms)

                var bitmap = update()

                Log.w("BITMAP", bitmap.toString())

                arena.updateBitmap(bitmap)
            }
        }
    }

    fun update() : Bitmap {
        var bitmap: Bitmap = Bitmap.createBitmap(arena_width, arena_height, Bitmap.Config.ARGB_8888)
        var canvas: Canvas = Canvas(bitmap)

        val p1_current: Bitmap = p1.spriteSheet.getSprite(p1.currentAction,p1.actionFrame)
        canvas.drawBitmap(p1_current, (p1.posX).toFloat(), (p1.posY).toFloat(), null)

        val p2_current: Bitmap = p2.spriteSheet.getSprite(p2.currentAction,p2.actionFrame)
        canvas.drawBitmap(p2_current, (p2.posX).toFloat(), (p2.posY).toFloat(), null)

        // check if gameOver
        if (p1.hp <= 0 || p2.hp <= 0) {
            // draw game over screen
        }

        return bitmap
    }

    fun end(v: View) {
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}