package com.p413.tddlearning.groovy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.p413.tddlearning.R
import com.p413.tddlearning.groovy.playlist.PlayListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}