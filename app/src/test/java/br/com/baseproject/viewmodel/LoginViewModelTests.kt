package br.com.baseproject.viewmodel

import br.com.baseproject.model.consulta.LoginConsulta
import br.com.baseproject.model.entidade.Usuario
import br.com.baseproject.model.local.UsuarioDao
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class LoginViewModelTests: BaseViewModelTests() {

    lateinit var sut: LoginViewModel

    @Mock
    lateinit var usuarioDao: UsuarioDao

    @Before
    override fun setUp() {
        super.setUp()
        sut = LoginViewModel()
        sut.localDb = localDb
        sut.sharedPref = sharedPreferences
        sut.service = service
    }

    @Test
    fun `Logando usuario no servico`() {
        val usuario = "foo.test"
        val senha = "123456"
        val usuarioObj = getObject<Usuario>("usuario", Usuario::class.java)
        //Falhando teste caso seja nulo
        if(usuarioObj == null) {
            fail("Usuário nulo")
            return
        }

        val consulta = LoginConsulta(usuario, senha)
        whenever(service.login(consulta)).thenReturn(Observable.just(usuarioObj))
        whenever(localDb.usuarioDao()).thenReturn(usuarioDao)
        whenever(usuarioDao.insertUsuario(usuarioObj)).then {}

        sut.login(usuario, senha)

        assert(sut.status.value == false)
        assert(sut.usuario.value == usuarioObj)
    }

}