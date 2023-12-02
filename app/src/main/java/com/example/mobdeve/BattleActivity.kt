package com.example.mobdeve

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.example.mobdeve.isColliding
import android.widget.Toast

class BattleActivity : AppCompatActivity() {
    lateinit var battle: Battle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_screen)

        val p1CharId = intent.getStringExtra("p1")
        val p2CharId = intent.getStringExtra("p2")

        var p1 : Player = Player(Integer.parseInt(p1CharId))
        var p2 : Player = Player(Integer.parseInt(p2CharId))

        this.battle = Battle(p1, p2)

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
    
    fun end(v: View){
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}