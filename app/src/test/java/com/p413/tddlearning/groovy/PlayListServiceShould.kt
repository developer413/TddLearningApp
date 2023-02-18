package com.p413.tddlearning.groovy

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.p413.tddlearning.groovy.playlist.PlayListApi
import com.p413.tddlearning.groovy.playlist.PlayListRaw
import com.p413.tddlearning.groovy.playlist.PlayListService
import com.p413.tddlearning.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.RuntimeException

class PlayListServiceShould : BaseUnitTest() {
    private val playListApi: PlayListApi = mock()

    private lateinit var service: PlayListService
    private val playLists: List<PlayListRaw> = mock()

    @Test
    fun shouldFetchPlayListFromApi(): Unit = runBlocking {

        service = PlayListService(playListApi)
        service.fetchPlayList().first()
        verify(playListApi, times(1)).fetchAllPlayLists()
    }

    @Test
    fun convertValuesToFlowAndEmitThem(): Unit = runBlocking {
        mockSuccessCase()
        assertEquals(Result.success(playLists), service.fetchPlayList().first())
    }

    private suspend fun mockSuccessCase() {
        whenever(playListApi.fetchAllPlayLists()).thenReturn(playLists)
        service = PlayListService(playListApi)
    }

    @Test
    fun emitsErrorResultWhenNetworkFails(): Unit = runBlocking {
        mockErrorCase()
        assertEquals(
            "Something went wrong",
            service.fetchPlayList().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase() {
        whenever(playListApi.fetchAllPlayLists()).thenThrow(RuntimeException("Damn Error"))
        service = PlayListService(playListApi)
    }
}