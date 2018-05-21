package com.solutis.base.baseproj.model.service.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.solutis.base.baseproj.model.entity.Post
import com.solutis.base.baseproj.model.entity.Usuario


/**
 * Created by osiascarneiro on 05/12/17.
 */

@Database(entities = [Usuario::class, Post::class], version = 1)
abstract class BancoLocal : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

}
