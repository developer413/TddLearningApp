package com.p413.tddlearning.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.onEach

class PlayListViewModel(private val repository: PlayListRepository) : ViewModel() {

    val loader = MutableLiveData(true)

    val playList = liveData {
        loader.postValue(true)
        emitSource(repository.getPlaylists().onEach {
            loader.postValue(false)
        }.asLiveData())
    }

}