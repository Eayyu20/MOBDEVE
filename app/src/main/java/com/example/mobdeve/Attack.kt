package com.example.mobdeve

class Attack {
    var windupFC : Int = 0
    var hitFC : Int = 0
    var followthroughFC : Int = 0

    constructor(windupFC: Int, hitFC: Int, followthroughFC: Int) {
        this.windupFC = windupFC
        this.hitFC = hitFC
        this.followthroughFC = followthroughFC
    }
}