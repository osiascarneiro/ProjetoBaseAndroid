package br.com.baseproject

import br.com.baseproject.module.DaggerApiComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by osiascarneiro on 07/11/17.
 */

class BaseProjectApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerApiComponent
                                                                                    .factory()
                                                                                    .create(this)

}