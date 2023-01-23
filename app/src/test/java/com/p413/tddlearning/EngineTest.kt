package com.p413.tddlearning

import org.junit.Assert.assertEquals
import org.junit.Test

class EngineTest {

    private val engine = Engine(1200, 88, 15, false)

    @Test
    fun turnOn() {
        engine.turnOn()

        assertEquals(true, engine.isTurnedOn)
        assertEquals(95, engine.temperature)
    }

    @Test
    fun turnOff() {
        engine.turnOff()

        assertEquals(false, engine.isTurnedOn)
        assertEquals(15, engine.temperature)
    }
}