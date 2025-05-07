package com.trancas.salgado.screens.metrics.classes

data class Metrics(
    val vendasDoMes: Int = 0,
    val agendamentosDoMes: Int = 0,
    val clientesNovosNoMes: Int = 0,
    val trancaMaisFeitaNoMes: String? = null,
    val taxaDeClienteQueAgendaramNoMes: Int = 0,
    val lucroDoMesAtual: Double = 0.0,
    val lucroDoMesPassado: Double = 0.0
)
