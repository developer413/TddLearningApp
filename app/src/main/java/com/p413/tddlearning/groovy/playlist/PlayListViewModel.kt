package com.p413.tddlearning.groovy.playlist

import androidx.lifecycle.*

class PlayListViewModel(private val repository: PlayListRepository) : ViewModel() {

    val playList = liveData {
        emitSource(repository.getPlaylists().asLiveData())
    }

}