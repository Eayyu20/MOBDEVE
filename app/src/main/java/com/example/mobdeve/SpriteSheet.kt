package com.example.mobdeve

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import androidx.core.graphics.times


class SpriteSheet (context: Context){
    val SPRITE_WIDTH_PIXELS : Int = 0
    val SPRITE_HEIGHT_PIXELS : Int = 0
    var bitmap: Bitmap

    init {
        var bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inScaled = false
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spear_spritesheet, bitmapOptions)
    }

    fun getBitmap(): Bitmap {
        return bitmap
    }

    fun getSprite(idxRow: Int, idxCol: Int): Sprite {
        return Sprite(this, Rect(idxCol * SPRITE_WIDTH_PIXELS, idxRow * SPRITE_HEIGHT_PIXELS,(idxCol + 1) * SPRITE_WIDTH_PIXELS,(idxRow + 1) * SPRITE_HEIGHT_PIXELS))
    }

    fun getIdleSprite(frame_num: Int): Sprite {
        return Sprite(this, Rect(frame_num * SPRITE_WIDTH_PIXELS, 0  * SPRITE_HEIGHT_PIXELS, (frame_num + 1) * SPRITE_WIDTH_PIXELS, (0 + 1) * SPRITE_HEIGHT_PIXELS))
    }

    fun getMovingSprite(frame_num: Int): Sprite {
        return Sprite(this, Rect(frame_num * SPRITE_WIDTH_PIXELS, 1  * SPRITE_HEIGHT_PIXELS, (frame_num + 1) * SPRITE_WIDTH_PIXELS, (1 + 1) * SPRITE_HEIGHT_PIXELS))
    }

    fun getNormalAttackSprite(frame_num: Int): Sprite {
        return Sprite(this, Rect(frame_num * SPRITE_WIDTH_PIXELS, 2  * SPRITE_HEIGHT_PIXELS, (frame_num + 1) * SPRITE_WIDTH_PIXELS, (2 + 1) * SPRITE_HEIGHT_PIXELS))
    }

    fun getSpecialAttackSprite(frame_num: Int): Sprite {
        return Sprite(this, Rect(frame_num * SPRITE_WIDTH_PIXELS, 3  * SPRITE_HEIGHT_PIXELS, (frame_num + 1) * SPRITE_WIDTH_PIXELS, (3 + 1) * SPRITE_HEIGHT_PIXELS))
    }

    fun getDeathSprite(frame_num: Int): Sprite {
        return Sprite(this, Rect(frame_num * SPRITE_WIDTH_PIXELS, 4  * SPRITE_HEIGHT_PIXELS, (frame_num + 1) * SPRITE_WIDTH_PIXELS, (4 + 1) * SPRITE_HEIGHT_PIXELS))
    }
}