package com.p413.tddlearning.groovy

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.p413.tddlearning.groovy.playlist.PlayList
import com.p413.tddlearning.groovy.playlist.PlayListRepository
import com.p413.tddlearning.groovy.playlist.PlayListService
import com.p413.tddlearning.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayListRepoShould : BaseUnitTest() {

    private val service: PlayListService = mock()
    private val playLists = mock<List<PlayList>>()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlayListFromService(): Unit = runBlocking {
        val repository = PlayListRepository(service)
        repository.getPlaylists()
        verify(service, times(1)).fetchPlayList()
    }

    @Test
    fun emitPlayListFromService(): Unit = runBlocking {
        mockSuccessCase()

        val repository = PlayListRepository(service)

        assertEquals(playLists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors(): Unit = runBlocking {
        mockErrorCase()

        val repository = PlayListRepository(service)

        assertEquals(exception,repository.getPlaylists().first().exceptionOrNull())
    }

    private suspend fun mockSuccessCase() {
        whenever(service.fetchPlayList()).thenReturn(
            flow {
                emit(Result.success(playLists))
            }
        )
    }

    private suspend fun mockErrorCase() {
        whenever(service.fetchPlayList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )
    }

}