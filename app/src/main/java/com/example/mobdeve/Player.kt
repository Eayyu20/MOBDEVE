package com.example.mobdeve

import android.content.Context
import android.graphics.Rect
import android.util.Log

class Player(context: Context, charId: Int) {
    // 1 - sword, 2 - spear, 3 - shield
    val ARENA_SIZE: Int = 800
    var charId: Int = 0
    var hp: Int = 100
    var posX: Int = 0
    var posY: Int = 0
    var speed: Int = 0
    var angle : Float = 0F
    var currentAction : Int = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death, 5 - take damage
    var actionFrame: Int = 0
    lateinit var normal_attack : Attack
    lateinit var special_attack : Attack
    var att_dmg: Int = 0
    var spa_dmg: Int = 0
    var spriteSheet: SpriteSheet
    lateinit var def_hitbox: Array<IntArray>
    lateinit var att_hitbox: Array<IntArray>
    lateinit var spa_hitbox: Array<IntArray>
    lateinit var const_def_hitbox: Array<IntArray>
    lateinit var const_att_hitbox: Array<IntArray>
    lateinit var const_spa_hitbox: Array<IntArray>
    lateinit var neg_const_def_hitbox: Array<IntArray>
    lateinit var neg_const_att_hitbox: Array<IntArray>
    lateinit var neg_const_spa_hitbox: Array<IntArray>

    init {
        this.charId = charId

        // initialize spriteSheet
        this.spriteSheet = SpriteSheet(context, charId)

        // sword character
        if (charId == 1) {
            this.hp = 150
            this.att_dmg = 10
            this.spa_dmg = 18
            this.normal_attack = Attack(1, 5, 2)
            this.special_attack = Attack(3, 5, 4)
            this.speed = 25
            this.const_def_hitbox = arrayOf(intArrayOf(35,21), intArrayOf(35,32), intArrayOf(8,21), intArrayOf(8,32))
            this.const_att_hitbox = arrayOf(intArrayOf(36,-48), intArrayOf(32,90), intArrayOf(15,-32), intArrayOf(9,85))
            this.const_spa_hitbox = arrayOf(intArrayOf(27,-25), intArrayOf(27,84), intArrayOf(5,-25), intArrayOf(5, 84))
        }
        // spear character
        else if (charId == 2) {
            this.hp = 120
            this.att_dmg = 8
            this.spa_dmg = 14
            this.normal_attack = Attack(1, 4, 1)
            this.special_attack = Attack(1, 1, 3)
            this.speed = 30
            this.const_def_hitbox = arrayOf(intArrayOf(31,25), intArrayOf(31,33), intArrayOf(4,26), intArrayOf(4,33))
            this.const_att_hitbox = arrayOf(intArrayOf(19,-36), intArrayOf(19,107), intArrayOf(17,-36), intArrayOf(17,107))
            this.const_spa_hitbox = arrayOf(intArrayOf(60,-46), intArrayOf(53,97), intArrayOf(14,-35), intArrayOf(23,106))
        }
        // shield character
        else if (charId == 3) {
            this.hp = 200
            this.att_dmg = 12
            this.spa_dmg = 20
            this.normal_attack = Attack(2, 6, 2)
            this.special_attack = Attack(0, 6, 2)
            this.speed = 20
            this.const_def_hitbox = arrayOf(intArrayOf(48,23), intArrayOf(48,42), intArrayOf(6,23), intArrayOf(6,42))
            this.const_att_hitbox = arrayOf(intArrayOf(35,-15), intArrayOf(35,79), intArrayOf(9,-15), intArrayOf(9,79))
            this.const_spa_hitbox = arrayOf(intArrayOf(36,-31), intArrayOf(36,63), intArrayOf(10,-31), intArrayOf(10,63))
        }
        else {
            print("ERR: Character not found.")
        }

        // initialize consts
        neg_const_def_hitbox = const_def_hitbox
        neg_const_att_hitbox = const_att_hitbox
        neg_const_spa_hitbox = const_spa_hitbox

        neg_const_def_hitbox.forEach { it.forEachIndexed { index, value -> it[index] = value * -1 } }
        neg_const_att_hitbox.forEach { it.forEachIndexed { index, value -> it[index] = value * -1 } }
        neg_const_spa_hitbox.forEach { it.forEachIndexed { index, value -> it[index] = value * -1 } }
    }

    fun initHitboxes() {
        def_hitbox = incrementCoordinates(const_def_hitbox, posX, posY)
        att_hitbox = incrementCoordinates(const_att_hitbox, posX, posY)
        spa_hitbox = incrementCoordinates(const_spa_hitbox, posX, posY)
    }

    fun updateHitboxes(flip: Boolean) {
        if (flip) {
            def_hitbox = incrementCoordinates(const_def_hitbox, posX, posY)
            att_hitbox = incrementCoordinates(const_att_hitbox, posX, posY)
            spa_hitbox = incrementCoordinates(const_spa_hitbox, posX, posY)
        }
        else {
            def_hitbox = incrementCoordinates(neg_const_def_hitbox, posX, posY)
            att_hitbox = incrementCoordinates(neg_const_att_hitbox, posX, posY)
            spa_hitbox = incrementCoordinates(neg_const_spa_hitbox, posX, posY)
        }
    }

    fun move(x_displacement: Int, y_displacement: Int) {
        if (posX + x_displacement < 0) posX = 0
        else if (posX + x_displacement > ARENA_SIZE - 64) posX = ARENA_SIZE - 64
        else posX += x_displacement
        if (posY + y_displacement < 0) posY = 0
        else if (posY + y_displacement > ARENA_SIZE - 96) posY = ARENA_SIZE - 96
        else posY += y_displacement
    }

    private fun incrementCoordinates(coordinates: Array<IntArray>, x_inc: Int, y_inc: Int): Array<IntArray> {
        val result = Array(coordinates.size) { IntArray(2) }

        for (i in coordinates.indices) {
            result[i][0] = coordinates[i][0] + x_inc
            result[i][1] = coordinates[i][1] + y_inc
        }

        return result
    }
}