package com.p413.tddlearning.groovy.details

import com.p413.tddlearning.groovy.playlist.PlayListApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListDetailsService @Inject constructor(private val playListApi: PlayListApi) {


    suspend fun fetchPlayListDetails(id: String): Flow<Result<PlayListDetails>> {
        return flow {
            emit(Result.success(playListApi.fetchPlayListDetails(id)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}