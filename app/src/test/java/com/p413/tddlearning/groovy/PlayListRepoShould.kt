package com.p413.tddlearning.groovy

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.p413.tddlearning.groovy.playlist.*
import com.p413.tddlearning.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayListRepoShould : BaseUnitTest() {

    private val service: PlayListService = mock()
    private val playLists = mock<List<PlayList>>()
    private val playListsRaw = mock<List<PlayListRaw>>()
    private val mapper: PlayListMapper = mock()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlayListFromService(): Unit = runBlocking {
        val repository = mockSuccessCase()
        repository.getPlaylists()
        verify(service, times(1)).fetchPlayList()
    }

    @Test
    fun emitMappedPlayListFromService(): Unit = runBlocking {
        val repository = mockSuccessCase()
        assertEquals(playLists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors(): Unit = runBlocking {
        val repository = mockErrorCase()
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }

    @Test
    fun delegateBusinessLogicToMapper(): Unit = runBlocking {
        val repository = mockSuccessCase()
        repository.getPlaylists().first()

        verify(mapper, times(1)).invoke(playListsRaw)
    }

    private suspend fun mockSuccessCase(): PlayListRepository {
        whenever(service.fetchPlayList()).thenReturn(
            flow {
                emit(Result.success(playListsRaw))
            }
        )
        whenever(mapper.invoke(playListsRaw)).thenReturn(playLists)
        return PlayListRepository(service, mapper)
    }

    private suspend fun mockErrorCase(): PlayListRepository {
        whenever(service.fetchPlayList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )
        return PlayListRepository(service, mapper)
    }

}