package com.trancas.salgado.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trancas.salgado.service.SalgadoApi
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val api = SalgadoApi.api
    var email: String = "";
    var password: String = "";



    private val _erros = mutableStateListOf<String>();

    fun login() {
        _erros.clear();
        viewModelScope.launch {
            try {
                if (email.isEmpty()) {
                    _erros.add("Requer o e-mail")
                }
                if (password.isEmpty()) {
                    _erros.add("Requer a senha")
                }
                if (_erros.isEmpty()) {
                    val loginRequest = LoginRequestData(
                        email = email,
                        password = password
                    )
                    val response = api.postLogin(
                        loginRequest
                    )
                    if (response != null) {
                        Log.d("API", "Login efetuado com sucesso: ${response}")
                    } else {
                        Log.d("API", "Login falho: Credenciais invalidas")
                        _erros.add("E-mail ou senha invalidos")
                    }
                } else {
                    _erros.add("Por favor insira todos os campos")
                }
            } catch (e: Exception) {
                Log.d("API", "Erro ao efetuar login: ${e.message}")
                _erros.add(e.message.toString())
            }
        }

    }
}