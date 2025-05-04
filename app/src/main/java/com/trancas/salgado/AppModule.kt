package com.trancas.salgado

import com.trancas.salgado.screens.login.LoginViewModel
import com.trancas.salgado.service.LoginService
import com.trancas.salgado.service.SalgadoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
