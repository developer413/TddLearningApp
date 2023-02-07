package com.p413.tddlearning.groovy.playlist

interface PlayListApi {

    suspend fun fetchAllPlayLists(): List<PlayList> {
        TODO("To be implemented")
    }
}