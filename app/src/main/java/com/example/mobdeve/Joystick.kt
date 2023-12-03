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

    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var innerCircleRadius: Float = 0f
    private var joystickCallback: JoystickListener? = null

    private var joystickId: Int = -1

    init {
        setOnTouchListener{ view, motionEvent -> true}
    }

    override fun onSizeChanged(newWidth: Int, newHeight: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight)
        centerX = newWidth / 2f
        centerY = newHeight / 2f
        innerCircleRadius = (newWidth.coerceAtMost(newHeight) / 4).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.apply {
            drawCircle(centerX, centerY, (innerCircleRadius * 2), outerCirclePaint)
            drawCircle(centerX, centerY, innerCircleRadius, innerCirclePaint)
        }
    }

    private fun handleTouch(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val displacement = calculateDistance(event.x, event.y)
                if (displacement <= innerCircleRadius) {
                    invalidate()
                    val displacementX = event.x - centerX
                    val displacementY = event.y - centerY
                    joystickCallback?.onJoystickMoved(displacementX, displacementY)
                    val angle = Math.toDegrees(atan2(displacementY.toDouble(), displacementX.toDouble())).toFloat()
                    joystickCallback?.onAngleChanged(this, angle)
                } else {
                    val ratio = innerCircleRadius / displacement
                    val constrainedX = centerX + (event.x - centerX) * ratio
                    val constrainedY = centerY + (event.y - centerY) * ratio
                    invalidate()
                    joystickCallback?.onJoystickMoved(constrainedX - centerX, constrainedY - centerY)
                }
            }
            MotionEvent.ACTION_UP -> {
                resetJoystick()
            }
        }
    }

    private fun calculateDistance(x: Float, y: Float): Float {
        val displacementX = x - centerX
        val displacementY = y - centerY
        val distance = sqrt(displacementX * displacementX + displacementY * displacementY).toFloat()

        return distance.toFloat()
    }


    private fun resetJoystick() {
        invalidate()
        joystickCallback?.onJoystickMoved(0f, 0f)
    }

    fun setJoystickId(id: Int) {
        joystickId = id
    }

    fun getJoystickId(): Int {
        return joystickId
    }

    fun setJoystickListener(listener: JoystickListener) {
        joystickCallback = listener
    }

    interface JoystickListener {
        fun onJoystickMoved(xPercent: Float, yPercent: Float)
        fun onAngleChanged(joystick: Joystick, angle: Float)
    }
}