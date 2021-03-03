package com.example.androiddevchallenge.entity

import java.io.Serializable

class PuppyEntity : Serializable {
    var puppyId: Int = 0
    lateinit var nickName: String
    lateinit var puppyMsg: String
    var puppyImg: Int = 0
    var adopted: Boolean = false
}