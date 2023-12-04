package com.example.mobdeve

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class HP(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    var HPString: String = "HP: "

    fun updateHP(newHP: Int){
        HPString = "HP: ${newHP}"
        text = HPString
    }

}