package com.example.mobdeve

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CharacterSelectActivity : AppCompatActivity() {
    private var p1CharSelect: Int = -1
    private var p2CharSelect: Int = -1

    private var p1LockStatus: Int = -1
    private var p2LockStatus: Int = -1

    private var p1Idle: Boolean = true
    private var p2Idle: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_select)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    fun onCharSelect(v: View) {
        lateinit var charaView: ImageView
        lateinit var classname: String

        // if player is player 1
        if (v.id === R.id.ivShieldP1) {
            this.p1CharSelect = 3
            charaView = findViewById<ImageView>(R.id.ivCharacviewP1)
            classname = "Shield"
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_standing_1, applicationContext.getTheme()));

        }
        else if (v.id === R.id.ivSpearP1) {
            this.p1CharSelect = 2
            charaView = findViewById<ImageView>(R.id.ivCharacviewP1)
            classname = "Spear"
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_standing_1, applicationContext.getTheme()));
        }
        else if (v.id === R.id.ivSwordP1) {
            this.p1CharSelect = 1
            charaView = findViewById<ImageView>(R.id.ivCharacviewP1)
            classname = "Sword"
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_standing_1, applicationContext.getTheme()));
        }
        // if player is player 2
        else if (v.id === R.id.ivShieldP2) {
            this.p2CharSelect = 3
            charaView = findViewById<ImageView>(R.id.ivCharacviewP2)
            classname = "Shield"
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_standing_1, applicationContext.getTheme()));
        }
        else if (v.id === R.id.ivSpearP2) {
            this.p2CharSelect = 2
            charaView = findViewById<ImageView>(R.id.ivCharacviewP2)
            classname = "Spear"
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_standing_1, applicationContext.getTheme()));
        }
        else if (v.id === R.id.ivSwordP2) {
            this.p2CharSelect = 1
            charaView = findViewById<ImageView>(R.id.ivCharacviewP2)
            classname = "Sword"
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_standing_1, applicationContext.getTheme()));
        }

//        animateIdle(classname, charaView)
    }
    fun onLock(v: View) {
        // set player lock status to 1

        val btP1: Button = findViewById(R.id.btP1Lock)
        val btP2: Button = findViewById(R.id.btP2Lock)

        if (v.id === R.id.btP1Lock) {
            p1LockStatus *= -1
            if (p1LockStatus == -1){ btP1.text = "LOCK" }
            else { btP1.text = "READY!" }
        }
        else if (v.id === R.id.btP2Lock) {
            p2LockStatus *= -1
            if (p2LockStatus == -1){ btP2.text = "LOCK" }
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

    fun Attack (v: View) {
        lateinit var charaView: ImageView
        lateinit var classname: String

        if (v.id === R.id.ivCharacviewP1) {
            charaView = findViewById<ImageView>(R.id.ivCharacviewP1)
            classname = findViewById<TextView>(R.id.tvCharacclassP1).text.toString();
            Log.e("TAG", classname + " " + charaView)
        }
//        else if (v.id === R.id.ivCharacviewP2) {
//            charaView = findViewById<ImageView>(R.id.ivCharacviewP2)
//            classname = findViewById<TextView>(R.id.tvCharacclassP2).text.toString();
//        }

        animateAttack(classname, charaView)
    }

    fun animateIdle(classname: String, charaView: ImageView){
        val handler = Handler()
        if (classname == "Spear"){
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_standing_1, applicationContext.getTheme()));
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_1, applicationContext.getTheme()));
            }, 250)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_2, applicationContext.getTheme()));
            }, 250)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_3, applicationContext.getTheme()));
            }, 250)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_4, applicationContext.getTheme()));
            }, 250)
        }
        else if (classname == "Sword"){
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_standing_1, applicationContext.getTheme()));
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_1, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_2, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_3, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_4, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_5, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_6, applicationContext.getTheme()));
            }, 166)
        }
        else if (classname == "Shield"){
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_standing_1, applicationContext.getTheme()))
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_1, applicationContext.getTheme()));
            }, 333)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_2, applicationContext.getTheme()));
            }, 333)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_3, applicationContext.getTheme()));
            }, 333)
        }
    }
    fun animateAttack (classname: String, charaView: ImageView) {
        val handler = Handler()
        if (classname == "Spear"){
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_standing_1, applicationContext.getTheme()));
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_1, applicationContext.getTheme()));
            }, 250)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_2, applicationContext.getTheme()));
            }, 250)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_3, applicationContext.getTheme()));
            }, 250)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_4, applicationContext.getTheme()));
            }, 250)
        }
        else if (classname == "Sword"){
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_standing_1, applicationContext.getTheme()));
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_1, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_2, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_3, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_4, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_5, applicationContext.getTheme()));
            }, 166)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_6, applicationContext.getTheme()));
            }, 166)
        }
        else if (classname == "Shield"){
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_standing_1, applicationContext.getTheme()))
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_1, applicationContext.getTheme()));
            }, 333)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_2, applicationContext.getTheme()));
            }, 333)
            handler.postDelayed(Runnable() {
                charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_3, applicationContext.getTheme()));
            }, 333)
        }
    }

    fun goBack(v: View){
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}