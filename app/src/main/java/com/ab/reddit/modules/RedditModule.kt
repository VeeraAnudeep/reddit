package com.ab.reddit.modules

import com.ab.reddit.viewmodels.RedditViewModel
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