package com.p413.tddlearning.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlayListService(private val playListApi: PlayListApi) {

    suspend fun fetchPlayList(): Flow<Result<List<PlayList>>> {
        return flow {
            emit(Result.success(playListApi.fetchAllPlayLists()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}