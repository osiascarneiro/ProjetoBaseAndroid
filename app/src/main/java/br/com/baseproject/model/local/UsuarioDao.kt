package br.com.baseproject.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.baseproject.model.entidade.Usuario

/**
 * Created by osias on 06/12/2017.
 */

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsuario(user: Usuario)

    @Update
    fun updateUsuario(user: Usuario)

    @Delete
    fun deleteUsuario(user: Usuario)

    @Query("SELECT * FROM usuario WHERE token = :arg")
    fun getUsuario(arg: String): LiveData<Usuario>

}