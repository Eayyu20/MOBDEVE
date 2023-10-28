package com.example.mobdeve

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast

class BattleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_screen)

        val p1CharId = intent.getStringExtra("p1")
        val p2CharId = intent.getStringExtra("p2")

        var p1 : Player = Player(Integer.parseInt(p1CharId))
        var p2 : Player = Player(Integer.parseInt(p2CharId))

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels


//         main game loop
//        while (true) {
//
//        }

    }
}