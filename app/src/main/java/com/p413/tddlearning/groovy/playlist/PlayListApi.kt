package com.p413.tddlearning.groovy.playlist

import retrofit2.http.GET

interface PlayListApi {

    @GET("playlist")
    suspend fun fetchAllPlayLists(): List<PlayListRaw>
}