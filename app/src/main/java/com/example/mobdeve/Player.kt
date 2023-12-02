package com.example.mobdeve


class Player {
    var hp: Int = 100
    var posX: Float = 0F
    var posY: Float = 0F
    var currentAction = "idle"
    var actionFrame: Int = 0
    var charId = 0
    lateinit var normal_attack : Attack
    lateinit var special_attack : Attack

    constructor(charId: Int) {
        this.charId = charId

        if (charId == 1) {

        }
    }
}