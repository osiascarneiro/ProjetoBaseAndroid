package br.com.baseproject.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.baseproject.model.entidade.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

    @Update
    fun updatePost(post: Post)

    @Delete
    fun deletePost(post: Post)

    @Query("SELECT * FROM post WHERE id = :id")
    fun getPost(id: Int): LiveData<Post>

}
