package com.example.mobdeve

import android.content.Context
import android.graphics.Rect

class Player(context: Context, charId: Int) {
    // 1 - sword, 2 - spear, 3 - shield
    var charId: Int = 0
    var hp: Int = 100
    var posX: Int = 0
    var posY: Int = 0
    var speed: Int = 0
    var currentAction : Int = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death, 5 - take damage
    var actionFrame: Int = 0
    lateinit var normal_attack : Attack
    lateinit var special_attack : Attack
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
            this.normal_attack = Attack(1, 5, 2)
            this.special_attack = Attack(3, 5, 4)
            this.speed = 100
            this.def_hitbox = arrayOf(intArrayOf(21,35), intArrayOf(32,35), intArrayOf(21,8), intArrayOf(32,8))
            this.att_hitbox = arrayOf(intArrayOf(16,36), intArrayOf(60,32), intArrayOf(32,15), intArrayOf(55,9))
            this.spa_hitbox = arrayOf(intArrayOf(39,27), intArrayOf(54,27), intArrayOf(39,5), intArrayOf(54,5))
        }
        // spear character
        else if (charId == 2) {
            this.hp = 120
            this.normal_attack = Attack(1, 4, 1)
            this.special_attack = Attack(1, 1, 3)
            this.speed = 120
            this.def_hitbox = arrayOf(intArrayOf(0,0), intArrayOf(0,0), intArrayOf(0,0), intArrayOf(0,0))
            this.att_hitbox = arrayOf(intArrayOf(0,0), intArrayOf(0,0), intArrayOf(0,0), intArrayOf(0,0))
            this.spa_hitbox = arrayOf(intArrayOf(0,0), intArrayOf(0,0), intArrayOf(0,0), intArrayOf(0,0))
        }
        // shield character
        else if (charId == 3) {
            this.hp = 200
            this.normal_attack = Attack(2, 6, 2)
            this.special_attack = Attack(0, 6, 2)
            this.speed = 80
            this.def_hitbox = arrayOf(intArrayOf(23,48), intArrayOf(42,48), intArrayOf(23,6), intArrayOf(42,6))
            this.att_hitbox = arrayOf(intArrayOf(49,35), intArrayOf(59,35), intArrayOf(49,9), intArrayOf(59,9))
            this.spa_hitbox = arrayOf(intArrayOf(33,36), intArrayOf(43,36), intArrayOf(33,10), intArrayOf(43,10))
        }
        else {
            print("ERR: Character not found.")
        }
    }

    fun move(x_displacement: Int, y_displacement: Int) {
        this.posX += x_displacement
        this.posY += y_displacement
    }
}