package br.com.sabesp.redesabesp.module

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.solutis.base.baseproj.BuildConfig
import com.solutis.base.baseproj.model.Service
import com.solutis.base.baseproj.model.service.local.BancoLocal
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by osiascarneiro on 07/11/17.
 */

@Module
class ApiModule(val context: Context) {

    @Provides
    fun getAmbiente(): String = BuildConfig.SERVER_URL

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val ambiente = getAmbiente()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor {
                    val requestBuilder = it.request().newBuilder()
                    requestBuilder.header("Accept", "application/json")
                    it.proceed(requestBuilder.build())
                }
                .build()

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ambiente)
                .client(httpClient)
                .build()
    }

    @Provides
    fun getClient(): Service = getRetrofit().create(Service::class.java)

    @Provides
    @Singleton
    fun getDatabase(): BancoLocal =
            Room.databaseBuilder(context.applicationContext, BancoLocal::class.java, "local_storage").build()

    @Provides
    fun getSharedPref(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}