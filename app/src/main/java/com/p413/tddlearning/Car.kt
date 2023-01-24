package com.p413.tddlearning

class Car(var engine: Engine, var fuel: Double) {
    fun turnOn() {
        fuel -= 0.5
        engine.turnOn()
    }
}