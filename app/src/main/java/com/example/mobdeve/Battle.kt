package com.example.mobdeve

import android.graphics.Bitmap
import android.util.Log
import java.lang.Math.cos
import java.lang.Math.sin

class Battle(var p1: Player, var p2: Player) {
    var gameOver: Boolean = false
    val ARENA_SIZE: Int = 800
    init {
        this.p2.posX = (ARENA_SIZE / 2).toInt()
        this.p2.posY = ((ARENA_SIZE / 4) - 32).toInt()

        this.p1.posX = (ARENA_SIZE / 2).toInt()
        this.p1.posY = ((ARENA_SIZE * 3 / 4) + 32).toInt()

        this.p1.initHitboxes()
        this.p2.initHitboxes()

        this.p1.currentAction = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death
        this.p2.currentAction = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death

        this.p1.actionFrame = 0 // frame count
        this.p1.actionFrame = 0 // frame count
    }

    fun update(p1Joystick: Float, p2Joystick: Float, p1normal: Boolean, p1special: Boolean, p2normal: Boolean, p2special: Boolean) {
        p1.angle = p1Joystick
        p2.angle = p2Joystick

        if (p1.angle >= 0) {
            p1.updateHitboxes(true)
        }
        else {
            p1.updateHitboxes(false)
        }

        if (p2.angle < 0) {
            p2.updateHitboxes(true)
        }
        else {
            p2.updateHitboxes(false)
        }

        // handle death
        if (p1.hp <= 0) {
            if (p1.currentAction != 4) {
                p1.currentAction = 4
                p1.actionFrame = 0
            }
            if (p1.actionFrame < 16) p1.actionFrame += 1
            else gameOver = true
            return
        }
        if (p2.hp <= 0) {
            if (p2.currentAction != 4) {
                p2.currentAction = 4
                p2.actionFrame = 0
            }
            if (p2.actionFrame < 16) p2.actionFrame += 1
            else gameOver = true
            return
        }

        // movement = speed * direction
        if (p1.angle != 0F) p1.move((p1.speed * cos(p1Joystick.toDouble())).toInt(), (p1.speed * sin(p1Joystick.toDouble())).toInt())
        if (p2.angle != 0F) p2.move((p2.speed * cos(p2Joystick.toDouble())).toInt(), (p2.speed * sin(p2Joystick.toDouble())).toInt())

        // take damage
        if (p1.currentAction == 5) {
            if (p1.actionFrame < 8) p1.actionFrame += 1
            else p1.currentAction = 0
        }
        if (p2.currentAction == 8) {
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
            else if (p1Joystick != 0F) {
                p1.currentAction = 1
                if (p1.actionFrame < 9) p1.actionFrame += 1
                else p1.actionFrame = 0
            }
            else if (p1Joystick == 0F) {
                p1.currentAction = 0
                if (p1.actionFrame < 11) p1.actionFrame += 1
                else p1.actionFrame = 0
            }
        }
        else {
            // moving but not attacking
            if (p1Joystick != 0F && !p1normal && !p1special) {
                if (p1.currentAction != 1) {
                    p1.currentAction = 1
                    p1.actionFrame = 0
                }
                else {
                    if (p1.actionFrame < 9) p1.actionFrame += 1
                    else p1.actionFrame = 0
                }
            }
            // normal and special attack
            else if (p1.currentAction == 2 && p1.actionFrame < (p1.normal_attack.windupFC + p1.normal_attack.hitFC + p1.normal_attack.followthroughFC) * 1) {
                p1.actionFrame += 1
            }
            else if (p1.currentAction == 3 && p1.actionFrame < (p1.special_attack.windupFC + p1.special_attack.hitFC + p1.special_attack.followthroughFC) * 1) {
                p1.actionFrame += 1
            }
            // idle
            else {
                p1.currentAction = 0
                if (p1.actionFrame < 11) p1.actionFrame += 1
                else p1.actionFrame = 0
            }
        }

        if (p2.currentAction < 2) {
            // currently idle or moving
            if (p2normal) {
                p2.currentAction = 2
                p2.actionFrame = 0
            }
            else if (p2special) {
                p2.currentAction = 3
                p2.actionFrame = 0
            }
            else if (p2Joystick != 0F) {
                p2.currentAction = 1
                if (p2.actionFrame < 9) p2.actionFrame += 1
                else p2.actionFrame = 0
            }
            else if (p2Joystick == 0F) {
                p2.currentAction = 0
                if (p2.actionFrame < 11) p2.actionFrame += 1
                else p2.actionFrame = 0
            }
        }
        else {
            // moving
            if (p2Joystick != 0F && !p2normal && !p2special) {
                if (p2.currentAction != 1) {
                    p2.currentAction = 1
                    p2.actionFrame = 0
                }
                else {
                    if (p2.actionFrame < 9) p2.actionFrame += 1
                    else p2.actionFrame = 0
                }
            }
            // normal and special attack
            else if (p2.currentAction == 2 && p2.actionFrame < (p2.normal_attack.windupFC + p2.normal_attack.hitFC + p2.normal_attack.followthroughFC) * 1) {
                p2.actionFrame += 1
            }
            else if (p2.currentAction == 3 && p2.actionFrame < (p2.special_attack.windupFC + p2.special_attack.hitFC + p2.special_attack.followthroughFC) * 1) {
                p2.actionFrame += 1
            }
            // idle
            else {
                p2.currentAction = 0
                if (p2.actionFrame < 11) p2.actionFrame += 1
                else p2.actionFrame = 0
            }
        }

        // if collision, update currentAction to take-damage
        // update
        if (p1normal && ifCollide(p2.def_hitbox, p1.att_hitbox)) {
            // if p2 is mid attack, superarmor
            if (p2.currentAction > 1 &&
                ((p2.currentAction == 2 && p2.actionFrame > p2.normal_attack.windupFC && p2.actionFrame <= p2.normal_attack.hitFC) ||
                (p2.currentAction == 3 && p2.actionFrame > p2.special_attack.windupFC && p2.actionFrame <= p2.special_attack.hitFC))) {
                p2.actionFrame += 1
            }
            else {
                p2.currentAction = 5 // take damage
                p2.actionFrame = 0
            }
            p2.hp -= p1.att_dmg
            // set p1 current frame to end of hitFC
            p1.actionFrame = p1.normal_attack.hitFC + 1
        }
        if (p1special && ifCollide(p2.def_hitbox, p1.spa_hitbox)) {
            // if p2 is mid attack, superarmor
            if (p2.currentAction > 1 &&
                ((p2.currentAction == 2 && p2.actionFrame > p2.normal_attack.windupFC && p2.actionFrame <= p2.normal_attack.hitFC) ||
                        (p2.currentAction == 3 && p2.actionFrame > p2.special_attack.windupFC && p2.actionFrame <= p2.special_attack.hitFC))) {
                p2.actionFrame += 1
            }
            else {
                p2.currentAction = 5 // take damage
                p2.actionFrame = 0
            }

            p2.hp -= p1.spa_dmg
            // set p1 current frame to end of hitFC
            p1.actionFrame = p1.special_attack.hitFC + 1
        }
        if (p2normal && ifCollide(p1.def_hitbox, p2.att_hitbox)) {
            // if p1 is mid attack, superarmor
            if (p1.currentAction > 1 &&
                ((p1.currentAction == 2 && p1.actionFrame > p1.normal_attack.windupFC && p1.actionFrame <= p1.normal_attack.hitFC) ||
                        (p1.currentAction == 3 && p1.actionFrame > p1.special_attack.windupFC && p1.actionFrame <= p1.special_attack.hitFC))) {
                p2.actionFrame += 1
            }
            else {
                p1.currentAction = 5 // take damage
                p1.actionFrame = 0
            }
            p1.hp -= p2.att_dmg
            // set p2 current frame to end of hitFC
            p2.actionFrame = p2.normal_attack.hitFC + 1
        }
        if (p2special && ifCollide(p1.def_hitbox, p2.spa_hitbox)) {
            // if p1 is mid attack, superarmor
            if (p1.currentAction > 1 &&
                ((p1.currentAction == 2 && p1.actionFrame > p1.normal_attack.windupFC && p1.actionFrame <= p1.normal_attack.hitFC) ||
                        (p1.currentAction == 3 && p1.actionFrame > p1.special_attack.windupFC && p1.actionFrame <= p1.special_attack.hitFC))) {
                p1.actionFrame += 1
            }
            else {
                p1.currentAction = 5 // take damage
                p1.actionFrame = 0
            }
            p1.hp -= p2.spa_dmg
            // set p2 current frame to end of hitFC
            p2.actionFrame = p2.special_attack.hitFC + 1
        }
    }

    fun ifCollide(def: Array<IntArray>, att: Array<IntArray>): Boolean {
        for (i in def.indices) {
            val normal = getNormal(def[i], def[(i+1)%def.size])
            val proj1 = project(def, normal)
            val proj2 = project(att, normal)
            if (!overlaps(proj1, proj2)) {
                return false
            }
        }
        return true
    }

    fun getNormal(point1: IntArray, point2: IntArray): IntArray {
        return intArrayOf(point2[1] - point1[1], point1[0] - point2[0])
    }

    fun project(polygon: Array<IntArray>, normal: IntArray): Pair<Int, Int> {
        var minDot = Int.MAX_VALUE
        var maxDot = Int.MIN_VALUE
        for (point in polygon) {
            val dot = point[0]*normal[0] + point[1]*normal[1]
            minDot = minOf(minDot, dot)
            maxDot = maxOf(maxDot, dot)
        }
        return Pair(minDot, maxDot)
    }

    fun overlaps(proj1: Pair<Int, Int>, proj2: Pair<Int, Int>): Boolean {
        return proj1.first <= proj2.second && proj1.second >= proj2.first
    }
}