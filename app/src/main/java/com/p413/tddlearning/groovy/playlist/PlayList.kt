package com.p413.tddlearning.groovy.playlist

import com.p413.tddlearning.R

data class PlayList(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.playlist,
)
