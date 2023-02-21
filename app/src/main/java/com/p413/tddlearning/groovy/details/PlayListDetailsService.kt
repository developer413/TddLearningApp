package com.p413.tddlearning.groovy.details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListDetailsService @Inject constructor(private val playListDetailsApi: PlayListDetailsApi) {

    suspend fun fetchPlayListDetails(id: String): Flow<Result<PlayListDetails>> {
        return flow {
            emit(Result.success(playListDetailsApi.fetchPlayListDetails(id)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}