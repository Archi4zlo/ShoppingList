package com.archi4zlo.shoppinglist.other

open class Event<out T>(private val content: T){
    var hasBeenHandled = false
        private set // Allow external read but no write

    fun getContentIfNoHandled(): T?{
        return if (hasBeenHandled){
            null
        } else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}