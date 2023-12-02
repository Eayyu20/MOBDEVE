package com.example.mobdeve

import android.content.Context

class Player(context: Context, charId: Int) {
    // 1 - sword, 2 - spear, 3 - shield
    var charId: Int = 0
    var hp: Int = 100
    var posX: Int = 0
    var posY: Int = 0
    var currentAction : Int = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death
    var actionFrame: Int = 0
    lateinit var normal_attack : Attack
    lateinit var special_attack : Attack
    var spriteSheet: SpriteSheet
    var dir: Float = 0F

    init {
        this.charId = charId

        // initialize spriteSheet
        this.spriteSheet = SpriteSheet(context, charId)

        // sword character
        if (charId == 1) {
            this.hp = 150
            this.normal_attack = Attack(1, 5, 2)
            this.special_attack = Attack(3, 5, 4)
        }
        // spear character
        else if (charId == 2) {
            this.hp = 120
            this.normal_attack = Attack(1, 4, 1)
            this.special_attack = Attack(1, 1, 3)
        }
        // shield character
        else if (charId == 3) {
            this.hp = 200
            this.normal_attack = Attack(2, 6, 2)
            this.special_attack = Attack(0, 6, 2)
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