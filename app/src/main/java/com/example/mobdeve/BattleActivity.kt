package com.example.mobdeve

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BattleActivity : AppCompatActivity() {
    lateinit var battle: Battle
    lateinit var arena: Arena
    val ARENA_SIZE: Int = 720

    lateinit var p1: Player
    lateinit var p2: Player

    lateinit var player1AButton: ImageView
    lateinit var player1BButton: ImageView
    lateinit var player2AButton: ImageView
    lateinit var player2BButton: ImageView
    lateinit var player1Joystick : Joystick
    lateinit var player2Joystick: Joystick

    var p1ABool: Boolean = false
    var p1BBool: Boolean = false
    var p2ABool: Boolean = false
    var p2BBool: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_screen)

        val p1CharId = intent.getStringExtra("p1")
        val p2CharId = intent.getStringExtra("p2")

        p1 = Player(this, Integer.parseInt(p1CharId))
        p2 = Player(this, Integer.parseInt(p2CharId))

        // Initialize map
        arena = findViewById<Arena>(R.id.arena) 
        battle = Battle(p1, p2)

        player1AButton = findViewById<ImageView>(R.id.player1AButton)
        player1BButton = findViewById<ImageView>(R.id.player1BButton)
        player2AButton = findViewById<ImageView>(R.id.player2AButton)
        player2BButton = findViewById<ImageView>(R.id.player2BButton)

        player1AButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // The user touched the screen
                    p1ABool = true
                }
                MotionEvent.ACTION_UP -> {
                    // The user lifted their finger
                    p1ABool = false
                }
            }
            true
        }
        player1BButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // The user touched the screen
                    p1BBool = true
                }
                MotionEvent.ACTION_UP -> {
                    // The user lifted their finger
                    p1BBool = false
                }
            }
            true
        }
        player2AButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // The user touched the screen
                    p2ABool = true
                }
                MotionEvent.ACTION_UP -> {
                    // The user lifted their finger
                    p2ABool = false
                }
            }
            true
        }
        player2BButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // The user touched the screen
                    p2BBool = true
                }
                MotionEvent.ACTION_UP -> {
                    // The user lifted their finger
                    p2BBool = false
                }
            }
            true
        }

        player1Joystick = findViewById<Joystick>(R.id.player1joystick)
        player2Joystick = findViewById<Joystick>(R.id.player2joystick)

        runLoopOnThread()
    }

    fun runLoopOnThread() {
        GlobalScope.launch(Dispatchers.Default) { // launch a new coroutine in background
            while (true) { // infinite loop
                battle.update(player1Joystick.angle, player2Joystick.angle, p1ABool, p1BBool, p2ABool, p2BBool)
                delay(86) // non-blocking delay for 1 second (default time unit is ms)
                arena.updateBitmap(update())
            }
        }
    }

    fun update() : Bitmap {
        var bitmap: Bitmap = Bitmap.createBitmap(ARENA_SIZE,ARENA_SIZE, Bitmap.Config.ARGB_8888)
        var canvas: Canvas = Canvas(bitmap)

        var p1_current: Bitmap = getTile(p1)
        p1_current = flipY(p1_current)
        p1_current = rotate(p1_current, 90F)
        if (p1.angle > 0) p1_current = flipBitmap(p1_current)
        canvas.drawBitmap(p1_current, (p1.posX).toFloat(), (p1.posY).toFloat(), null)

        var p2_current: Bitmap = getTile(p2)
        p2_current = rotate(p2_current, 270F)
        if (p2.angle < 0) p2_current = flipBitmap(p2_current)
        canvas.drawBitmap(p2_current, (p2.posX).toFloat(), (p2.posY).toFloat(), null)

        // check if gameOver
        if (p1.hp <= 0 || p2.hp <= 0) {
            // draw game over screen
        }

        return bitmap
    }

    fun getTile(player: Player): Bitmap  {
        val currentAction = player.currentAction
        val actionFrame = player.actionFrame
        var spriteRow = currentAction
        var spriteCol = 0

        // prevent exceeding sprite dimensions
        if (currentAction > 4) spriteRow = 4

        // if moving or idle, as is works
        if (player.currentAction == 0) {
            spriteCol = actionFrame / 4
        }
        else if (player.currentAction == 1) {
            spriteCol = actionFrame / 2
        }
        // if take damage
        else if (player.currentAction == 5) {
            spriteRow = 4
            spriteCol = 0
        }
        // if death
        else if (player.currentAction == 4) {
            spriteCol = actionFrame / 4
        }
        // if normal attack
        else if (player.currentAction == 2) {
            if (player.charId == 1) {
                // wind up
                if (player.actionFrame / 4 < player.normal_attack.windupFC) {
                    spriteCol = 0
                }
                else if (player.actionFrame / 2 < player.normal_attack.windupFC) {
                    spriteCol = 1
                }
                // hit
                else if (player.actionFrame / 2 < player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = 2
                }
                // follow through
                else if (player.actionFrame / 2 > player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = (player.actionFrame / 2) % (player.normal_attack.windupFC + player.normal_attack.hitFC)
                }
            }
            else if (player.charId == 2) {
                // wind up
                if (player.actionFrame / 2 < player.normal_attack.windupFC) {
                    spriteCol = 0
                }
                // hit
                else if (player.actionFrame / 2 < player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = 1
                }
                // follow through
                else if (player.actionFrame / 2 > player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = 2
                }
            }
            else if (player.charId == 3) {
                // wind up
                if (player.actionFrame / 2 < player.normal_attack.windupFC) {
                    spriteCol = 0
                }
                // hit
                else if (player.actionFrame / 2 < player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = 1
                }
                // follow through
                else if (player.actionFrame / 2 > player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = 2
                }
            }
        }
        // if special attack
        else if (player.currentAction == 3) {
            if (player.charId == 1) {
                // wind up
                if (player.actionFrame / 4 < player.normal_attack.windupFC) {
                    spriteCol = 0
                }
                else if (player.actionFrame / 2 < player.normal_attack.windupFC) {
                    spriteCol = 1
                }
                // hit
                else if (player.actionFrame / 2 < player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = 2
                }
                // follow through
                else if (player.actionFrame / 2 > player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = (player.actionFrame / 2) % (player.normal_attack.windupFC + player.normal_attack.hitFC)
                }
            }
            else if (player.charId == 2) {
                // wind up
                if (player.actionFrame / 2 < player.normal_attack.windupFC) {
                    spriteCol = 1
                }
                // hit
                else if (player.actionFrame / 2 < player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = 3
                }
                // follow through
                else if (player.actionFrame / 2 > player.normal_attack.windupFC + player.normal_attack.hitFC) {
                    spriteCol = 4
                }
            }
            else if (player.charId == 3) {
                // hit
                if (player.actionFrame / 2 < player.normal_attack.hitFC) {
                    spriteCol = 0
                }
                // follow through
                else if (player.actionFrame / 2 > player.normal_attack.hitFC) {
                    spriteCol = 1
                }
            }
        }


        return player.spriteSheet.getSprite(spriteRow, spriteCol)
    }

    fun flipBitmap(source: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.postScale(1F, -1F, source.getWidth() / 2f, source.getHeight() / 2f)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    fun rotate(source: Bitmap, rotate: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(90F)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    fun flipY(source: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.postScale(-1F, 1F, source.getWidth() / 2f, source.getHeight() / 2f)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    fun end(v: View) {
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}