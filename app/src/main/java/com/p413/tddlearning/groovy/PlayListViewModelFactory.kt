package com.p413.tddlearning.groovy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListViewModel() as T
    }
}