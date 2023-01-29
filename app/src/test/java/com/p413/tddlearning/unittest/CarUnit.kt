package com.p413.tddlearning.unittest

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.p413.tddlearning.Car
import com.p413.tddlearning.Engine
import com.p413.tddlearning.utils.MainCoroutineScopeRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class CarUnit {

    private var engine: Engine = mock()
    private val car = Car(engine, 5.0)

    @get: Rule
    var coroutineTestRule = MainCoroutineScopeRule()


    @Test
    fun carShouldLooseFuelOnceTurnedOn() = runBlocking {
        car.turnOn()
        assertEquals(4.5, car.fuel)
    }

    @Test
    fun carShouldTurnOnItsEngine() = runBlocking {
        car.turnOn()
        verify(engine, times(1)).turnOn()
    }
}