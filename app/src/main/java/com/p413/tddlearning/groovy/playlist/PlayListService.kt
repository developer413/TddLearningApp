package com.p413.tddlearning.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListService @Inject constructor(private val playListApi: PlayListApi) {

    suspend fun fetchPlayList(): Flow<Result<List<PlayListRaw>>> {
        return flow {
            emit(Result.success(playListApi.fetchAllPlayLists()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}