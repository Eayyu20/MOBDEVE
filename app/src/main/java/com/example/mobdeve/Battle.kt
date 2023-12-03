package com.example.mobdeve

import android.util.Log

class Battle(var p1: Player, var p2: Player, val STAGE_SIZE_X: Int, val STAGE_SIZE_Y: Int) {
    init {
        Log.w("Arena", "Height: " + STAGE_SIZE_Y.toString() + " Width: " + STAGE_SIZE_X.toString())
        this.p1.posX = (this.STAGE_SIZE_X / 2) - 5 // 5 here assumes that the hitbox is centered and half the sprite is 5 units
        this.p1.posY = this.STAGE_SIZE_Y - 50 // 50 units away from the edge

        this.p2.posX = this.STAGE_SIZE_X - this.p1.posX // should be mirror opposite of p1
        this.p2.posY = this.STAGE_SIZE_Y - this.p1.posY // should be mirror opposite of p1

        this.p1.currentAction = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death
        this.p2.currentAction = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death

        this.p1.actionFrame = 0 // frame count
        this.p1.actionFrame = 0 // frame count
    }

    fun update(p1Joystick: Int, p2Joystick: Int, p1normal: Int, p1special: Int, p2normal: Int, p2special: Int) {
        // movement = speed * direction
        // if p1normal or p1special or p2normal or p2special
            // ifCollide
                // update health, currentAction
        // update currentAction
        // update frameCount
        // idle hard cap = 3
        // movement hard cap = 5
        // death hard cap = 4
        // take damage hard cap = 1 * use death frame 1
    }

    fun ifCollide(def: Array<IntArray>, att: Array<IntArray>): Boolean {
        val rect_lines = arrayOf(
            intArrayOf(def[1][0] - def[0][0], def[0][1] - def[1][1], def[1][0] - def[0][0] + def[0][1] - def[1][1]),
            intArrayOf(def[2][0] - def[1][0], def[1][1] - def[2][1], def[2][0] - def[1][0] + def[1][1] - def[2][1]),
            intArrayOf(def[3][0] - def[2][0], def[2][1] - def[3][1], def[3][0] - def[2][0] + def[2][1] - def[3][1]),
            intArrayOf(def[0][0] - def[3][0], def[3][1] - def[0][1], def[0][0] - def[3][0] + def[3][1] - def[0][1]),
            )

        var prevCoord: IntArray = att[0]
        var currentLine: IntArray
        var det : Double
        var intersect_flag : Boolean = false
        var t : Double
        var u : Double

        for (coord in att) {
            if (coord != prevCoord) {
                currentLine = intArrayOf(coord[0] - prevCoord[0], prevCoord[1] - coord[1], coord[0] - prevCoord[0] + prevCoord[1] - coord[1])
                for (line in rect_lines) {
                    det = (currentLine[0] * line[1] - currentLine[1] * line[0]).toDouble()
                    if (det != 0.0) {
                        t = (line[2] * currentLine[1] - currentLine[2] * line[1]) / det
                        u = (line[2] * currentLine[0] - currentLine[2] * line[0]) / det
                        if (0 <= t && t <= 1 && 0 <= u && u <= 1) {
                            intersect_flag = true
                            break
                        }
                    }
                }
                if (intersect_flag) {
                    break
                }
            }
            prevCoord = coord
        }

        return intersect_flag
    }
}