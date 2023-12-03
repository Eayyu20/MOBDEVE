package com.example.mobdeve

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2
import kotlin.math.sqrt

class Joystick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

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
        setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                    innerCircleX = event.x
                    innerCircleY = event.y
                    angle = Math.toDegrees(atan2((event.y - centerY).toDouble(), (event.x - centerX).toDouble())).toFloat()
                    invalidate()
                    return true
                }
                else if (event.action == MotionEvent.ACTION_UP){
                    resetJoystick()
                }
                return false
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.apply {
            drawCircle(centerX, centerY, (innerCircleRadius * 2), outerCirclePaint)
            drawCircle(innerCircleX, innerCircleY, innerCircleRadius, innerCirclePaint)
        }
    }

    private fun resetJoystick() {
        innerCircleX = 0F
        innerCircleY = 0F
        angle = 0F
        invalidate()
    }
}