package br.com.baseproject.viewmodel

import android.content.SharedPreferences
import br.com.baseproject.RxImmediateSchedulerRule
import br.com.baseproject.model.local.BancoLocal
import com.google.gson.Gson
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.baseproject.model.Service

open class BaseViewModelTests {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @Mock
    lateinit var localDb: BancoLocal

    @Mock
    lateinit var service: Service

    lateinit var erroDefault: Throwable

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
        erroDefault = Throwable("Erro")
    }

    @Test
    fun nothing() {
        assert(true)
    }

    fun <T> getObject(fileName: String, type: Type) : T? {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("$fileName.json")

        val r = BufferedReader(InputStreamReader(inputStream))
        val json = StringBuilder()
        for (line in r.lines()) {
            json.append(line)
        }

        val jsonString = json.toString()
        val gson = Gson()
        return gson.fromJson<T>(jsonString, type)
    }

}