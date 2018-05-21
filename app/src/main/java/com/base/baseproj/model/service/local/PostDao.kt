package com.base.baseproj.model.service.local

import android.arch.persistence.room.*
import com.base.baseproj.model.entity.Post

interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

    @Update
    fun updatePost(post: Post)

    @Delete
    fun deletePost(post: Post)

    @Query("SELECT * FROM post WHERE id = :id")
    fun getPost(id: Int)

}