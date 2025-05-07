package com.trancas.salgado.screens.extract.classes

abstract class Transaction(
    open val data: List<Int>,
    open val valor: Double
)

data class Atendimento(
    val nomeCliente: String,
    val tipoAtendimento: Boolean,
    override val data: List<Int>,
    override val valor: Double
) : Transaction(data, valor)

data class Despesa(
    val nome: String,
    val descricao: String,
    override val data: List<Int>,
    override val valor: Double
) : Transaction(data, valor)