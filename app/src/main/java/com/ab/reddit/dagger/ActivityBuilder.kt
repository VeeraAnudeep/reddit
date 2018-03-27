package com.ab.reddit.dagger

import com.ab.reddit.activities.MainActivity
import com.ab.reddit.activities.TopicPostActivity
import com.ab.reddit.modules.RedditModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(RedditModule::class)])
    internal abstract fun bindActivity(): MainActivity

    @ContributesAndroidInjector(modules = [((RedditModule::class))])
    internal abstract fun bindPostTopicActivity() : TopicPostActivity
}