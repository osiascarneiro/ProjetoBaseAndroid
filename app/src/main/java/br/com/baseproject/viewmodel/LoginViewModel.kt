package br.com.baseproject.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import br.com.baseproject.model.consulta.LoginConsulta
import br.com.baseproject.model.entidade.Usuario
import br.com.baseproject.model.local.BancoLocal
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by osiascarneiro on 08/11/17.
 */

class LoginViewModel @Inject constructor(): BaseViewModel() {

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
                .fromCallable { localDb.usuarioDao().insertUsuario(user) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.usuario.value = user
                }, {}))
    }

    override fun refreshItens() {}

}