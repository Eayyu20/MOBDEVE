package com.example.mobdeve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BattleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_select)

        var bundle : Bundle ?= intent.extras // not sure if this is the correct syntax. pls check

        val p1CharId : Int = bundle!!.getString("p1Char").toString().toInt()
        val p2CharId : Int = bundle!!.getString("p2Char").toString().toInt()

        var p1 : Player = Player(p1CharId)
        var p2 : Player = Player(p2CharId)

        // main game loop
        while (true) {

        }
    }
}

class Player {
    var stocks: Int = 3
    var posX: Float = 0F
    var posY: Float = 0F
    var currentAction = "idle"
    var actionFrame: Int = 0
    var charId = 0
    lateinit var normal_attack : Attack
    lateinit var special_attack : Attack

    constructor(charId: Int) {
        this.charId = charId

        if (charId == 1) {

        }
    }
}

class Attack {
    var windupFC : Int = 0
    var hitFC : Int = 0
    var followthroughFC : Int = 0

    constructor(windupFC: Int, hitFC: Int, followthroughFC: Int) {
        this.windupFC = windupFC
        this.hitFC = hitFC
        this.followthroughFC = followthroughFC
    }
}