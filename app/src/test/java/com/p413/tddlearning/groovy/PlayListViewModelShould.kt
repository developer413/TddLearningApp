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
import petros.efthymiou.groovy.utils.captureValues
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
        val viewModel = errorCase()

        assertEquals(exception, viewModel.playList.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showProgressBarWhileLoading(): Unit = runBlocking {
        val viewModel = successCase()

        viewModel.loader.captureValues {
            viewModel.playList.getValueForTest()
            assertEquals(true, values[0])
        }
    }


    @Test
    fun hideProgressBarAfterLoading(): Unit = runBlocking {
        val viewModel = successCase()

        viewModel.loader.captureValues {
            viewModel.playList.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun hideProgressBarWhenError(): Unit = runBlocking {
        val viewModel = errorCase()

        viewModel.loader.captureValues {
            viewModel.playList.getValueForTest()
            assertEquals(false, values.last())
        }
    }


    private fun successCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlayListViewModel(repository)
    }

    private fun errorCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }
        return PlayListViewModel(repository)
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