package com.trancas.salgado

import com.trancas.salgado.screens.login.LoginViewModel
import com.trancas.salgado.service.LoginService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { SessaoUsuario() }

    factory<LoginService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getApi(sessao.token)
    }

    viewModel {
        LoginViewModel(
            api = get(),
            sessaoUsuario = get()
        )
    }
}
