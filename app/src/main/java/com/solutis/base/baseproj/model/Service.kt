package com.solutis.base.baseproj.model

import com.solutis.base.baseproj.model.entity.Post
import com.solutis.base.baseproj.model.service.remote.LoginConsulta
import com.solutis.base.baseproj.model.entity.Usuario
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by osiascarneiro on 07/11/17.
 */

interface Service {

    @POST("api/v1/oauth/autenticar")
    @Headers("Content-Type: application/json")
    fun login(@Body consulta: LoginConsulta): Observable<Usuario>

    @GET("posts")
    fun listPosts(): Observable<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Observable<Post?>

}