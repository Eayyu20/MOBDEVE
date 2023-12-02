package com.example.mobdeve

class Battle(var p1: Player, var p2: Player) {
    var stage_size_x = 0
    var stage_size_y = 0
    init {
        // get battlefield dimensions
        this.stage_size_x = 500
        this.stage_size_y = 500

        this.p1.posX = (this.stage_size_x / 2) - 5 // 5 here assumes that the hitbox is centered and half the sprite is 5 units
        this.p1.posY = this.stage_size_y - 50 // 50 units away from the edge

        this.p2.posX = this.stage_size_x - this.p1.posX // should be mirror opposite of p1
        this.p2.posY = this.stage_size_y - this.p1.posY // should be mirror opposite of p1

        this.p1.currentAction = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death
        this.p2.currentAction = 0 // 0 - idle, 1 - moving, 2 - normal attack, 3 - special attack, 4 - death

        this.p1.actionFrame = 0 // frame count
        this.p1.actionFrame = 0 // frame count
    }
}