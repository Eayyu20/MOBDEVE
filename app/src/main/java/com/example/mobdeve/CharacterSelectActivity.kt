package com.example.mobdeve

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class CharacterSelectActivity : AppCompatActivity() {
    private var p1CharSelect: Int = -1
    private var p2CharSelect: Int = -1

    private var p1LockStatus: Int = -1
    private var p2LockStatus: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_select)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Toast.makeText(this, "Tip: Select your character and then lock when you're ready.", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Tip: Battle will commence once both players lock in.", Toast.LENGTH_LONG).show()
    }

    fun onCharSelect(v: View) {
        // if player is player 1
        if (v.id === R.id.ivShieldP1) {
            this.p1CharSelect = 3
        }
        else if (v.id === R.id.ivSpearP1) {
            this.p1CharSelect = 2
        }
        else if (v.id === R.id.ivSwordP1) {
            this.p1CharSelect = 1
        }
        // if player is player 2
        else if (v.id === R.id.ivShieldP2) {
            this.p2CharSelect = 3
        }
        else if (v.id === R.id.ivSpearP2) {
            this.p2CharSelect = 2
        }
        else if (v.id === R.id.ivSwordP2) {
            this.p2CharSelect = 1
        }
    }
    fun onLock(v: View) {
        // set player lock status to 1

        val btP1: Button = findViewById(R.id.btP1Lock)
        val btP2: Button = findViewById(R.id.btP2Lock)

        if (v.id === R.id.btP1Lock) {
            p1LockStatus *= -1
            if (p1LockStatus == -1){ btP1.text = "READY?" }
            else { btP1.text = "READY!" }
        }
        else if (v.id === R.id.btP2Lock) {
            p2LockStatus *= -1
            if (p2LockStatus == -1){ btP2.text = "READY?" }
            else { btP2.text = "READY!" }
        }

        if (p1LockStatus == 1 && p2LockStatus == 1 && p1CharSelect > 0 && p2CharSelect > 0) {
            val intent = Intent(this, BattleActivity::class.java)
            intent.putExtra("p1", p1CharSelect.toString())
            intent.putExtra("p2", p2CharSelect.toString())
            startActivity(intent)
            finish()
        }
    }

    fun goBack(v: View){
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}