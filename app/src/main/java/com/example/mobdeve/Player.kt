package com.example.mobdeve

import android.content.Context
import android.graphics.Rect
import android.util.Log

class Player(context: Context, charId: Int) {
    // 1 - sword, 2 - spear, 3 - shield
    val ARENA_SIZE: Int = 720
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
            this.def_hitbox = arrayOf(intArrayOf(21,35), intArrayOf(32,35), intArrayOf(21,8), intArrayOf(32,8))
            this.att_hitbox = arrayOf(intArrayOf(16,36), intArrayOf(60,32), intArrayOf(32,15), intArrayOf(55,9))
            this.spa_hitbox = arrayOf(intArrayOf(39,27), intArrayOf(54,27), intArrayOf(39,5), intArrayOf(54,5))
        }
        // spear character
        else if (charId == 2) {
            this.hp = 120
            this.att_dmg = 8
            this.spa_dmg = 14
            this.normal_attack = Attack(1, 4, 1)
            this.special_attack = Attack(1, 1, 3)
            this.speed = 30
            this.def_hitbox = arrayOf(intArrayOf(25,31), intArrayOf(23,31), intArrayOf(26,4), intArrayOf(33,4))
            this.att_hitbox = arrayOf(intArrayOf(28,19), intArrayOf(57,19))
            this.spa_hitbox = arrayOf(intArrayOf(18,60), intArrayOf(47,53), intArrayOf(29,14), intArrayOf(56,23))
        }
        // shield character
        else if (charId == 3) {
            this.hp = 200
            this.att_dmg = 12
            this.spa_dmg = 20
            this.normal_attack = Attack(2, 6, 2)
            this.special_attack = Attack(0, 6, 2)
            this.speed = 20
            this.def_hitbox = arrayOf(intArrayOf(23,48), intArrayOf(42,48), intArrayOf(23,6), intArrayOf(42,6))
            this.att_hitbox = arrayOf(intArrayOf(49,35), intArrayOf(59,35), intArrayOf(49,9), intArrayOf(59,9))
            this.spa_hitbox = arrayOf(intArrayOf(33,36), intArrayOf(43,36), intArrayOf(33,10), intArrayOf(43,10))
        }
        else {
            print("ERR: Character not found.")
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
}