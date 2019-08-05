package br.com.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import br.com.baseproject.model.Service
import br.com.baseproject.model.entidade.Post
import br.com.baseproject.model.local.BancoLocal
import com.base.baseproj.common.extension.androidSubscribe
import javax.inject.Inject

class PostsViewModel @Inject constructor(
        val localDb: BancoLocal,
        val service: Service
) : BaseViewModel() {

    var posts = MutableLiveData<List<Post>>()
    var status = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getPosts() {
        disposables.add(service.listPosts()
                .androidSubscribe()
                .doOnSubscribe { status.value = true }
                .doFinally { status.value = false }
                .subscribe({
                    posts.value = it
                }, {
                    error.value = it.localizedMessage
                }))
    }

    override fun refreshItens() {
        getPosts()
    }

}
