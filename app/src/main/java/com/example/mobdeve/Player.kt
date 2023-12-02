package com.example.mobdeve


class Player {
    var hp: Int = 100
    var posX: Float = 0F
    var posY: Float = 0F
    var currentAction : Int = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death
    var actionFrame: Int = 0
    var charId : Int = 0 // 1 - sword, 2 - spear, 3 - shield
    lateinit var normal_attack : Attack
    lateinit var special_attack : Attack

    constructor(charId: Int) {
        this.charId = charId

        if (charId == 1) {

        }
    }
}