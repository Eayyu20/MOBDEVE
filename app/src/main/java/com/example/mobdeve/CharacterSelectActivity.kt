package com.example.mobdeve

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class CharacterSelectActivity : AppCompatActivity() {
    private var p1CharSelect: Int = -1
    private var p2CharSelect: Int = -1

    private var p1LockStatus: Int = -1
    private var p2LockStatus: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_select)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    fun onCharSelect(charId: Int) {
        // if player is player 1
        if (true) {
            p1CharSelect = charId
        }
        // if player is player 2
        else {
            p2CharSelect = charId
        }
    }
    fun onLock(v: View) {
        // set player lock status to 1

        val btP1: Button = findViewById(R.id.btP1Lock)
        val btP2: Button = findViewById(R.id.btP2Lock)

        if (v.id === R.id.btP1Lock) {
            p1LockStatus *= -1
            if (p1LockStatus == -1){ btP1.text = "LOCK" }
            else { btP1.text = "LOCKED" }
        }
        else if (v.id === R.id.btP2Lock) {
            p2LockStatus *= -1
            if (p2LockStatus == -1){ btP2.text = "LOCK" }
            else { btP2.text = "LOCKED" }
        }

        if (p1LockStatus == 1 && p2LockStatus == 1) {
            val i = Intent(this, BattleActivity::class.java)
            i.putExtra("p1Char", p1CharSelect)
            i.putExtra("p2Char", p2CharSelect)
            finish()
            startActivity(i)
        }
    }
}