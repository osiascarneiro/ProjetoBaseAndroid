package br.com.baseproject.module

import br.com.baseproject.BaseProjectApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by osiascarneiro on 07/11/17.
 */

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                      ActivityBuilder::class,
                      ApiModule::class])
interface ApiComponent: AndroidInjector<BaseProjectApplication> {

    @Component.Factory
    interface Builder : AndroidInjector.Factory<BaseProjectApplication> {
        override fun create(@BindsInstance instance: BaseProjectApplication): AndroidInjector<BaseProjectApplication>
    }

}
