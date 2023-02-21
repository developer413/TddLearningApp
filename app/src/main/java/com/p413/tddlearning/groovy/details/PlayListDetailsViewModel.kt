package com.p413.tddlearning.groovy.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PlayListDetailsViewModel(
    private val service: PlayListDetailsService,
) : ViewModel() {

    val playListDetails: MutableLiveData<Result<PlayListDetails>> = MutableLiveData()

    fun getPlayListDetails(id: String) {
        viewModelScope.launch {
            service.fetchPlayListDetails(id).collect {
                playListDetails.postValue(it)
            }
        }
    }
}