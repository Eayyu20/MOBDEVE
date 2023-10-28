package com.example.mobdeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BattleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_screen)

//        var bundle : Bundle? = intent.extras // not sure if this is the correct syntax. pls check
//
//        val p1CharId : Int = bundle!!.getString("p1Char").toString().toInt()
//        val p2CharId : Int = bundle!!.getString("p2Char").toString().toInt()
//
//        var p1 : Player = Player(p1CharId)
//        var p2 : Player = Player(p2CharId)

        // main game loop
//        while (true) {
//
//        }
    }
}