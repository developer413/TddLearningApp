package com.p413.tddlearning

import com.p413.tddlearning.utils.MainCoroutineScopeRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class EngineTest {

    private val engine = Engine(1200, 88, 15, false)

    @get: Rule
    var coroutineTestRule = MainCoroutineScopeRule()


    @Test
    fun turnOnEngineGradually() = runBlocking {
        val temp: Flow<Int> = engine.turnOn()
        val tempList: List<Int> = temp.toList()

        assertEquals(true, engine.isTurnedOn)
        assertEquals(listOf(25, 50, 95), tempList)
    }

    @Test
    fun turnOff() {
        engine.turnOff()

        assertEquals(false, engine.isTurnedOn)
        assertEquals(15, engine.temperature)
    }
}