package com.example.mobdeve

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import kotlin.math.atan2

class Joystick(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs), SurfaceHolder.Callback{

    private lateinit var canvas: Canvas
    private var centerX: Float = 0F
    private var centerY: Float = 0F
    private var innerCircleRadius: Float = 0F
    private var innerCircleX: Float = 0F
    private var innerCircleY: Float = 0F
    var angle: Float = 0F

    init {
        holder.addCallback(this)

        setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                    innerCircleX = event.x
                    innerCircleY = event.y
                    angle = atan2((event.y - centerY).toDouble(), (event.x - centerX).toDouble()).toFloat()
                    if (innerCircleX > 350) {
                        innerCircleX = 350F
                    }
                    else if (innerCircleX < -350) {
                        innerCircleX = -350F
                    }
                    if (innerCircleY > 350) {
                        innerCircleY = 350F
                    }
                    else if (innerCircleY < -350) {
                        innerCircleY = -350F
                    }
                    invalidate()
                    return true
                }
                else {
                    resetJoystick()
                }
                view.performClick()
                return false
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.w("onDraw", " centerX: ${centerX}, centerY: ${centerY}, innerCircleRadius: ${innerCircleRadius}")
        drawOnCanvas(holder)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {

        centerX = width / 2F
        centerY = height / 2F
        innerCircleRadius = 64F
        innerCircleX = width / 2F
        innerCircleY = height / 2F
        angle = 0F

        Log.w("surfaceCreated", " width: ${width}, height: ${height}")
        Log.w("surfaceCreated", " centerX: ${centerX}, centerY: ${centerY}, innerCircleRadius: ${innerCircleRadius}")
        drawOnCanvas(holder)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Handle surface changes if needed
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // Handle surface destruction if nee
    }

    private fun drawOnCanvas(holder: SurfaceHolder) {
        canvas = holder.lockCanvas()
        val outerCirclePaint = Paint().apply {
            color = Color.GRAY
            style = Paint.Style.FILL
        }

        val innerCirclePaint = Paint().apply {
            color = Color.RED
            style = Paint.Style.FILL
        }

        Log.w("drawnOnCanvas", " centerX: ${centerX}, centerY: ${centerY}, innerCircleRadius: ${innerCircleRadius}")
        Log.w("drawnOnCanvas", " width: ${width}, height: ${height}")

        canvas.drawCircle(centerX, centerY, (innerCircleRadius * 2), outerCirclePaint)
        canvas.drawCircle(innerCircleX, innerCircleY, innerCircleRadius, innerCirclePaint)

        holder.unlockCanvasAndPost(canvas)
    }

    private fun resetJoystick() {
        innerCircleX = centerX
        innerCircleY = centerY
        angle = 0F
        Log.w("resetJoystick", " centerX: ${centerX}, centerY: ${centerY}, angle: ${angle}")
        invalidate()
    }
}