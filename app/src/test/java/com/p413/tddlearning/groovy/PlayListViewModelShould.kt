package com.p413.tddlearning.groovy

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.p413.tddlearning.groovy.playlist.PlayList
import com.p413.tddlearning.groovy.playlist.PlayListRepository
import com.p413.tddlearning.groovy.playlist.PlayListViewModel
import com.p413.tddlearning.utils.BaseUnitTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest


class PlayListViewModelShould : BaseUnitTest() {


    private val repository: PlayListRepository = mock()

    private val playLists = mock<List<PlayList>>()
    private val expected = Result.success(playLists)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlayListFromRepo(): Unit = runBlocking {
        val viewModel = initializations()

        viewModel.playList.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitPlayListFromRepo(): Unit = runBlocking {
        val viewModel = initializations()
        assertEquals(expected, viewModel.playList.getValueForTest())
    }

    @Test
    fun emitError(): Unit = runBlocking {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }
        val viewModel = PlayListViewModel(repository)

        assertEquals(exception,viewModel.playList.getValueForTest()!!.exceptionOrNull())
    }

    private fun initializations(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        return PlayListViewModel(repository)
    }
}