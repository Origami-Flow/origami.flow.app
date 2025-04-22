package com.trancas.salgado.screens.schedule.classes

import java.time.LocalTime

data class Event(
    val nome: String,
    val cliente: String,
    val telefone: String,
    val horario: LocalTime,
    val tipo: String,
)
