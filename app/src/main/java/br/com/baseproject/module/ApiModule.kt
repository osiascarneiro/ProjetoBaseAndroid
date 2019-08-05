package br.com.baseproject.module

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import br.com.baseproject.BaseProjectApplication
import br.com.baseproject.BuildConfig
import br.com.baseproject.model.Service
import br.com.baseproject.model.local.BancoLocal
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by osiascarneiro on 07/11/17.
 */

@Module
object ApiModule {

    @JvmStatic
    @Provides
    fun getAmbiente(): String = BuildConfig.SERVER_URL

    @JvmStatic
    @Provides
    @Reusable
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

    @JvmStatic
    @Provides
    fun getClient(): Service = getRetrofit().create(Service::class.java)

    @JvmStatic
    @Provides
    @Reusable
    fun getDatabase(context: BaseProjectApplication): BancoLocal
            = Room.databaseBuilder(context, BancoLocal::class.java, "local_storage").build()

    @JvmStatic
    @Provides
    fun getSharedPref(context: BaseProjectApplication): SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)
}