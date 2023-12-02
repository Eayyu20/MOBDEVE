package com.example.mobdeve

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

    fun isColliding(player1: Player, player2: Player, context: Context): Boolean{

        val sprite1X = player1.posX
        val sprite1Y = player1.posY

        val sprite2X = player2.posX
        val sprite2Y = player2.posY

        val player1Bitmap: Bitmap
        val player2Bitmap: Bitmap

        // get player 1 attack sprite dimensions
        if(player1.charId == 1){
            player1Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_1)
        }else if(player1.charId == 2){
            player1Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_2)
        }else{
            player1Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_3)
        }

        // get player 2 attack sprite dimensions
        if(player2.charId == 1){
            player2Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_1)
        }else if(player2.charId == 2){
            player2Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_2)
        }else{
            player2Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.standing_3)
        }

        val sprite1Height = player1Bitmap.height
        val sprite1Width = player1Bitmap.width

        val sprite2Height = player2Bitmap.height
        val sprite2Width = player2Bitmap.width

        val rectSprite1 = Rect(sprite1X, sprite1Y, sprite1X + sprite1Width, sprite1Y + sprite1Height)
        val rectSprite2 = Rect(sprite2X, sprite2Y, sprite2X + sprite2Width, sprite2Y + sprite2Height)

        return Rect.intersects(rectSprite1, rectSprite2)
    }