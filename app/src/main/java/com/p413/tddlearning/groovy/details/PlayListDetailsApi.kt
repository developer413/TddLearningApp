package com.p413.tddlearning.groovy.details

import retrofit2.http.GET

interface PlayListDetailsApi {

    @GET("playlist-details/id")
    suspend fun fetchPlayListDetails(id: String): PlayListDetails
}