package com.example.mobdeve

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View

// This file should only instantiate the different classes and variables as well as hold the main loop.
// think of it as the controller that ensures the correct data can be passed to from the model to the gui
// updating the canvas object needs to be done in BattleDisplay.kt
// updating player data needs to be done via Battle.kt

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

        this.battle = Battle(p1, p2, height, width)

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