package com.example.mobdeve

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView

class Arena(context: Context, attrs: AttributeSet): SurfaceView(context, attrs), SurfaceHolder.Callback {

    private lateinit var canvas: Canvas
    private var bitmap: Bitmap? = null

    init{
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawBitmap()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Handle surface changes if needed
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // Handle surface destruction if needed
    }

    fun drawBitmap() {
        bitmap?.let {
            val canvas = holder.lockCanvas()
            if (canvas != null) {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                canvas.drawBitmap(it, 0F, 0F, null)
                holder.unlockCanvasAndPost(canvas)
            }
        }
    }

    fun updateBitmap(bitmap: Bitmap){
        this.bitmap = bitmap
        drawBitmap()
    }
}