package br.com.sabesp.redesabesp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.solutis.base.baseproj.common.extension.androidSubscribe
import com.solutis.base.baseproj.model.service.remote.LoginConsulta
import com.solutis.base.baseproj.model.entity.Usuario
import com.solutis.base.baseproj.model.service.local.BancoLocal
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by osiascarneiro on 08/11/17.
 */

class LoginViewModel : BaseViewModel {

    @Inject
    constructor()

    @Inject
    lateinit var localDb: BancoLocal

    @Inject
    lateinit var sharedPref: SharedPreferences

    var usuario = MutableLiveData<Usuario>()
    var status = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun login(usuario: String, senha: String) {
        disposables.add(service.login(LoginConsulta(usuario, senha))
                .androidSubscribe()
                .doOnSubscribe { status.value = true }
                .doFinally { status.value = false }
                .subscribe(
                        {
                            this.insertUser(it)
                        },
                        {
                            error.value = "Login inválido"
                        }
                )
        )
    }

    private fun insertUser(user: Usuario) {
        disposables.add(Single
                .fromCallable({ localDb.usuarioDao().insertUsuario(user) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.usuario.value = user
                }, {}))
    }

    override fun refreshItens() {}

}