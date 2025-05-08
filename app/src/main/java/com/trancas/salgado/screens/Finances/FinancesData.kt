package com.trancas.salgado.screens.finances

import com.google.gson.annotations.SerializedName

data class FinancesData(
    @SerializedName("lucroMes")
    var profitMonth: Double
)

