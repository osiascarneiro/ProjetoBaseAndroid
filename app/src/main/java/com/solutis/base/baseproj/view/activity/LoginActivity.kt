package com.solutis.base.baseproj.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import br.com.sabesp.redesabesp.viewmodel.LoginViewModel
import br.com.sabesp.redesabesp.viewmodel.ViewModelFactory
import com.solutis.base.baseproj.BaseProjApplication
import com.solutis.base.baseproj.R
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


/**
 * Created by osiascarneiro on 08/11/17.
 */

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>

    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (application as BaseProjApplication).graph.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java)

        doBindings()
    }

    fun doBindings() {
        super.onStart()
        observeLoading()
        observeUsuario()
        observeError()

        loginBtn.setOnClickListener {
            when {
                TextUtils.isEmpty(usuario.text) -> usuario.error = "Informe o usuário"
                TextUtils.isEmpty(senha.text) -> senha.error = "Informe a senha"
                else -> viewModel?.login(usuario.text.toString(), senha.text.toString())
            }
        }
    }

    fun observeLoading() {
        viewModel?.status?.observe(this, Observer {
            val loadingFlag = it ?: false

            loading.visibility = if (loadingFlag) View.VISIBLE else View.GONE
            loginBtn.text = if (loadingFlag) "" else "Entrar"
        })
    }

    fun observeUsuario() {
        viewModel?.usuario?.observe(this, Observer {
            if (it != null) {
                //Sucesso login!
            }
        })
    }

    fun observeError() {
        viewModel?.error?.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}