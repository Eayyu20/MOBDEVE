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

    fun getBM(): Bitmap {
        return this.bitmap
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