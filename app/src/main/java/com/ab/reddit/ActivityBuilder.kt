package com.toppr.dubbio

import com.ab.reddit.MainActivity
import com.ab.reddit.RedditModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(RedditModule::class)])
    internal abstract fun bindActivity(): MainActivity
}