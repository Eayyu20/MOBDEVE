package com.example.mobdeve

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView

class Arena(context: Context, attrs: AttributeSet): SurfaceView(context, attrs), SurfaceHolder.Callback {
    private lateinit var canvas: Canvas
    init{
        holder.addCallback(this)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        canvas = holder.lockCanvas()
        holder.unlockCanvasAndPost(canvas)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Handle surface changes if needed
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // Handle surface destruction if nee
    }

    fun renderCanvas(bitmap: Bitmap) {
        Log.e("Surface Canvas", "Called Render")
        var surfaceCanvas = holder.lockCanvas() // surfaceCanvas is a null object
        if (surfaceCanvas == null) return
        Log.e("Surface Canvas", "Not NUll")
        surfaceCanvas.drawBitmap(bitmap, 0f, 0f, null)
        holder.unlockCanvasAndPost(surfaceCanvas)
    }
}