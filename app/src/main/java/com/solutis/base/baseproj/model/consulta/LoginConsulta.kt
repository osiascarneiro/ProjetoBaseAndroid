package com.solutis.base.baseproj.model.consulta

import com.google.gson.annotations.SerializedName

/**
 * Created by osiascarneiro on 07/11/17.
 */

class LoginConsulta {

    @SerializedName("usuario")
    var usuario: String

    @SerializedName("senha")
    var senha: String

    constructor(usuario: String, senha: String) {
        this.usuario = usuario
        this.senha = senha
    }
}