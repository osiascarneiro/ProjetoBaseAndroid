package br.com.baseproject.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.baseproject.model.entidade.Post
import br.com.baseproject.model.entidade.Usuario


/**
 * Created by osiascarneiro on 05/12/17.
 */

@Database(entities = [Usuario::class, Post::class], version = 1, exportSchema = false)
abstract class BancoLocal : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun postDao(): PostDao

}
