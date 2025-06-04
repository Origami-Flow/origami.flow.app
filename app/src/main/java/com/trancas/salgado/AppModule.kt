package com.trancas.salgado
import com.trancas.salgado.screens.login.LoginViewModel
import com.trancas.salgado.service.ClientService
import com.trancas.salgado.service.LoginService
import org.koin.androidx.viewmodel.dsl.viewModel
import com.trancas.salgado.screens.client.ClientViewModel
import com.trancas.salgado.screens.event.CreateEventViewModel
import com.trancas.salgado.screens.event.EditEventViewModel
import com.trancas.salgado.screens.extract.ExtractViewModel
import com.trancas.salgado.screens.finances.FinancesViewModel
import com.trancas.salgado.screens.metrics.MetricsViewModel
import com.trancas.salgado.screens.schedule.WeeklyScheduleViewModel
import com.trancas.salgado.screens.stock.AddProductViewModel
import com.trancas.salgado.screens.stock.StockViewModel
import com.trancas.salgado.screens.stock.UpdateProductViewModel
import com.trancas.salgado.service.AssistantService
import com.trancas.salgado.service.AtendimentoService
import com.trancas.salgado.service.DespesaService
import com.trancas.salgado.service.EventService
import com.trancas.salgado.service.FinancesService
import com.trancas.salgado.service.MetricsService
import com.trancas.salgado.service.ScheduleService
import com.trancas.salgado.service.ServicesService
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
    factory<MetricsService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getMetricas(sessao.token)
    }
    factory<FinancesService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getFinances(sessao.token)
    }
    factory<DespesaService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getDespesas(sessao.token)
    }
    factory<AtendimentoService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getAtendimentos(sessao.token)
    }
    factory<ServicesService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getServices(sessao.token)
    }
    factory<EventService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getEvent(sessao.token)
    }
    factory<AssistantService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getAssisant(sessao.token)
    }
    factory<ScheduleService> {
        val sessao = get<SessaoUsuario>()
        SalgadoApi.getSchedule(sessao.token)
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
    viewModel{
        MetricsViewModel(
            api = get()
        )
    }
    viewModel{
        FinancesViewModel(
            api = get()
        )
    }
    viewModel{
        ExtractViewModel(
            api = get(),
            apiAtendimento = get()
        )
    }
    viewModel{
        CreateEventViewModel(
            api = get(),
            apiEvent = get(),
            apiClient = get(),
            apiAssist = get()
        )
    }
    viewModel{
        EditEventViewModel(
            api = get(),
            apiAssist = get(),
            apiSchedule = get()
        )
    }
    viewModel{
        WeeklyScheduleViewModel(
            api = get()
        )
    }
}
