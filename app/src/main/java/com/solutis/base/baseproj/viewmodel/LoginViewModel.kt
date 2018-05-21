package br.com.sabesp.redesabesp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.solutis.base.baseproj.model.consulta.LoginConsulta
import com.solutis.base.baseproj.model.entidade.Usuario
import com.solutis.base.baseproj.model.local.BancoLocal
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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