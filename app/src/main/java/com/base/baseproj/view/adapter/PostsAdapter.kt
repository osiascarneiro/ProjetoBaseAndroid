package com.base.baseproj.view.adapter

import com.base.baseproj.R
import com.base.baseproj.model.entity.Post
import com.base.baseproj.view.component.DataBindingBaseAdapter
import kotlin.properties.Delegates

class PostsAdapter: DataBindingBaseAdapter() {

    var posts by Delegates.observable(listOf<Post>(), { _, _, _ ->
        notifyDataSetChanged()
    })

    override fun getObjForPosition(position: Int): Any {
        return posts[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.item_post
    }

    override fun getItemCount(): Int {
        return posts.count()
    }

}