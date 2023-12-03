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
import org.w3c.dom.Attr
import kotlin.math.atan2
import kotlin.math.sqrt

class Joystick(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs) {

    private var holder: SurfaceHolder = getHolder()

    private val outerCirclePaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL
    }

    private val innerCirclePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private var centerX: Float = 0F
    private var centerY: Float = 0F
    private var innerCircleRadius: Float = 0f
    var innerCircleX: Float = 0F
    var innerCircleY: Float = 0F

    var angle : Float = 0F

    init {
        setWillNotDraw(false)
//        alpha = 0F
        setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                    innerCircleX = event.x
                    innerCircleY = event.y
                    angle = Math.toDegrees(atan2((event.y - centerY).toDouble(), (event.x - centerX).toDouble())).toFloat()
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
                else if (event.action == MotionEvent.ACTION_UP){
                    resetJoystick()
                }
                view.performClick()
                return false
            }
        })
    }

    override fun onDraw(c : Canvas) {
        if (holder.surface.isValid()) {
            var canvas: Canvas = holder.lockCanvas()
            canvas.drawCircle(centerX, centerY, (innerCircleRadius * 2), outerCirclePaint)
            canvas.drawCircle(innerCircleX, innerCircleY, innerCircleRadius, innerCirclePaint)
            holder.unlockCanvasAndPost(canvas)
            Log.w(this.javaClass.name, "X: " + innerCircleX.toString() + " Y: " + innerCircleY.toString())
        }
    }

    private fun resetJoystick() {
        innerCircleX = 0F
        innerCircleY = 0F
        angle = 0F
        invalidate()
    }
}