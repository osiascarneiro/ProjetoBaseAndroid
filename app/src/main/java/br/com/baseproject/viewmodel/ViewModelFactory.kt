package br.com.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by jeziel.lago on 09/01/18.
 */

class ViewModelFactory<T : BaseViewModel> @Inject constructor(viewModel: T) : ViewModelProvider.Factory {

    private val mViewModel: T = viewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mViewModel as? T ?: throw IllegalArgumentException("Unknow class name")
    }

}