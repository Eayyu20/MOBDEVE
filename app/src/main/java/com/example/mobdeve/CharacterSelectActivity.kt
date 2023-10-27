package com.example.mobdeve

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CharacterSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_select)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}