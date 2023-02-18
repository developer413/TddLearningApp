package com.p413.tddlearning.groovy.playlist

import com.p413.tddlearning.R
import javax.inject.Inject

class PlayListMapper @Inject constructor() : Function1<List<PlayListRaw>, List<PlayList>> {
    override fun invoke(playListRaw: List<PlayListRaw>): List<PlayList> {
        return playListRaw.map {
            val image = when (it.category) {
                "rock" -> R.mipmap.rock
                else -> R.mipmap.playlist
            }
            PlayList(it.id, it.name, it.category, image)
        }
    }

}