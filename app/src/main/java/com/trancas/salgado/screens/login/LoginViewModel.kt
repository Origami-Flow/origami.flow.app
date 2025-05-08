package com.trancas.salgado.screens.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.trancas.salgado.SessaoUsuario
import com.trancas.salgado.service.LoginService
import kotlinx.coroutines.launch


class LoginViewModel(private val api: LoginService, private val sessaoUsuario: SessaoUsuario) : ViewModel() {

    var email by mutableStateOf("")
    var senha by mutableStateOf("")
    var erros = mutableStateListOf<String>()
    var isLoginSuccessful by mutableStateOf(false)
    var token: String? = null

    fun loginApp() {
        erros.clear()

        viewModelScope.launch {
            try {
                if (email.isEmpty()) {
                    erros.add("Requer o e-mail")
                }
                if (senha.isEmpty()) {
                    erros.add("Requer a senha")
                }
                if (erros.isEmpty()) {
                    val loginRequest = LoginRequestData(email = email, senha = senha)

                    val response = api.postLogin(loginRequest)

                    sessaoUsuario.token = response.token
                    sessaoUsuario.id = response.id
                    sessaoUsuario.nome = response.nome

                    if (response != null) {
                        isLoginSuccessful = true
                        token = response.token
                    } else {
                        Log.e("API", "Login falho: Credenciais inválidas")
                        erros.add("E-mail ou senha inválidos")
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao efetuar login: ${e.message}")
                val loginRequest = LoginRequestData(email = email, senha = senha)
                Log.e("LOGIN_DEBUG", "JSON enviado: ${Gson().toJson(loginRequest)}")
                erros.add("Erro ao efetuar login: ${e.message}")
            }
        }
    }
}
