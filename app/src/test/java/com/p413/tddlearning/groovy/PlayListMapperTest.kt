package com.p413.tddlearning.groovy

import com.p413.tddlearning.R
import com.p413.tddlearning.groovy.playlist.PlayListMapper
import com.p413.tddlearning.groovy.playlist.PlayListRaw
import com.p413.tddlearning.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayListMapperTest : BaseUnitTest() {
    private val playListRaw = PlayListRaw("1", "name", "Category")
    private val playListRawRock = PlayListRaw("1", "name", "rock")
    private val playListMapper = PlayListMapper()
    private val playLists = playListMapper(listOf(playListRaw))
    private val playList = playLists[0]
    private val playListRock = playListMapper(listOf(playListRawRock))[0]

    @Test
    fun shouldKeepSameId() {
        assertEquals(playListRaw.id, playList.id)
    }

    @Test
    fun shouldKeepSameName() {
        assertEquals(playListRaw.name, playList.name)
    }

    @Test
    fun shouldKeepSameCategory() {
        assertEquals(playListRaw.category, playList.category)
    }

    @Test
    fun mapDefaultImage() {
        assertEquals(R.mipmap.playlist, playList.image)
    }

    @Test
    fun mapRockImageImage() {
        assertEquals(R.mipmap.rock, playListRock.image)
    }

}