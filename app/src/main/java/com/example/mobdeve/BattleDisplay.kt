package com.example.mobdeve

import android.R.attr.x
import android.R.attr.y
import android.graphics.Canvas
import android.graphics.Rect
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth


class BattleDisplay (var battle: Battle){
    var p1 = battle.p1
    var p2 = battle.p2

    val STAGE_SIZE_X: Int = battle.STAGE_SIZE_X
    val STAGE_SIZE_Y: Int = battle.STAGE_SIZE_Y

    val p1ss: SpriteSheet = p1.spriteSheet
    val p2ss: SpriteSheet = p2.spriteSheet

    fun update(canvas: Canvas) {
        // draw p1 - check for direction, action type, frame count, position
        // draw p2 - check for direction, action type, frame count, position
    }
}