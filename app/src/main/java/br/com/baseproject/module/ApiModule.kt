package br.com.baseproject.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import br.com.baseproject.BaseProjectApplication
import br.com.baseproject.BuildConfig
import br.com.baseproject.model.Service
import br.com.baseproject.model.local.BancoLocal
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
class ApiModule {

    @Singleton
    @Provides
    fun provideContext(application: BaseProjectApplication): Context {
        return application
    }

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
    fun getDatabase(context: Context): BancoLocal =
            Room.databaseBuilder(context, BancoLocal::class.java, "local_storage").build()

    @Provides
    fun getSharedPref(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}