package com.trancas.salgado.screens.login

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    //private val api;
    var email: String = "";
    var password: String = "";


    private val _erros = mutableStateListOf<String>();
}