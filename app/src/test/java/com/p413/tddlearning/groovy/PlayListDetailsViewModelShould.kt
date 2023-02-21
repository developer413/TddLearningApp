package com.p413.tddlearning.groovy

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.p413.tddlearning.groovy.details.PlayListDetails
import com.p413.tddlearning.groovy.details.PlayListDetailsService
import com.p413.tddlearning.groovy.details.PlayListDetailsViewModel
import com.p413.tddlearning.utils.BaseUnitTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest

class PlayListDetailsViewModelShould : BaseUnitTest() {

    private val service: PlayListDetailsService = mock()
    private val playListDetails = mock<PlayListDetails>()
    private val expected = Result.success(playListDetails)
    private val exception = RuntimeException("Something went wrong")
    private val id = "1"

    @Test
    fun getPlayListDetailsFromService(): Unit = runBlocking {
        val viewModel = PlayListDetailsViewModel(service)

        viewModel.getPlayListDetails(id)
        viewModel.playListDetails.getValueForTest()
        verify(service, times(1)).fetchPlayListDetails(id)
    }

    @Test
    fun emitPlayListDetailsFromService(): Unit = runBlocking {
        val viewModel = successCase()
        viewModel.getPlayListDetails(id)
        Assert.assertEquals(expected, viewModel.playListDetails.getValueForTest())
    }

    @Test
    fun emitError(): Unit = runBlocking {
        val viewModel = errorCase()
        viewModel.getPlayListDetails(id)
        Assert.assertEquals(exception, viewModel.playListDetails.getValueForTest()!!.exceptionOrNull())
    }


    private fun successCase(): PlayListDetailsViewModel {
        runBlocking {
            whenever(service.fetchPlayListDetails(id)).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        return PlayListDetailsViewModel(service)
    }

    private fun errorCase(): PlayListDetailsViewModel {
        runBlocking {
            whenever(service.fetchPlayListDetails(id)).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }

        return PlayListDetailsViewModel(service)
    }

}