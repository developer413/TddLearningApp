package com.p413.tddlearning

import kotlinx.coroutines.delay

class Engine(
    var cc: Int = 1200,
    var horsePower: Int = 88,
    var temperature: Int = 15,
    var isTurnedOn: Boolean = false
) {

    suspend fun turnOn() {
        isTurnedOn = true
        delay(6000)
        temperature = 95
    }

    fun turnOff() {
        isTurnedOn = false
        temperature = 15
    }

}