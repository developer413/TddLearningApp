package com.p413.tddlearning.unittest

import com.p413.tddlearning.Car
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CarUnit {

    private val car = Car(5.0)

    @Test
    fun carShouldLooseFuelOnceTurnedOn(){
        car.turnOn()
        assertEquals(4.5,car.fuel)
    }
}