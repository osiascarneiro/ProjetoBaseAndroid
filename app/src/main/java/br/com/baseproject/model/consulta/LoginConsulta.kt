package br.com.baseproject.model.consulta

import com.google.gson.annotations.SerializedName

/**
 * Created by osiascarneiro on 07/11/17.
 */

class LoginConsulta(@SerializedName("usuario") var usuario: String, @SerializedName("senha") var senha: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginConsulta

        if (usuario != other.usuario) return false
        if (senha != other.senha) return false

        return true
    }
}