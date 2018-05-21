package br.com.sabesp.redesabesp.module

import com.base.baseproj.view.activity.LoginActivity
import com.base.baseproj.view.activity.PostsActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by osiascarneiro on 07/11/17.
 */

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(activity: LoginActivity)
    fun inject(activity: PostsActivity)
}
