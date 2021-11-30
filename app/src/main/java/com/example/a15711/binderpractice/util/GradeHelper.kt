package com.example.a15711.binderpractice.util

object GradeHelper {
    val map = HashMap<String, Int>()
    val REQUEST_CODE = 1000

    init {
        map["wjc"] = 100
        map["ff"] = 99
        map["yy"] = 50
    }
    fun getGradeByName(name: String?): Int {
        return map[name] ?: 0
    }
}