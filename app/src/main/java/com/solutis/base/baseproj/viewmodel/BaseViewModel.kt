package br.com.sabesp.redesabesp.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.solutis.base.baseproj.repository.Service
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by osiascarneiro on 09/11/17.
 */

abstract class BaseViewModel: ViewModel() {

    var disposables = CompositeDisposable()

    @Inject
    lateinit var service: Service

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        Log.d("ViewModel", "onCleared")
    }

    abstract fun refreshItens()

}