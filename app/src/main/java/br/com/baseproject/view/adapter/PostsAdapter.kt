package br.com.baseproject.view.adapter

import br.com.baseproject.R
import br.com.baseproject.model.entidade.Post
import br.com.baseproject.view.component.DataBindingBaseAdapter
import kotlin.properties.Delegates

class PostsAdapter: DataBindingBaseAdapter() {

    var posts by Delegates.observable(listOf<Post>(), { _, _, _ ->
        notifyDataSetChanged()
    })

    override fun getObjForPosition(position: Int): Any {
        return posts[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.activity_posts_item
    }

    override fun getItemCount(): Int {
        return posts.count()
    }

}