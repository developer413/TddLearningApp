package com.p413.tddlearning.groovy.playlist

import com.p413.tddlearning.groovy.details.PlayListDetails
import retrofit2.http.GET

interface PlayListApi {

    @GET("playlist")
    suspend fun fetchAllPlayLists(): List<PlayListRaw>

    @GET("details")
    suspend fun fetchPlayListDetails(id: String): PlayListDetails
}