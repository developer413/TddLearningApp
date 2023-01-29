package com.p413.tddlearning

import com.p413.tddlearning.utils.MainCoroutineScopeRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class EngineTest {

    private val engine = Engine(1200, 88, 15, false)

    @get: Rule
    var coroutineTestRule = MainCoroutineScopeRule()


    @Test
    fun turnOn() = runBlocking {
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