package com.p413.tddlearning.integrationtest

import com.p413.tddlearning.Car
import com.p413.tddlearning.Engine
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CarFeature {

    private val engine = Engine()
    private val car = Car(engine, 5.0)

    @Test
    fun carIsLoosingFuelOnTurningOn() {
        car.turnOn()
        assertEquals(4.5, car.fuel)
    }

    @Test
    fun carShouldIncreaseTempOnStartingEngine(){
        car.turnOn()

        assertEquals(95,car.engine.temperature)
        assertEquals(true,car.engine.isTurnedOn)
    }
}