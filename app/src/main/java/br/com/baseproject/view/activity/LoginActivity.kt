package br.com.baseproject.view.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.baseproject.R
import br.com.baseproject.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by osiascarneiro on 08/11/17.
 */

class LoginActivity :BaseActivity<LoginViewModel>(LoginViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        doBindings()
    }

    private fun doBindings() {
        super.onStart()
        observeLoading()
        observeUsuario()
        observeError()

        loginBtn.setOnClickListener {
            when {
                TextUtils.isEmpty(usuario.text) -> usuario.error = getString(R.string.error_usuario)
                TextUtils.isEmpty(senha.text) -> senha.error = getString(R.string.error_senha)
                else -> viewModel.login(usuario.text.toString(), senha.text.toString())
            }
        }
    }

    private fun observeLoading() {
        viewModel.status.observe(this, Observer {
            val loadingFlag = it ?: false

            loading.visibility = if (loadingFlag) View.VISIBLE else View.GONE
            loginBtn.text = if (loadingFlag) "" else getString(R.string.login)
        })
    }

    private fun observeUsuario() {
        viewModel.usuario.observe(this, Observer {
            if (it != null) {
                //Sucesso login!
            }
        })
    }

    private fun observeError() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}