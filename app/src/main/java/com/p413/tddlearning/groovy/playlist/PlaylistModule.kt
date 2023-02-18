package com.p413.tddlearning.groovy.playlist

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playListApi(retrofitInstance: RetrofitClientInstance): PlayListApi =
        retrofitInstance().retrofitInstance.create(PlayListApi::class.java)

    @Provides
    fun retrofitInstance(): RetrofitClientInstance = RetrofitClientInstance
}