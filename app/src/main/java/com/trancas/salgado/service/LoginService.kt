package com.trancas.salgado.service

import com.trancas.salgado.screens.login.LoginRequestData
import com.trancas.salgado.screens.login.LoginResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginService {
    @POST("logins")
    suspend fun postLogin(
        @Body loginRequestData: LoginRequestData
    ): Response<LoginResponseData>
}

