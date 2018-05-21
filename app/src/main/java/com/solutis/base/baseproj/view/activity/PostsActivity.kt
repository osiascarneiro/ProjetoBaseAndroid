package com.solutis.base.baseproj.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.sabesp.redesabesp.viewmodel.ViewModelFactory
import com.solutis.base.baseproj.BaseProjApplication
import com.solutis.base.baseproj.viewmodel.PostsViewModel
import javax.inject.Inject

class PostsActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<PostsViewModel>

    var viewModel: PostsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as BaseProjApplication).graph.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)
        doBindings()
    }

    fun doBindings() {
        viewModel?.posts?.observe(this, Observer {

        })

        viewModel?.status?.observe(this, Observer {

        })

        viewModel?.error?.observe(this, Observer {

        })

        viewModel?.getPosts()
    }

}