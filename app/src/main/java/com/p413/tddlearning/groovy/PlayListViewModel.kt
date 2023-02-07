package com.p413.tddlearning.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayListViewModel : ViewModel() {

    val playList = MutableLiveData<Result<List<PlayList>>>()

}