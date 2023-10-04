package com.example.datadeliverationdemo

object SingletonData {
    private var value: Any? = null
    fun <T> setData(value :T) {
        this.value = value
    }
    fun <T> getData() : T? = value as? T
}