package com.p413.tddlearning.groovy.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.p413.tddlearning.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.RuntimeException

class PlayListDetailsServiceShould : BaseUnitTest() {

    private lateinit var detailsService: PlayListDetailsService
    private val playListDetails: PlayListDetails = mock()
    private val playListDetailsApi: PlayListDetailsApi = mock()
    private val id = "1"
    private val exception = RuntimeException("Damn Error")


    @Test
    fun shouldFetchPlayListDetailsFromApi(): Unit = runBlocking {

        detailsService = PlayListDetailsService(playListDetailsApi)
        detailsService.fetchPlayListDetails(id).first()
        verify(playListDetailsApi, times(1)).fetchPlayListDetails(id)
    }

    @Test
    fun convertValuesToFlowAndEmitThem(): Unit = runBlocking {
        mockSuccessCase()
        assertEquals(
            Result.success(playListDetails),
            detailsService.fetchPlayListDetails(id).first()
        )
    }

    @Test
    fun emitsErrorResultWhenNetworkFails(): Unit = runBlocking {
        mockErrorCase()
        assertEquals(
            "Something went wrong",
            detailsService.fetchPlayListDetails(id).first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase() {
        whenever(playListDetailsApi.fetchPlayListDetails(id)).thenThrow(exception)
        detailsService = PlayListDetailsService(playListDetailsApi)
    }

    private suspend fun mockSuccessCase() {
        whenever(playListDetailsApi.fetchPlayListDetails(id)).thenReturn(playListDetails)
        detailsService = PlayListDetailsService(playListDetailsApi)
    }


}