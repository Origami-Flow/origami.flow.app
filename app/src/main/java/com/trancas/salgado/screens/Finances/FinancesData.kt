package com.trancas.salgado.screens.Finances

import com.google.gson.annotations.SerializedName

data class FinancesData(
    @SerializedName("lucroMes")
    var profitMonth: Double
)

