package com.solutis.base.baseproj.repository.entidade

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by osiascarneiro on 07/11/17.
 */

@Entity(tableName = "usuario")
class Usuario {

    @ColumnInfo(name="tokenType")
    @SerializedName("token_type")
    var tokenType: String? = null

    @PrimaryKey
    @ColumnInfo(name="token")
    @SerializedName("access_token")
    var token: String = ""

    @ColumnInfo(name="nome")
    @SerializedName("nome")
    var nome: String? = null

    @ColumnInfo(name="login")
    @SerializedName("login")
    var login: String? = null

    @ColumnInfo(name="foto")
    @SerializedName("foto")
    var foto: String? = null

    @ColumnInfo(name="email")
    @SerializedName("email")
    var email: String? = null

    @ColumnInfo(name="matricula")
    @SerializedName("matricula")
    var matricula: String? = null

}