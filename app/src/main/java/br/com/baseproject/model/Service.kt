package br.com.baseproject.model

import br.com.baseproject.model.consulta.LoginConsulta
import br.com.baseproject.model.entidade.Post
import br.com.baseproject.model.entidade.Usuario
import io.reactivex.Observable
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