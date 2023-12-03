package com.example.mobdeve

import android.util.Log
import java.lang.Math.cos
import java.lang.Math.sin

class Battle(var p1: Player, var p2: Player, val STAGE_SIZE_X: Int, val STAGE_SIZE_Y: Int) {
    var gameOver: Boolean = false
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

    fun update(p1Joystick: Float, p2Joystick: Float, p1normal: Boolean, p1special: Boolean, p2normal: Boolean, p2special: Boolean) {
        // handle death
        if (p1.hp <= 0) {
            if (p1.currentAction != 4) {
                p1.currentAction = 4
                p1.actionFrame = 0
            }
            if (p1.actionFrame < 4) p1.actionFrame += 1
            else gameOver = true
            return
        }
        if (p2.hp <= 0) {
            if (p2.currentAction != 4) {
                p2.currentAction = 4
                p2.actionFrame = 0
            }
            if (p2.actionFrame < 4) p2.actionFrame += 1
            else gameOver = true
            return
        }

        // movement = speed * direction
        p1.posX += (p1.speed * cos(p1Joystick.toDouble())).toInt()
        p1.posY += (p1.speed * sin(p1Joystick.toDouble())).toInt()

        p2.posX += (p2.speed * cos(p2Joystick.toDouble())).toInt()
        p2.posY += (p2.speed * sin(p2Joystick.toDouble())).toInt()

        // take damage
        if (p1.currentAction == 5) {
            if (p1.actionFrame < 2) p1.actionFrame += 1
            else p1.currentAction = 0
        }
        if (p2.currentAction == 5) {
            if (p2.actionFrame < 2) p2.actionFrame += 1
            else p2.currentAction = 0
        }

        // update currentAction
        // update frameCount
        if (p1.currentAction < 2) {
            // currently idle or moving
            if (p1normal) {
                p1.currentAction = 2
                p1.actionFrame = 0
            }
            else if (p1special) {
                p1.currentAction = 3
                p1.actionFrame = 0
            }
            // idle
            else if (p1.currentAction == 0){
                if (p1.actionFrame < 3) p1.actionFrame += 1
                else p1.actionFrame = 0
            }
            // moving
            else if (p1.currentAction == 1){
                if (p1.actionFrame < 5) p1.actionFrame += 1
                else p1.actionFrame = 0
            }
        }
        else {
            // moving
            if (p1Joystick > 0 && !p1normal && !p1special) {
                if (p1.currentAction != 1) p1.currentAction = 1
                else {
                    if (p1.actionFrame < 5) p1.actionFrame += 1
                    else p1.actionFrame = 0
                }
            }
            // normal and special attack
            else if (p1.currentAction == 2 || p1.currentAction == 3) {
                p1.actionFrame += 1
            }
            // idle
            else {
                if (p1.actionFrame < 3) p1.actionFrame += 1
                else p1.actionFrame = 0
            }
        }

        if (p2.currentAction < 2) {
            // currently idle or moving
            if (p1normal) {
                p2.currentAction = 2
                p2.actionFrame = 0
            }
            else if (p1special) {
                p2.currentAction = 3
                p2.actionFrame = 0
            }
            // idle
            else if (p2.currentAction == 0){
                if (p2.actionFrame < 3) p2.actionFrame += 1
                else p2.actionFrame = 0
            }
            // moving
            else if (p2.currentAction == 1){
                if (p2.actionFrame < 5) p2.actionFrame += 1
                else p2.actionFrame = 0
            }
        }
        else {
            // moving
            if (p2Joystick > 0 && !p2normal && !p2special) {
                if (p2.currentAction != 1) p2.currentAction = 1
                else {
                    if (p2.actionFrame < 5) p2.actionFrame += 1
                    else p2.actionFrame = 0
                }
            }
            // normal and special attack
            else if (p2.currentAction == 2 || p2.currentAction == 3) {
                p2.actionFrame += 1
            }
            // idle
            else {
                if (p2.actionFrame < 3) p2.actionFrame += 1
                else p2.actionFrame = 0
            }
        }

        // if collision, update currentAction to take-damage
        // update
        if (p1normal && ifCollide(p2.def_hitbox, p1.att_hitbox)) {
            // if p2 is mid attack, superarmor
            if (p2.currentAction > 1 &&
                ((p2.currentAction == 1 && p2.actionFrame > p2.normal_attack.windupFC && p2.actionFrame <= p2.normal_attack.hitFC) ||
                (p2.currentAction == 1 && p2.actionFrame > p2.special_attack.windupFC && p2.actionFrame <= p2.special_attack.hitFC))) {
                p2.hp -= p1.att_dmg
            }
            else {
                p2.currentAction = 5 // take damage
                p2.actionFrame = 0
            }
            // set p1 current frame to end of hitFC
            p1.actionFrame = p1.normal_attack.hitFC + 1
        }
        if (p1special && ifCollide(p2.def_hitbox, p1.spa_hitbox)) {
            // if p2 is mid attack, superarmor
            if (p2.currentAction > 1 &&
                ((p2.currentAction == 1 && p2.actionFrame > p2.normal_attack.windupFC && p2.actionFrame <= p2.normal_attack.hitFC) ||
                        (p2.currentAction == 1 && p2.actionFrame > p2.special_attack.windupFC && p2.actionFrame <= p2.special_attack.hitFC))) {
                p2.hp -= p1.spa_dmg
            }
            else {
                p2.currentAction = 5 // take damage
                p2.actionFrame = 0
            }
            // set p1 current frame to end of hitFC
            p1.actionFrame = p1.special_attack.hitFC + 1
        }
        if (p2normal && ifCollide(p1.def_hitbox, p2.att_hitbox)) {
            // if p1 is mid attack, superarmor
            if (p1.currentAction > 1 &&
                ((p1.currentAction == 1 && p1.actionFrame > p1.normal_attack.windupFC && p1.actionFrame <= p1.normal_attack.hitFC) ||
                        (p1.currentAction == 1 && p1.actionFrame > p1.special_attack.windupFC && p1.actionFrame <= p1.special_attack.hitFC))) {
                p1.hp -= p2.att_dmg
            }
            else {
                p1.currentAction = 5 // take damage
                p1.actionFrame = 0
            }
            // set p2 current frame to end of hitFC
            p2.actionFrame = p2.normal_attack.hitFC + 1
        }
        if (p2special && ifCollide(p1.def_hitbox, p2.spa_hitbox)) {
            // if p1 is mid attack, superarmor
            if (p1.currentAction > 1 &&
                ((p1.currentAction == 1 && p1.actionFrame > p1.normal_attack.windupFC && p1.actionFrame <= p1.normal_attack.hitFC) ||
                        (p1.currentAction == 1 && p1.actionFrame > p1.special_attack.windupFC && p1.actionFrame <= p1.special_attack.hitFC))) {
                p1.hp -= p2.spa_dmg
            }
            else {
                p1.currentAction = 5 // take damage
                p1.actionFrame = 0
            }
            // set p2 current frame to end of hitFC
            p2.actionFrame = p2.special_attack.hitFC + 1
        }
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