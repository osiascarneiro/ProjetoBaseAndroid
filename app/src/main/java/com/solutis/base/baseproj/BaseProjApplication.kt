package com.solutis.base.baseproj

import android.app.Application
import br.com.sabesp.redesabesp.module.ApiComponent
import br.com.sabesp.redesabesp.module.ApiModule
import br.com.sabesp.redesabesp.module.DaggerApiComponent

/**
 * Created by osiascarneiro on 07/11/17.
 */

class BaseProjApplication: Application() {

    lateinit var graph: ApiComponent

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApiComponent.builder().apiModule(ApiModule(this)).build()
    }

}