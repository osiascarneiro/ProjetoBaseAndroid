package br.com.baseproject.module

import br.com.baseproject.view.activity.LoginActivity
import br.com.baseproject.view.activity.PostsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributesPostsActivity(): PostsActivity

}