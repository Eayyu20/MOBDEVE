package com.example.mobdeve

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView

class Arena(context: Context, attrs: AttributeSet): SurfaceView(context, attrs), SurfaceHolder.Callback {

    private lateinit var canvas: Canvas
    private var bitmap: Bitmap? = null
    var onClick: (() -> Unit)? = null

    init{
        setWillNotDraw(false)
        holder.addCallback(this)
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSPARENT)
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

    fun pauseBitmap(){
        val canvas = holder.lockCanvas()
        if (canvas != null) {
            var image = BitmapFactory.decodeResource(resources, R.drawable.pause)

            val scaledWidth = image.width / 4
            val scaledHeight = image.height / 4
            val scaledImage = Bitmap.createScaledBitmap(image, scaledWidth, scaledHeight, true)

            val x = (canvas.width - scaledImage.width) / 2
            val y = (canvas.height - scaledImage.height) / 2

            val paint = Paint()
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            canvas.drawPaint(paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)

            canvas.drawBitmap(scaledImage, x.toFloat(), y.toFloat(), null)
            holder.unlockCanvasAndPost(canvas)

        }
    }

    fun gameOverBitmap(){
        val canvas = holder.lockCanvas()
        if (canvas != null) {

            var image = BitmapFactory.decodeResource(resources, R.drawable.game_over)

            val scaledWidth = image.width / 3
            val scaledHeight = image.height / 3
            val scaledImage = Bitmap.createScaledBitmap(image, scaledWidth, scaledHeight, true)

            val x = (canvas.width - scaledImage.width) / 2
            val y = (canvas.height - scaledImage.height) / 2

            val paint = Paint()
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            canvas.drawPaint(paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)

            canvas.drawBitmap(scaledImage, x.toFloat(), y.toFloat(), null)
            holder.unlockCanvasAndPost(canvas)
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                onClick?.invoke()
            }
        }
        return super.onTouchEvent(event)
    }
}