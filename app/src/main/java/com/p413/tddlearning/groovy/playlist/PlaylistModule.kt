package com.p413.tddlearning.groovy.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playListApi(retrofitInstance: RetrofitClientInstance): PlayListApi =
        retrofitInstance().retrofitInstance.create(PlayListApi::class.java)

    @Provides
    fun retrofitInstance(): RetrofitClientInstance = RetrofitClientInstance
}