package com.trancas.salgado
import com.trancas.salgado.screens.login.LoginViewModel
import com.trancas.salgado.service.ClientService
import com.trancas.salgado.service.LoginService
import org.koin.androidx.viewmodel.dsl.viewModel
import com.trancas.salgado.screens.client.ClientViewModel
import com.trancas.salgado.screens.stock.AddProductViewModel
import com.trancas.salgado.screens.stock.StockViewModel
import com.trancas.salgado.screens.stock.UpdateProductViewModel
import com.trancas.salgado.service.StockService
import com.trancas.salgado.screens.clients.ClientViewModel as ClientViewModel2
import org.koin.dsl.module

val appModule = module {

    single { SessaoUsuario() }

    factory<LoginService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getApi(sessao.token)
    }

    factory<ClientService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getClientApi(sessao.token)
    }
    factory<StockService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getStockApi(sessao.token)
    }

    viewModel {
        LoginViewModel(
            api = get(),
            sessaoUsuario = get()
        )
    }

    viewModel {
        ClientViewModel(
            api = get(),
        )
    }

    viewModel{
        ClientViewModel2(
            api = get()
        )
    }

    viewModel{
        StockViewModel(
            api = get()
        )
    }
    viewModel{
        AddProductViewModel(
            api = get()
        )
    }
    viewModel{
        UpdateProductViewModel(
            api = get()
        )
    }



}
