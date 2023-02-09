package com.p413.tddlearning.groovy.playlist

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PlayListRepository @Inject constructor(private val service: PlayListService) {

    suspend fun getPlaylists(): Flow<Result<List<PlayList>>> = service.fetchPlayList()


}