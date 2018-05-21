package com.solutis.base.baseproj.model.local

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.Database
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.InvalidationTracker
import android.arch.persistence.room.RoomDatabase
import com.solutis.base.baseproj.model.entidade.Usuario


/**
 * Created by osiascarneiro on 05/12/17.
 */

@Database(entities = [Usuario::class], version = 1)
abstract class BancoLocal : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

}
