package com.p413.tddlearning

class Engine(
    var cc: Int = 1200,
    var horsePower: Int = 88,
    var temperature: Int = 15,
    var isTurnedOn: Boolean = false
) {

    fun turnOn() {
        isTurnedOn = true
        temperature = 95
    }

    fun turnOff() {
        isTurnedOn = false
        temperature = 15
    }

}