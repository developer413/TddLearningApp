package com.p413.tddlearning.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PlayListRepository @Inject constructor(
    private val service: PlayListService,
    private val mapper: PlayListMapper,
) {

    suspend fun getPlaylists(): Flow<Result<List<PlayList>>> = service.fetchPlayList().map {
        if(it.isSuccess){
            Result.success(mapper(it.getOrNull()!!))
        }else{
            Result.failure(it.exceptionOrNull()!!)
        }
    }


}