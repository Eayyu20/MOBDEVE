package com.example.mobdeve

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
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
    private var p1CharSelect: Int = 1
    private var p2CharSelect: Int = 1

    private var p1LockStatus: Int = -1
    private var p2LockStatus: Int = -1

    private var p1Idle: Boolean = true
    private var p2Idle: Boolean = true

    private val swordDesc: String = "A strong fighter that harnesses the power of the sun to defeat his foes.\n\n[A] Normal Attack: Performs his ritual to deal 10 damage in a cone in front of him.\n\n[B] Special Attack: Releases a powerful slash, dealing 18 damage to foes in front of him."
    private val spearDesc: String = "Fierce and combative. She cleverly uses her nimbleness and long range to whittle down her opponents until she can land the final blow.\n\n[A] Normal Attack: Pierces in front of her to deal 8 damage.\n\n[B] Special Attack: Swings her spear upwards, covering more space to deal 14 damage."
    private val shieldDesc: String = "With a resilient body that has gone through countless of battles, he uses his wits to outsmart his opponents. Get too close to him and you'll learn what hurt means.\n\n[A] Normal Attack: Swings his shield to deal 12 damage.\n\n[B] Special Attack: Throws himself forward to deal 20 damage."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_select)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Initialize Sword as 1st choice
        findViewById<ImageView>(R.id.ivSwordP1).setBackgroundColor(Color.parseColor("#2187ab"))
        findViewById<ImageView>(R.id.ivSwordP2).setBackgroundColor(Color.parseColor("#943838"))
        findViewById<TextView>(R.id.tvCharacclassP1).text = "Sword"
        findViewById<TextView>(R.id.tvCharacclassP2).text = "Sword"
        findViewById<TextView>(R.id.tvCharacdescP1).text = swordDesc
        findViewById<TextView>(R.id.tvCharacdescP2).text = swordDesc

    }
    fun onCharSelect(v: View) {
        lateinit var charaView: ImageView
        lateinit var classname: String

        Log.e("TAG", v.id.toString())

        // if player is player 1
        if ((v.id === R.id.ivShieldP1) && (p1LockStatus == -1)) {
            this.p1CharSelect = 3

            // reset colors and highlight selected
            findViewById<ImageView>(R.id.ivSpearP1).setBackgroundColor(Color.parseColor("#9DC5F6"))
            findViewById<ImageView>(R.id.ivSwordP1).setBackgroundColor(Color.parseColor("#9DC5F6"))
            v.setBackgroundColor(Color.parseColor("#2187ab"))

            // show character
            charaView = findViewById<ImageView>(R.id.ivCharacviewP1)
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_standing_1, applicationContext.getTheme()))
            classname = "Shield"

            // show character details
            findViewById<TextView>(R.id.tvCharacclassP1).text = classname
            findViewById<TextView>(R.id.tvCharacdescP1).text = shieldDesc

        }
        else if ((v.id === R.id.ivSpearP1) && (p1LockStatus == -1)) {
            this.p1CharSelect = 2

            // reset colors and highlight selected
            findViewById<ImageView>(R.id.ivShieldP1).setBackgroundColor(Color.parseColor("#9DC5F6"))
            findViewById<ImageView>(R.id.ivSwordP1).setBackgroundColor(Color.parseColor("#9DC5F6"))
            v.setBackgroundColor(Color.parseColor("#2187ab"))

            // show character
            charaView = findViewById<ImageView>(R.id.ivCharacviewP1)
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_standing_1, applicationContext.getTheme()))
            classname = "Spear"

            // show character details
            findViewById<TextView>(R.id.tvCharacclassP1).text = classname
            findViewById<TextView>(R.id.tvCharacdescP1).text = spearDesc

        }
        else if ((v.id === R.id.ivSwordP1) && (p1LockStatus == -1)){
            this.p1CharSelect = 1

            // reset colors and highlight selected
            findViewById<ImageView>(R.id.ivShieldP1).setBackgroundColor(Color.parseColor("#9DC5F6"))
            findViewById<ImageView>(R.id.ivSpearP1).setBackgroundColor(Color.parseColor("#9DC5F6"))
            v.setBackgroundColor(Color.parseColor("#2187ab"))

            // show character
            charaView = findViewById<ImageView>(R.id.ivCharacviewP1)
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_standing_1, applicationContext.getTheme()))
            classname = "Sword"

            // show character details
            findViewById<TextView>(R.id.tvCharacclassP1).text = "Sword"
            findViewById<TextView>(R.id.tvCharacdescP1).text = swordDesc
        }
        // if player is player 2
        else if ((v.id === R.id.ivShieldP2) && (p2LockStatus == -1)){
            this.p2CharSelect = 3

            // reset colors and highlight selected
            findViewById<ImageView>(R.id.ivSpearP2).setBackgroundColor(Color.parseColor("#D39898"))
            findViewById<ImageView>(R.id.ivSwordP2).setBackgroundColor(Color.parseColor("#D39898"))
            v.setBackgroundColor(Color.parseColor("#943838"))

            // show character
            charaView = findViewById<ImageView>(R.id.ivCharacviewP2)
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_standing_1, applicationContext.getTheme()))
            classname = "Shield"

            // show character details
            findViewById<TextView>(R.id.tvCharacclassP2).text = classname
            findViewById<TextView>(R.id.tvCharacdescP2).text = shieldDesc
        }
        else if ((v.id === R.id.ivSpearP2) && (p2LockStatus == -1)) {
            this.p2CharSelect = 2

            // reset colors and highlight selected
            findViewById<ImageView>(R.id.ivShieldP2).setBackgroundColor(Color.parseColor("#D39898"))
            findViewById<ImageView>(R.id.ivSwordP2).setBackgroundColor(Color.parseColor("#D39898"))
            v.setBackgroundColor(Color.parseColor("#943838"))

            // show character
            charaView = findViewById<ImageView>(R.id.ivCharacviewP2)
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_standing_1, applicationContext.getTheme()))
            classname = "Spear"

            // show character details
            findViewById<TextView>(R.id.tvCharacclassP2).text = classname
            findViewById<TextView>(R.id.tvCharacdescP2).text = spearDesc

        }
        else if ((v.id === R.id.ivSwordP2) && (p2LockStatus == -1)) {
            this.p2CharSelect = 1

            // reset colors and highlight selected
            findViewById<ImageView>(R.id.ivShieldP2).setBackgroundColor(Color.parseColor("#D39898"))
            findViewById<ImageView>(R.id.ivSpearP2).setBackgroundColor(Color.parseColor("#D39898"))
            v.setBackgroundColor(Color.parseColor("#943838"))

            // show character
            charaView = findViewById<ImageView>(R.id.ivCharacviewP2)
            charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_standing_1, applicationContext.getTheme()))
            classname = "Sword"

            // show character details
            findViewById<TextView>(R.id.tvCharacclassP2).text = classname
            findViewById<TextView>(R.id.tvCharacdescP2).text = swordDesc
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
        Log.e("TAG", v.id.toString())
        if (v.id === R.id.ivCharacviewP1) {
            charaView = findViewById<ImageView>(R.id.ivCharacviewP1)
            classname = findViewById<TextView>(R.id.tvCharacclassP1).text.toString();
            Log.e("TAG", classname + " " + charaView)
        }
        else if (v.id === R.id.ivCharacviewP2) {
            charaView = findViewById<ImageView>(R.id.ivCharacviewP2)
            classname = findViewById<TextView>(R.id.tvCharacclassP2).text.toString();
            Log.e("TAG", classname + " " + charaView)
        }

        animateAttack(classname, charaView)
    }

    fun animateIdle(classname: String, charaView: ImageView){
        var handler = Handler()
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
        var handler = Handler()
        val nSpearframes = 4
        val nSwordframes = 6
        val nShieldframes = 3

        if (classname == "Spear"){
            for (i in 0..nSpearframes){
                Log.e("TAG", i.toString())
                handler.postDelayed(Runnable() {
                    if (i == 0) charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_standing_1, applicationContext.getTheme()))
                    else if (i == 1) charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_1, applicationContext.getTheme()))
                    else if (i == 2) charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_2, applicationContext.getTheme()))
                    else if (i == 3) charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_3, applicationContext.getTheme()))
                    else if (i == 4) charaView.setImageDrawable(getResources().getDrawable(R.drawable.spear_na_4, applicationContext.getTheme()))
               }, 250)
            }
        }
        else if (classname == "Sword"){
            for (i in 0..nSwordframes){
                Log.e("TAG", i.toString())
                handler.postDelayed(Runnable() {
                    if (i == 0) charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_standing_1, applicationContext.getTheme()))
                    else if (i == 1) charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_1, applicationContext.getTheme()))
                    else if (i == 2) charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_2, applicationContext.getTheme()))
                    else if (i == 3) charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_3, applicationContext.getTheme()))
                    else if (i == 4) charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_4, applicationContext.getTheme()))
                    else if (i == 5) charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_5, applicationContext.getTheme()))
                    else if (i == 6) charaView.setImageDrawable(getResources().getDrawable(R.drawable.sword_na_6, applicationContext.getTheme()))
               }, 166)
            }
        }
        else if (classname == "Shield"){
            for (i in 0..nShieldframes){
                handler.postDelayed(Runnable() {
                    if (i == 0) charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_standing_1, applicationContext.getTheme()))
                    else if (i == 1) charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_1, applicationContext.getTheme()))
                    else if (i == 2) charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_2, applicationContext.getTheme()))
                    else if (i == 3) charaView.setImageDrawable(getResources().getDrawable(R.drawable.shield_na_3, applicationContext.getTheme()))
               }, 333)
            }
        }
    }
    fun goBack(v: View){
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish()
    }
}