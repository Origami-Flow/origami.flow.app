package com.trancas.salgado.screens.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.SessaoUsuario
import com.trancas.salgado.service.LoginService
import kotlinx.coroutines.launch


class LoginViewModel(private val api: LoginService, private val sessaoUsuario: SessaoUsuario) : ViewModel() {

    var email by mutableStateOf("")
    var senha by mutableStateOf("")
    var erros = mutableStateListOf<String>()
    var loginResult by mutableStateOf<LoginResult>(LoginResult.Idle)
        private set
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

                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            sessaoUsuario.token = responseBody.token
                            sessaoUsuario.id = responseBody.id
                            sessaoUsuario.nome = responseBody.nome
                            Log.d("API", "Login bem-sucedido: ${responseBody.nome}")

                            token = responseBody.token
                            loginResult = LoginResult.Success
                        } else {
                            Log.e("API", "Resposta vazia")
                            erros.add("Erro inesperado. Tente novamente.")
                            loginResult = LoginResult.Failure("Erro inesperado.")
                        }
                    } else {
                        val errorMsg = when (response.code()) {
                            401 -> "E-mail ou senha inválidos"
                            else -> "Erro ao efetuar login: código ${response.code()}"
                        }
                        Log.e("API", errorMsg)
                        erros.add(errorMsg)
                        loginResult = LoginResult.Failure(errorMsg)
                    }
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao efetuar login: ${e.message}")
                erros.add("Erro ao efetuar login: ${e.message}")
                loginResult = LoginResult.Failure("Erro ao efetuar login: ${e.message}")
            }
        }
    }
}

sealed class LoginResult {
    data object Idle : LoginResult()
    data object Success : LoginResult()
    data class Failure(val message: String) : LoginResult()
}