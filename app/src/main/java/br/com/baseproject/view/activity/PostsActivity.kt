package br.com.baseproject.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.baseproject.R
import br.com.baseproject.view.adapter.PostsAdapter
import br.com.baseproject.viewmodel.PostsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_posts.*

class PostsActivity: BaseActivity<PostsViewModel>() {

    override var viewModel = PostsViewModel()
    var adapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        posts.adapter = adapter
        doBindings()
        viewModel.getPosts()
    }

    fun doBindings() {
        viewModel.posts.observe(this, Observer {
            adapter.posts = it ?: listOf()
        })

        viewModel.status.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = it ?: false
        })

        viewModel.error.observe(this, Observer {
            Snackbar.make(root, it ?: "Houve um erro desconhecido", Snackbar.LENGTH_SHORT)
        })
        swipeRefreshLayout.setOnRefreshListener { viewModel.refreshItens() }
    }

}
