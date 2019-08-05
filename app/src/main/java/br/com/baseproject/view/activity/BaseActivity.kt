package br.com.baseproject.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.baseproject.viewmodel.BaseViewModel
import br.com.baseproject.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Classe base do projeto,
 * sempre chamar o super do oncreate antes de usar a classe de viewmodel
 */
abstract class BaseActivity<T: BaseViewModel>(private val cls: Class<T>): AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<T>

    /**
     * ViewModel da tela, cada activity deve ter uma.
     * Inicializar na classe específica
     */
    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(cls)
    }

}