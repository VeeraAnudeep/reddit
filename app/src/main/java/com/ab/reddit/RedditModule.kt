package com.ab.reddit

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created on 27/03/18.
 */
@Module
class RedditModule{

    @Provides
    fun providesModel(retrofit: Retrofit) = RedditViewModel(retrofit)
}