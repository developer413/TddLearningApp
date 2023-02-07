package com.p413.tddlearning.groovy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayListViewModelFactory(
    private val repository: PlayListRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListViewModel(repository) as T
    }
}