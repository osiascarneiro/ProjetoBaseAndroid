package com.solutis.base.baseproj.viewmodel

import android.arch.lifecycle.MutableLiveData
import br.com.sabesp.redesabesp.viewmodel.BaseViewModel
import com.solutis.base.baseproj.common.extension.androidSubscribe
import com.solutis.base.baseproj.model.entity.Post
import com.solutis.base.baseproj.model.service.local.BancoLocal
import javax.inject.Inject

class PostsViewModel: BaseViewModel() {

    var posts = MutableLiveData<List<Post>>()
    var status = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    @Inject
    lateinit var localDb: BancoLocal

    fun getPosts() {
        service.listPosts()
                .androidSubscribe()
                .doOnSubscribe { status.value = true }
                .doFinally { status.value = false }
                .subscribe({
                    posts.value = it
                }, {
                    error.value = it.localizedMessage
                })
    }

    override fun refreshItens() {}

}