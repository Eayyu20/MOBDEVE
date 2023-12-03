package com.example.mobdeve

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class SpriteSheet (context: Context, char_type: Int){
    val SPRITE_WIDTH_PIXELS : Int = 64
    val SPRITE_HEIGHT_PIXELS : Int = 64
    var bitmap: Bitmap

    init {
        var bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inScaled = false
        if (char_type == 1) {
            this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword_spritesheet, bitmapOptions)
        }
        else if (char_type == 2) {
            this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spear_spritesheet, bitmapOptions)
        }
        else if (char_type == 3) {
            this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.shield_spritesheet, bitmapOptions)
        }
        // if error, default to sword
        else {
            this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword_spritesheet, bitmapOptions)
        }
    }

    fun getSprite(idxRow: Int, idxCol: Int): Bitmap {
        return Bitmap.createBitmap(bitmap, idxCol * SPRITE_WIDTH_PIXELS, idxRow * SPRITE_HEIGHT_PIXELS, SPRITE_WIDTH_PIXELS, SPRITE_HEIGHT_PIXELS)
    }
}