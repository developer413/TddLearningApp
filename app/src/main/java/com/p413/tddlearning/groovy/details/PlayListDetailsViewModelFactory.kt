package com.p413.tddlearning.groovy.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PlayListDetailsViewModelFactory @Inject constructor(
    private val service: PlayListDetailsService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListDetailsViewModel(service) as T
    }
}