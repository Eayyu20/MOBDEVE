package com.example.mobdeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class BattleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_screen)

        val p1CharId = intent.getStringExtra("p1")
        val p2CharId = intent.getStringExtra("p2")

        var p1 : Player = Player(Integer.parseInt(p1CharId))
        var p2 : Player = Player(Integer.parseInt(p2CharId))

//         main game loop
//        while (true) {
//
//        }
    }
}