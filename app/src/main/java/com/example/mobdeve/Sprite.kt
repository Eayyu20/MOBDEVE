package com.example.mobdeve

import android.R.attr
import android.graphics.Canvas
import android.graphics.Rect


class Sprite (val spriteSheet : SpriteSheet, val rect : Rect){
    fun draw(canvas: Canvas, x: Int, y: Int) {
        canvas.drawBitmap(
            this.spriteSheet.getBM(),
            rect,
            Rect(attr.x, attr.y, attr.x + getWidth(), attr.y + getHeight()),
            null
        )
    }

    fun getWidth(): Int {
        return rect.width()
    }

    fun getHeight(): Int {
        return rect.height()
    }
}