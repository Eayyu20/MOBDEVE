package com.example.mobdeve

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Battle(var p1: Player, var p2: Player, val STAGE_SIZE_X: Int, val STAGE_SIZE_Y: Int) {
    init {
        this.p1.posX = (this.STAGE_SIZE_X / 2) - 5 // 5 here assumes that the hitbox is centered and half the sprite is 5 units
        this.p1.posY = this.STAGE_SIZE_Y - 50 // 50 units away from the edge

        this.p2.posX = this.STAGE_SIZE_X - this.p1.posX // should be mirror opposite of p1
        this.p2.posY = this.STAGE_SIZE_Y - this.p1.posY // should be mirror opposite of p1

        this.p1.currentAction = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death
        this.p2.currentAction = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death

        this.p1.actionFrame = 0 // frame count
        this.p1.actionFrame = 0 // frame count
    }

//    fun isColliding(context: Context): Boolean{
//        val sprite1X = this.p1.posX
//        val sprite1Y = this.p1.posY
//
//        val sprite2X = this.p2.posX
//        val sprite2Y = this.p2.posY
//
//        val player1Bitmap: Bitmap
//        val player2Bitmap: Bitmap

        // TO DO: Update based on sprite sheet implementation
        // get player 1 attack sprite dimensions
//        if(this.p1.charId == 1){
//            player1Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_1)
//        }else if(this.p1.charId == 2){
//            player1Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_2)
//        }else{
//            player1Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_3)
//        }
//
//        // get player 2 attack sprite dimensions
//        if(this.p2.charId == 1){
//            player2Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_1)
//        }else if(this.p2.charId == 2){
//            player2Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_2)
//        }else{
//            player2Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_3)
//        }
//
//        val sprite1Height = player1Bitmap.height
//        val sprite1Width = player1Bitmap.width
//
//        val sprite2Height = player2Bitmap.height
//        val sprite2Width = player2Bitmap.width
//
//        val rectSprite1 = Rect(sprite1X, sprite1Y, sprite1X + sprite1Width, sprite1Y + sprite1Height)
//        val rectSprite2 = Rect(sprite2X, sprite2Y, sprite2X + sprite2Width, sprite2Y + sprite2Height)
//
//        return Rect.intersects(rectSprite1, rectSprite2)
//    }
}