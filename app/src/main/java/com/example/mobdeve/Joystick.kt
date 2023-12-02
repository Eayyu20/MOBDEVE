package com.example.mobdeve

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class Joystick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SurfaceView(context, attrs), SurfaceHolder.Callback, Runnable{

    private var joystickBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.joystick)
    private var posX: Int  = 0
    private var posY: Int = 0
    init{
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder){
        setBitmapPosition()
        Thread(this).start()
    }

    override fun run() {
        while(true){
            val canvas = holder.lockCanvas()
            if(canvas != null){
                Log.e("TAG", "CANVAS OKAY")
                drawJoystick(canvas)
                holder.unlockCanvasAndPost(canvas)
            }else{
                Log.e("TAG", "NO CANVAS")
            }
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int){

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    private fun drawJoystick(canvas: Canvas){
        canvas.drawBitmap(joystickBitmap, posX.toFloat(), posY.toFloat(), null)
    }

    private fun setBitmapPosition() {
        val centerX = holder.surfaceFrame.width() / 2
        val centerY = holder.surfaceFrame.height() / 2
        posX = centerX - joystickBitmap.width / 2
        posY = centerY - joystickBitmap.height / 2
    }
}