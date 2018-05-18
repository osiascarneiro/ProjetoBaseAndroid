package com.solutis.base.baseproj.repository

/**
 * Created by osiascarneiro on 07/11/17.
 */
 enum class Ambiente {

    PRODUCAO, DESENVOLVIMENTO, HOMOLOGACAO;

    fun getUrl(): String {
        return when(this) {
            PRODUCAO -> ""
            DESENVOLVIMENTO -> "http://despero.solutis.net.br:9080/<Sua Base URL>/"
            HOMOLOGACAO -> ""
        }
    }
}