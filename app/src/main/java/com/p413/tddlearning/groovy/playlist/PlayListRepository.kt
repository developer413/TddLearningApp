package com.p413.tddlearning.groovy.playlist

import kotlinx.coroutines.flow.Flow


class PlayListRepository(private val service: PlayListService) {

    suspend fun getPlaylists(): Flow<Result<List<PlayList>>> = service.fetchPlayList()

}