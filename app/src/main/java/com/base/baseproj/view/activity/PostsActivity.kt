package com.base.baseproj.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.sabesp.redesabesp.viewmodel.ViewModelFactory
import com.base.baseproj.BaseProjApplication
import com.base.baseproj.R
import com.base.baseproj.view.adapter.PostsAdapter
import com.base.baseproj.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostsActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<PostsViewModel>

    var viewModel: PostsViewModel? = null
    var adapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        (application as BaseProjApplication).graph.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)
        posts.layoutManager = LinearLayoutManager(this)
        posts.adapter = adapter
        doBindings()
    }

    fun doBindings() {
        viewModel?.posts?.observe(this, Observer {
            adapter.posts = it ?: listOf()
        })

        viewModel?.status?.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = it ?: false
        })

        viewModel?.error?.observe(this, Observer {
            Snackbar.make(root, it ?: "Houve um erro desconhecido", Snackbar.LENGTH_SHORT)
        })
        swipeRefreshLayout.setOnRefreshListener { viewModel?.refreshItens() }

        viewModel?.getPosts()
    }

}