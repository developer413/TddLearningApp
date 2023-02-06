package com.p413.tddlearning.integrationtest

import com.p413.tddlearning.sample.Car
import com.p413.tddlearning.sample.Engine
import com.p413.tddlearning.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.Rule
import org.junit.Test

class CarFeature {

    private val engine = Engine()
    private val car = Car(engine, 5.0)

    @get: Rule
    var coroutineTestRule = MainCoroutineScopeRule()


    @Test
    fun carIsLoosingFuelOnTurningOn() = runBlocking {
        car.turnOn()
        assertEquals(4.5, car.fuel)
    }

    @Test
    fun carShouldIncreaseTempOnStartingEngineGradually() = runBlocking {
        car.turnOn()

        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(25, car.engine.temperature)

        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(50, car.engine.temperature)

        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(95, car.engine.temperature)

        assertEquals(true, car.engine.isTurnedOn)
    }
}