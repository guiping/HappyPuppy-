package com.example.androiddevchallenge.entity

import android.os.Parcelable
import java.io.Serializable

class PuppyEntity : Serializable {
    var puppyId: Int = 0
    lateinit var nickName: String
    lateinit var puppyPrice: String
    lateinit var puppyMsg: String
    var puppyImg: Int = 0
    var adopted: Boolean = false
    fun printName() {
        println(this.nickName)
    }
}