package com.p413.tddlearning.sample

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Car(var engine: Engine, var fuel: Double) {
    fun turnOn() {
        fuel -= 0.5
        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn().collect {
                Log.v("LoGGING", "Collected values $it")
            }
        }
    }
}